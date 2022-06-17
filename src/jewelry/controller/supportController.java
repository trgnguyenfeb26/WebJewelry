package jewelry.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.constraints.Past;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import jewelry.Dao.supportDao;
import jewelry.entity.UserEntity;
import jewelry.entity.supportEntity;

@Controller
@RequestMapping("support")
public class supportController {
	
	@Autowired
	supportDao supportDao;
	
	private String mess="";
	
	@RequestMapping()
	public String support(ModelMap model,HttpSession session) {
		UserEntity u = (UserEntity)session.getAttribute("user");
		if(u == null) {
			return "redirect:/login.htm";
		}
	//	System.out.println("user "+user.getEmail()+" - "+user.getPassword());
		model.addAttribute("sp", new supportEntity());
		model.addAttribute("mess", "");
		return "user/support" ;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String setSupport(ModelMap model,@ModelAttribute("sp") supportEntity sp,HttpSession session) {
		UserEntity u = (UserEntity)session.getAttribute("user");
		if(u == null) {
			return "redirect:/login.htm";
		}
		if(sp.getContent() == null || sp.getContent().equals("")) {
			model.addAttribute("mess", "Nội dung không thể trống");
			return "user/support" ;
		}
		sp.setFeedback(false);
		sp.setAccount(u);
		Date date = new Date();
		sp.setDate(date);
		boolean kt = supportDao.insert(sp);
		if(kt)
			model.addAttribute("mess", "Gửi thành công");
		else
			model.addAttribute("mess", "Gửi thất bại");
		return "user/support" ;
	}
}
