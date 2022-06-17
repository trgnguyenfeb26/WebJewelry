package jewelry.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jewelry.bean.Mailer;
import jewelry.entity.UserEntity;
import jewelry.ultils.PasswordUltils;
@Controller
public class ForgotPassController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	@Transactional
	@RequestMapping(value = "/forgotPass", method = RequestMethod.GET)
	public String forgotPassword(@ModelAttribute("user") UserEntity user, ModelMap model) {
		model.addAttribute("user", new UserEntity());
		model.addAttribute("frmCtrlEmail", "form-control form-control-lg");
		model.addAttribute("frmCtrlCaptcha", "form-control form-control-lg");
		return "forgotPass";
	}
	public List<UserEntity> getUser(String email) {
		Session session = factory.openSession();
		String hql = "FROM UserEntity u WHERE u.email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		return query.list();
	}
	@RequestMapping(value = "/forgotPassVal", method = RequestMethod.POST)
	public String loginSucess(ModelMap model, @ModelAttribute("user") UserEntity user, HttpServletRequest request,
			HttpSession ss, HttpServletResponse response)
			throws InvalidKeySpecException {
		
		boolean err = false;
		Pattern patternEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		user.setEmail(user.getEmail().trim());
		String email = user.getEmail();
		ss.setAttribute("email", email);
		if (email.isBlank()) {
			model.addAttribute("frmCtrlEmail", "form-control is-invalid");
			model.addAttribute("fbEmail", "invalid-feedback");
			model.addAttribute("contentFBEmail", "Không được để trống email");
			err = true;

		} else {

			Matcher matcher = patternEmail.matcher(user.getEmail());
			boolean match = matcher.matches();
			if (!match) {
				model.addAttribute("frmCtrlEmail", "form-control is-invalid");
				model.addAttribute("fbEmail", "invalid-feedback");
				model.addAttribute("contentFBEmail", "Địa chỉ email không hợp lệ");
				err = true;
			} else {
				model.addAttribute("frmCtrlEmail", "form-control");

			}
		}
	
		String captcha = ss.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");
		boolean verify = false;

		if (captcha.equals(verifyCaptcha)) {

			verify = true;
		} else {
			verify = false;
		}

		if (!verify) {
			model.addAttribute("frmCtrlCaptcha", "form-control is-invalid");
			model.addAttribute("fbCaptcha", "invalid-feedback");
			model.addAttribute("contentFBCaptcha", "Mời bạn nhập lại captcha");
			return "forgotPass";
		} else {
			model.addAttribute("frmCtrlCaptcha", "form-control");
		}
		if (err == false && verify) {
			if (getUser(email).size() == 0) {
				model.addAttribute("frmCtrlEmail", "form-control is-invalid");
				model.addAttribute("fbEmail", "invalid-feedback");
				model.addAttribute("contentFBEmail", "Email bạn nhập không kết nối với tài khoản nào");
				err = true;
			} else {
				UserEntity userDB = new UserEntity();
				userDB = getUser(email).get(0);
			
				String newPass= PasswordUltils.generateRandomPassword();
				mailer.sendPassword(newPass,email);
				String salt = PasswordUltils.getSalt(30);
				userDB.setSalt(salt);
				String yourPass = "";
				try {
					yourPass = PasswordUltils.generateSecurePassword(newPass, salt);
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				userDB.setPassword(yourPass);
				Session session=factory.openSession();
						Transaction t = session.beginTransaction();
						UserEntity userTemp = (UserEntity) session.get(UserEntity.class,userDB.getId());
				
				try {
					userTemp.setPassword(userDB.getPassword());
					userTemp.setSalt(userDB.getSalt());
					session.update(userTemp);
					
					t.commit();

					model.addAttribute("message", "Cập nhật thành công!");
					// Cập nhật thông tin phiên hiện tại
					return "redirect:/login/notif.htm";
				
			
				} catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Cập nhật thất bại!");
				} finally {
					session.close();
				}
	
				System.out.print("new pass: "+newPass);
			}
		}

		return "forgotPass";
	}
	

}
