package jewelryAdmin.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jewelry.bean.Mailer;
import jewelry.Dao.supportDao;
import jewelry.entity.ProductEntity;
import jewelry.entity.supportEntity;
import jewelry.bean.combobox;
import jewelry.bean.ratedSearch;

;

//import ptithcm.model.SearchBody;


@Controller
@RequestMapping("admin/support")

public class supportAdminController {
	

	@Autowired
	supportDao supportDao;
	
	private String acEmail ="";
	private int Page =0;
	private int maxPage =100;
    private int pageSize =10;
    private ratedSearch rad = new ratedSearch();
    
	@RequestMapping()
	public String account(ModelMap model) {
		Page =0;
		return "redirect:/admin/support.htm?page=0";
	}
	@RequestMapping(params = { "page" })
	public String supportPage(ModelMap model,HttpServletRequest request,@RequestParam("page") int page) {
		
		ArrayList<combobox> cmb = new ArrayList<combobox>();
		{
			combobox x = new combobox();
			x.setId(2);x.setLabel("Đã phản hồi");
			cmb.add(x);
		}
		{
			combobox z = new combobox();
			z.setId(1);z.setLabel("Chưa phản hồi");
			cmb.add(z);
		}
		{
			combobox z = new combobox();
			z.setId(0);z.setLabel("Tất cả");
			cmb.add(z);
		}
		
		Page = page;
		PagedListHolder pagedListHolder = new PagedListHolder(supportDao.listSupport(rad.getId()));
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(maxPage);
		pagedListHolder.setPageSize(pageSize);
		model.addAttribute("pagedLink", "/JEWELRY6/admin/support.htm?page=");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("cmb", cmb);
		model.addAttribute("rad", rad);
		return "admin/support/support";

	}
	@RequestMapping(value ="{id}.htm",params ="feedback")
	public String edit(ModelMap model,@PathVariable(value ="id") int id) {
		supportEntity me = supportDao.getSupport(id);
		acEmail = me.getAccount().getEmail();
		model.addAttribute("sp", me);
		return "admin/support/emailSupport";
	}
	
	@Autowired 
	Mailer mail;
	@RequestMapping(value ="{id}.htm",params ="saveEmail")
	public String saveEmail(ModelMap model,@PathVariable(value ="id") int id,
			@RequestParam("subject") String subject,
			@RequestParam("body") String body) {		
		try {
			mail.sendSupport(acEmail,subject,body);
			supportEntity me = supportDao.getSupport(id);
			me.setFeedback(true);
			supportDao.update(me);
			model.addAttribute("message", "Gửi email thành công !");
			return "redirect:/admin/support.htm?page="+Page;
		}
		catch(Exception ex) {
			   System.out.println("err : "+ex);
				model.addAttribute("message", "Gửi email thất bại!");
				return "redirect:/admin/support/"+id+".htm?feedback";
		}
	}
	
	@RequestMapping(params = { "search" })
	public String stats2(ModelMap model,HttpServletRequest request,@ModelAttribute("rated") ratedSearch rt) { 
	    rad.setId(rt.getId());
		return "redirect:/admin/support.htm?page="+Page;
		
	}
}

