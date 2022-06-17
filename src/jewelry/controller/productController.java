package jewelry.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.support.PagedListHolder;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jewelry.entity.UserEntity;
import jewelry.entity.ratedEntity;

import jewelry.entity.ProductEntity;
import jewelry.Dao.productDao;
import jewelry.Dao.ratedDao;

@Controller
@RequestMapping("productDetail")

public class productController {
	
	@Autowired
	productDao productDao;
	@Autowired
	ratedDao ratedDao;
	private String name ="";
	private String mess ="";
	
	@RequestMapping(value ="{id}.htm")
	public String index(ModelMap model,HttpSession session,@PathVariable(value ="id") int id) {
		
		model.addAttribute("u",(UserEntity)session.getAttribute("user"));
		model.addAttribute("pd",productDao.getProduct(id));
		List<ratedEntity> cmt = ratedDao.getListRated(id);
		ArrayList<Integer> ar = new ArrayList<Integer>();
		for(ratedEntity x :cmt) {
			if(ratedDao.checkUser(x.getAccount().getId(), id) == true)
				ar.add(1);
			else
			  ar.add(0);
		}
		model.addAttribute("ar",ar);
		model.addAttribute("cmt",cmt);
		model.addAttribute("name",name);
		model.addAttribute("mess",mess);	
		return "user/productDetail";
	}
	@RequestMapping(value ="cmt/{id}.htm")
	public String cmt(ModelMap model,HttpSession session,@PathVariable(value ="id") int id,@RequestParam("comment") String comment) {
		UserEntity u = (UserEntity)session.getAttribute("user")	;
		if(u == null) {
			return "redirect:/login.htm";
		}
	    ratedEntity r = new ratedEntity();
	    r.setAccount(u);
	    r.setProduct(productDao.getProduct(id));
	    r.setContent(comment);
	    Date x = new Date();
	    r.setDate(x);
	    r.setFeedback("");
	    boolean kt = ratedDao.insert(r);
		
		return "redirect:/productDetail/"+id+".htm";
	}
	
}