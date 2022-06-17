package jewelry.controller;

import java.io.File;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.catalina.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jewelry.bean.UploadFile;
import jewelry.entity.OrderEntity;
import jewelry.entity.UserEntity;
import jewelry.ultils.PasswordUltils;

@Controller

public class ChangePassController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;

	@Transactional
	@RequestMapping(value = "{role}/changePass", method = RequestMethod.GET)
	public String profile(ModelMap model, HttpSession session,@PathVariable("role")String role) {
		
		model.addAttribute("frmCtrlPsw", "form-control form-control-lg");
	
		model.addAttribute("frmCtrlNewPsw", "form-control form-control-lg");
	
		model.addAttribute("frmCtrlReNewPsw", "form-control form-control-lg");
		model.addAttribute("frmCtrlCaptcha", "form-control form-control-lg");
		if(role.equals("user"))
		return "changePass";
		else
			return "changePassAdmin";
	}
	@RequestMapping(value = "{role}/changePass/{message}", method = RequestMethod.GET)
	public String loginError(ModelMap model, @PathVariable("message") String message,HttpSession session,@PathVariable("role")String role) {
		String view="";
		if(role.equals("user"))
		{
		
			view="changePass";
		}
		else
		{
		
			view="changePassAdmin";
		}
		if(message.equals("changePassSuccess"))
		{
			model.addAttribute("frmCtrl","col-md-12 mb-4 alert alert-success");
			model.addAttribute("warning", "");
			model.addAttribute("message", "Đổi mật khẩu thành công");
		}
		model.addAttribute("frmCtrlPsw", "form-control form-control-lg");
		model.addAttribute("frmCtrlNewPsw", "form-control form-control-lg");
		model.addAttribute("frmCtrlReNewPsw", "form-control form-control-lg");
		model.addAttribute("frmCtrlCaptcha", "form-control form-control-lg");
		return view;

	}
	@Transactional
	@RequestMapping(value = "{role}/changePass/validate", method = RequestMethod.POST)
	public String validate(ModelMap model, @Validated @ModelAttribute("user") UserEntity user, BindingResult errors,
			@PathVariable("role") String role,
			@RequestParam("newPsw") String newPsw,
			@RequestParam("psw") String psw,
			@RequestParam("reNewPsw") String reNewPsw,
			HttpSession ss, HttpServletResponse response,
			HttpServletRequest request) {
		UserEntity userE;
		String view="";
		if(role.equals("user"))
		{
			userE= (UserEntity) ss.getAttribute("user");
			view="changePass";
		}
		else
		{
			userE= (UserEntity) ss.getAttribute("admin");
			view="changePassAdmin";
		}
	
		boolean err = false;
		boolean isCorrect=false;
		boolean matchPass=true;
		if (psw.isBlank()) {
			model.addAttribute("frmCtrlPsw", "form-control form-control-lg is-invalid");
			model.addAttribute("fbPsw", "invalid-feedback");
			model.addAttribute("contentFBPsw", "Không được để trống mật khẩu");
			err = true;
		}

		else if (!psw.isBlank()) {
			
			try {
				isCorrect = PasswordUltils.verifyUserPassword(psw, userE.getPassword(), userE.getSalt());
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!isCorrect) {
				model.addAttribute("frmCtrlPsw", "form-control form-control-lg is-invalid");
				model.addAttribute("fbPsw", "invalid-feedback");
				model.addAttribute("contentFBPsw",
						"Mật khẩu không đúng");
				err = true;
			} else {
				model.addAttribute("frmCtrlPsw", "form-control form-control-lg");
				
			}
		}

		if (newPsw.isBlank()) {
			model.addAttribute("frmCtrlNewPsw", "form-control form-control-lg is-invalid");
			model.addAttribute("fbNewPsw", "invalid-feedback");
			model.addAttribute("contentFBNewPsw", "Không được để trống mật khẩu mới");
			model.addAttribute("frmCtrlReNewPsw", "form-control form-control-lg");
			model.addAttribute("frmCtrlCaptcha", "form-control form-control-lg");
			err = true;
			
		} 
		else if (!newPsw.isBlank()) {
			String regexPass = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
			Pattern patternPass = Pattern.compile(regexPass);
			
			System.out.print("New psw: "+newPsw);
			Matcher matcher = patternPass.matcher(newPsw);
			matchPass = matcher.matches();
			if (!matchPass) {
				model.addAttribute("frmCtrlNewPsw", "form-control form-control-lg is-invalid");
				model.addAttribute("fbNewPsw", "invalid-feedback");
				model.addAttribute("contentFBNewPsw",
						"Mật khẩu phải chứa ít nhất 8 ký tự, bao gồm chữ số, chữ cái và ký tự đặc biệt");
				err = true;
			} else {
				model.addAttribute("frmCtrlNewPsw", "form-control form-control-lg");
				
			}
		}
		if (matchPass && !newPsw.equals(reNewPsw)) {
			model.addAttribute("frmCtrlNewPsw", "form-control form-control-lg is-invalid");
			model.addAttribute("fbNewPsw", "invalid-feedback");
			model.addAttribute("contentFBNewPsw", "Mật khẩu mới không khớp");
			model.addAttribute("frmCtrlReNewPsw", "form-control form-control-lg");
			err = true;
		} else {
			model.addAttribute("frmCtrlReNewPsw", "form-control form-control-lg");
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
			err=true;
			return view;
		}
		else
		{
			model.addAttribute("frmCtrlCaptcha", "form-control form-control-lg");
		}

		if (err == false && verify) {
			String salt = PasswordUltils.getSalt(30);
			userE.setSalt(salt);
			String yourPass = "";
			try {
				yourPass = PasswordUltils.generateSecurePassword(newPsw, salt);
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userE.setPassword(yourPass);
			Session session=factory.openSession();
					Transaction t = session.beginTransaction();
					UserEntity userTemp = (UserEntity) session.get(UserEntity.class, userE.getId());
			
			try {
				userTemp.setPassword(userE.getPassword());
				userTemp.setSalt(userE.getSalt());
				session.update(userTemp);
				
				t.commit();

				model.addAttribute("message","Đổi mật khẩu thành công!");
				model.addAttribute("back","Quay về trang chủ");
				model.addAttribute("url","home.htm");
				return "user/success";
			
		
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Cập nhật thất bại!");
			} finally {
				session.close();
			}
		}
		
		return view;
	}
	


}