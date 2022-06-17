package jewelry.controller;

import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;

import jewelry.bean.Mailer;
import jewelry.bean.Verify;
import jewelry.entity.ProductEntity;
import jewelry.entity.UserEntity;
import jewelry.ultils.PasswordUltils;

@Controller
public class RegisterController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	@Transactional
	@RequestMapping("register")
	public String register(ModelMap model) {
		model.addAttribute("user", new UserEntity());
		model.addAttribute("frmCtrlLastName", "form-control form-control-lg");
		model.addAttribute("frmCtrlFirstName", "form-control form-control-lg");
		model.addAttribute("frmCtrlEmail", "form-control form-control-lg");
		model.addAttribute("frmCtrlPhone", "form-control form-control-lg");
		model.addAttribute("frmCtrlAdd", "form-control form-control-lg");
		model.addAttribute("frmCtrlPsw", "form-control form-control-lg");
		model.addAttribute("frmCtrlRePsw", "form-control form-control-lg");
		model.addAttribute("frmCtrlCaptcha", "form-control form-control-lg");
		model.addAttribute("frmCtrlBD", "form-control form-control-lg");
		return "register";
	}

	public Integer saveAccount(UserEntity user) {
		Session session = factory.openSession();
		org.hibernate.Transaction t = session.beginTransaction();
		int ret = 0;
		try {
			session.save(user);
			t.commit();
		} catch (Exception e) {
		
			t.rollback();
			ret = -1;
		} finally {
			session.close();
		}
		return ret;

	}

	public List<UserEntity> getUser(String email) {
		Session session = factory.openSession();
		String hql = "FROM UserEntity u WHERE u.email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		return query.list();
	}

	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public String validate(ModelMap model, @Validated @ModelAttribute("user") UserEntity user, BindingResult errors,
			@RequestParam("psw_repeat") String psw_repeat,
			@RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday, HttpServletRequest request,
			HttpSession ss, HttpServletResponse response) {
		
		boolean err = false;
		user.setEmail(user.getEmail().trim());
		if (user.getLastname().isBlank()) {
			model.addAttribute("frmCtrlLastName", "form-control form-control-lg is-invalid");
			model.addAttribute("fbLastName", "invalid-feedback");
			model.addAttribute("contentFBLN", "Không được để trống họ");
			err = true;
		} else {
			model.addAttribute("frmCtrlLastName", "form-control form-control-lg is-valid");
		
		}
		if(birthday==null)
		{
			model.addAttribute("frmCtrlBD", "form-control form-control-lg is-invalid");
			model.addAttribute("fbBD", "invalid-feedback");
			model.addAttribute("contentFBBD", "Không được để trống ngày sinh");
			err = true;
		}
		else
		{
			model.addAttribute("frmCtrlBD", "form-control form-control-lg is-valid");
			user.setBirthday(birthday);
		}
		if (user.getAddress().isBlank()) {
			model.addAttribute("frmCtrlAdd", "form-control form-control-lg is-invalid");
			model.addAttribute("fbAdd", "invalid-feedback");
			model.addAttribute("contentFBAdd", "Không được để trống địa chỉ");
			err = true;
		} else {
			model.addAttribute("frmCtrlAdd", "form-control form-control-lg is-valid");
			
		}

		if (user.getFirstname().isBlank()) {
			model.addAttribute("frmCtrlFirstName", "form-control form-control-lg is-invalid");
			model.addAttribute("fbFirstName", "invalid-feedback");
			model.addAttribute("contentFBFN", "Không được để trống tên");
			err = true;

		} else {
			model.addAttribute("frmCtrlFirstName", "form-control form-control-lg is-valid");
		
		}

		Pattern patternEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if (user.getEmail().isBlank()) {
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
			} else if (getUser(user.getEmail()).size() != 0) {
				model.addAttribute("frmCtrlEmail", "form-control form-control-lg is-invalid");
				model.addAttribute("fbEmail", "invalid-feedback");
				model.addAttribute("contentFBEmail", "Địa chỉ email được được đăng ký bởi tài khoản khác");
				err = true;
			} else {
				model.addAttribute("frmCtrlEmail", "form-control form-control-lg is-valid");
				
			}
		}

		

		String regexPhone = "(84|0[3|5|7|8|9])+([0-9]{8})";
		Pattern patternPhone = Pattern.compile(regexPhone);
		if (user.getPhone().isBlank()) {
			model.addAttribute("frmCtrlPhone", "form-control form-control-lg is-invalid");
			model.addAttribute("fbPhone", "invalid-feedback");
			model.addAttribute("contentFBPhone", "Số điện thoại không được để trống");
			err = true;
		} else if (!user.getPhone().isBlank()) {
			Matcher matcher = patternPhone.matcher(user.getPhone());
			boolean match = matcher.matches();
			if (!match) {
				model.addAttribute("frmCtrlPhone", "form-control form-control-lg is-invalid");
				model.addAttribute("fbPhone", "invalid-feedback");
				model.addAttribute("contentFBPhone", "Số điện thoại không hợp lệ");
				err = true;
			} else {
				model.addAttribute("frmCtrlPhone", "form-control form-control-lg is-valid");
				
			}
		}
		String regexPass = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
		Pattern patternPass = Pattern.compile(regexPass);
		boolean matchPass = true;
		if (user.getPassword().isBlank()) {
			model.addAttribute("frmCtrlPsw", "form-control form-control-lg is-invalid");
			model.addAttribute("fbPsw", "invalid-feedback");
			model.addAttribute("contentFBPsw", "Không được để trống mật khẩu");
			err = true;
		}

		else if (!user.getPassword().isBlank()) {
			Matcher matcher = patternPass.matcher(user.getPassword());
			matchPass = matcher.matches();
			if (!matchPass) {
				model.addAttribute("frmCtrlPsw", "form-control form-control-lg is-invalid");
				model.addAttribute("fbPsw", "invalid-feedback");
				model.addAttribute("contentFBPsw",
						"Mật khẩu phải chứa tối thiểu 8 ký tự, bao gồm chữ cái, chữ số, ký tự đặc biệt");
				err = true;
			} else {
				model.addAttribute("frmCtrlPsw", "form-control form-control-lg is-valid");
				
			}
		}

		if (psw_repeat.isBlank()) {
			model.addAttribute("frmCtrlRePsw", "form-control form-control-lg is-invalid");
			model.addAttribute("fbRePsw", "invalid-feedback");
			model.addAttribute("contentFBRePsw", "Không được để trống");
			err = true;
		} else if (matchPass && !psw_repeat.equals(user.getPassword())) {
			model.addAttribute("frmCtrlRePsw", "form-control form-control-lg is-invalid");
			model.addAttribute("fbRePsw", "invalid-feedback");
			model.addAttribute("contentFBRePsw", "Mật khẩu không khớp");
			err = true;
		} else {
			model.addAttribute("frmCtrlRePsw", "form-control form-control-lg");

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
			return "register";
		}
		else
		{
			model.addAttribute("frmCtrlCaptcha", "form-control form-control-lg");
		}

		if (err == false && verify) {
			model.addAttribute("message", "Bạn đã nhập thành công");
			String salt = PasswordUltils.getSalt(30);
			user.setSalt(salt);
			String yourPass = "";
			try {
				yourPass = PasswordUltils.generateSecurePassword(user.getPassword(), salt);
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setPassword(yourPass);
			if(user.getSex()==0)
			{
				user.setAvatar("men.jpg");
			}
			else
			{
				user.setAvatar("women.jpg");
			}
			if (saveAccount(user) == 0) {
				ss.setAttribute("userVerify", user);
				Verify verifyCode= new Verify();
				verifyCode.codeVerify=PasswordUltils.generateRandomPassword();
				mailer.sendVerifyCode(verifyCode.codeVerify, user.getEmail());
				return "redirect:/user/verify.htm";
			} else {
				model.addAttribute("message", "Tạo tài khoản thất bại");
			}
		}
		return "register";
	}

}
