package jewelry.controller;

import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jewelry.bean.Mailer;
import jewelry.bean.Verify;
import jewelry.entity.UserEntity;
import jewelry.ultils.PasswordUltils;

@Controller
public class LoginController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	@Transactional
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model, HttpSession ss) {
		if (ss.getAttribute("user") != null) {
			return "redirect:/home.htm";
		} else {
			model.addAttribute("user", new UserEntity());
			model.addAttribute("frmCtrlPsw", "form-control form-control-lg");
			model.addAttribute("frmCtrlEmail", "form-control form-control-lg");
			model.addAttribute("frmCtrlCaptcha", "form-control form-control-lg");
			return "login";
		}

	}

	@RequestMapping(value = "/login/{logined}", method = RequestMethod.GET)
	public String loginError(ModelMap model, @PathVariable("logined") String logined,HttpSession session) {
		if(logined.equals("notif"))
		{
			model.addAttribute("frmCtrl","col-md-12 mb-4 alert alert-success");
			model.addAttribute("warning", "");
			model.addAttribute("message", "Lấy lại mật khẩu thành công! Mật khẩu đã được gửi qua email của bạn!");
			model.addAttribute("email",session.getAttribute("email"));
		}
		else
		{
			model.addAttribute("frmCtrl","col-md-12 mb-4 alert alert-danger");
			model.addAttribute("warning", "Chưa đăng nhập!");
			model.addAttribute("message", "Bạn phải đăng nhập để tiếp tục!");
		}
		
		model.addAttribute("user", new UserEntity());
		model.addAttribute("frmCtrlPsw", "form-control form-control-lg");
		model.addAttribute("frmCtrlEmail", "form-control form-control-lg");
		model.addAttribute("frmCtrlCaptcha", "form-control form-control-lg");
		return "login";

	}

	public List<UserEntity> getUser(String email) {
		Session session = factory.openSession();
		String hql = "FROM UserEntity u WHERE u.email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		return query.list();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSucess(ModelMap model, @ModelAttribute("user") UserEntity user, HttpServletRequest request,
			HttpSession ss, HttpServletResponse response, @RequestParam("psw") String psw)
			throws InvalidKeySpecException {

		boolean err = false;
		Pattern patternEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		user.setEmail(user.getEmail().trim());
		String email = user.getEmail();
		if (email.isBlank()) {
			model.addAttribute("frmCtrlEmail", "form-control form-control-lg is-invalid");
			model.addAttribute("fbEmail", "invalid-feedback");
			model.addAttribute("contentFBEmail", "Không được để trống email");
			err = true;

		} else {

			Matcher matcher = patternEmail.matcher(user.getEmail());
			boolean match = matcher.matches();
			if (!match) {
				model.addAttribute("frmCtrlEmail", "form-control form-control-lg is-invalid");
				model.addAttribute("fbEmail", "invalid-feedback");
				model.addAttribute("contentFBEmail", "Địa chỉ email không hợp lệ");
				err = true;
			} else {
				model.addAttribute("frmCtrlEmail", "form-control form-control-lg");

			}
		}
		if (psw.isBlank()) {
			model.addAttribute("frmCtrlPsw", "form-control form-control-lg is-invalid");
			model.addAttribute("fbPsw", "invalid-feedback");
			model.addAttribute("contentFBPsw", "Không được để trống mật khẩu");
			err = true;
		} else {
			model.addAttribute("frmCtrlPsw", "form-control form-control-lg");
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
			model.addAttribute("frmCtrlCaptcha", "form-control form-control-lg is-invalid");
			model.addAttribute("fbCaptcha", "invalid-feedback");
			model.addAttribute("contentFBCaptcha", "Mời bạn nhập lại captcha");
			return "login";
		} else {
			model.addAttribute("frmCtrlCaptcha", "form-control form-control-lg");
		}
		if (err == false && verify) {
			if (getUser(email).size() == 0) {
				model.addAttribute("frmCtrlEmail", "form-control form-control-lg is-invalid");
				model.addAttribute("fbEmail", "invalid-feedback");
				model.addAttribute("contentFBEmail", "Email bạn nhập không kết nối với tài khoản nào");
				err = true;
			} else {
				UserEntity userDB = new UserEntity();
				userDB = getUser(email).get(0);
				if(userDB.getStatusDelete()==1)
				{
					model.addAttribute("frmCtrlEmail", "form-control form-control-lg is-invalid");
					model.addAttribute("fbEmail", "invalid-feedback");
					model.addAttribute("contentFBEmail", "Tài khoản của bạn đã bị khoá");
					return "login";
				}
				boolean isUser = PasswordUltils.verifyUserPassword(psw, userDB.getPassword(), userDB.getSalt());
				if (isUser) {
					if(userDB.getIsVerify()==0)
					{
						ss.setAttribute("userVerify", userDB);
						Verify verifyCode= new Verify();
						verifyCode.codeVerify=PasswordUltils.generateRandomPassword();
						mailer.sendVerifyCode(verifyCode.codeVerify, email);
						return "redirect:/user/verify.htm";
					}
					if(userDB.getRole_id()==0)
					ss.setAttribute("user", userDB);
					else
						ss.setAttribute("admin", userDB);
					ss.setAttribute("email", email);
					try {
						System.out.print("path: " + request.getContextPath());
						response.sendRedirect(request.getContextPath()
								+ (userDB.getRole_id() == 0 ? "/home.htm" : "/admin/user.htm"));

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					model.addAttribute("frmCtrlEmail", "form-control form-control-lg is-invalid");
					model.addAttribute("fbEmail", "invalid-feedback");
					model.addAttribute("contentFBEmail", "Email hoặc mật khẩu bạn nhập không đúng");
					err = true;

				}
			}
		}

		return "login";
	}

	@RequestMapping("/logout")
	public String logut(HttpSession ss) {
		ss.removeAttribute("user");
		ss.removeAttribute("admin");
		ss.removeAttribute("email");
		return "redirect:/login.htm";
	}

}