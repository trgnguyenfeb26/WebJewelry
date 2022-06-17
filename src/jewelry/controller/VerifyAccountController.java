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
import jewelry.bean.Verify;
import jewelry.entity.UserEntity;
import jewelry.ultils.PasswordUltils;

@Controller
public class VerifyAccountController {
	@Autowired
	SessionFactory factory;

	@Transactional
	@RequestMapping(value = "user/verify", method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("frmCtrlVerify", "form-control form-control-lg");
		return "verify";
	}

	@RequestMapping(value = "user/verify", method = RequestMethod.POST)
	public String verify(ModelMap model, @RequestParam("verify") String verify, HttpSession session) {
		if (verify.equals(Verify.codeVerify)) {
			UserEntity userE = (UserEntity) session.getAttribute("userVerify");
			Session session2 = factory.openSession();
			Transaction t = session2.beginTransaction();
			UserEntity userTemp = (UserEntity) session2.get(UserEntity.class, userE.getId());

			try {
				userTemp.setIsVerify(1);
				session2.update(userTemp);
				t.commit();
				model.addAttribute("message", "Cập nhật thành công!");
				// Cập nhật thông tin phiên hiện tại
				session.setAttribute("user", userTemp);
				return "redirect:/home.htm";

			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Cập nhật thất bại!");
			} finally {
				session2.close();
			}

		} else {
			model.addAttribute("frmCtrlVerify", "form-control form-control-lg is-invalid");
			model.addAttribute("fbVerify", "invalid-feedback");
			model.addAttribute("contentFBVerify", "Mã xác thực không đúng");
		}
		return "verify";
	}

}
