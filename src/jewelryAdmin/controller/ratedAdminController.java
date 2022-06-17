package jewelryAdmin.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

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

import jewelry.Dao.ratedDao;
import jewelry.entity.ProductEntity;
import jewelry.entity.ratedEntity;
import jewelry.bean.combobox;
import jewelry.bean.ratedFeedback;
import jewelry.bean.ratedSearch;
import jewelry.bean.stats;

//import ptithcm.model.SearchBody;

@Controller
@RequestMapping("admin/rated")

public class ratedAdminController {
	@Autowired
	ratedDao ratedDao;
	
	private int maxPage =10;
    private int pageSize =10;
    private int Page =0;
    private ratedSearch rad = new ratedSearch();
    
	private String trim (String x) {
		if (x == null)
			return null;
		if(x.equals(null) ) 
			return null;
		else return x.trim();
	}
	private String get(List<ratedEntity> rt,int vt ,int sl) {
		if (vt<sl) {
			return rt.get(vt).getFeedback();
		}
		return null;
	}
	@RequestMapping()
	public String rated(ModelMap model,HttpServletRequest request) {
        Page =0;
		return "redirect:/admin/rated.htm?page=" +Page;
	}
	@RequestMapping(params = { "page" })
	public String rated2(ModelMap model,HttpServletRequest request,@RequestParam("page") int page) {
		
		ArrayList<combobox> cmb = new ArrayList<combobox>();
		{
			combobox x = new combobox();
			x.setId(2);x.setLabel("Đã Phản hồi");
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
		ratedFeedback st = new ratedFeedback();
		Page = page;
		List<ratedEntity> rt = ratedDao.listRated(rad.getId());
		int i = page*pageSize;
		int sl = ratedDao.getCount(rad.getId());
		st.setFb0(get(rt,i,sl));
		st.setFb1(get(rt,i+1,sl));
		st.setFb2(get(rt,i+2,sl));
		st.setFb3(get(rt,i+3,sl));
		st.setFb4(get(rt,i+4,sl));
		st.setFb5(get(rt,i+5,sl));
		st.setFb6(get(rt,i+6,sl));
		st.setFb7(get(rt,i+7,sl));
		st.setFb8(get(rt,i+8,sl));
		st.setFb9(get(rt,i+9,sl));	
		PagedListHolder pagedListHolder = new PagedListHolder(rt);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(maxPage);
		pagedListHolder.setPageSize(pageSize);
		model.addAttribute("pagedLink", "/JEWELRY6/admin/rated.htm?page=");
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("cmb", cmb);
		model.addAttribute("rad", rad);
		model.addAttribute("st",st);
		return "admin/rated/rated";

	}
	@RequestMapping(value ="fb")
	public String fb(ModelMap model,HttpServletRequest request,@ModelAttribute("st") ratedFeedback st) {

		ArrayList <String> ar =new ArrayList<String>();
		ar.add(trim(st.getFb0()));
		ar.add(trim(st.getFb1()));
		ar.add(trim(st.getFb2()));
		ar.add(trim(st.getFb3()));
		ar.add(trim(st.getFb4()));
		ar.add(trim(st.getFb5()));
		ar.add(trim(st.getFb6()));
		ar.add(trim(st.getFb7()));
		ar.add(trim(st.getFb8()));
		ar.add(trim(st.getFb9()));

		List <ratedEntity> rated = ratedDao.listRated(rad.getId());
		int sl = ratedDao.getCount(rad.getId());
		int vt =pageSize;
		if((Page+1)*pageSize > sl)
			vt = sl - Page*pageSize;		
		for(int i=0;i<vt;i++) {
			ratedEntity rt = rated.get(Page*pageSize +i);
			rt.setFeedback(ar.get(i));
			if(ratedDao.update(rt) == false) {
				model.addAttribute("err", "luu-that-bai");
				return "redirect:/admin/rated.htm?page=" +Page;
			}
		}
		model.addAttribute("err", "luu-thanh-cong");
	   return "redirect:/admin/rated.htm?page=" +Page;

	}
	@RequestMapping(params = { "search" })
	public String stats2(ModelMap model,HttpServletRequest request,@ModelAttribute("rated") ratedSearch rt) { 
	    rad.setId(rt.getId());
		return "redirect:/admin/rated.htm?page="+Page;
		
	}
	
}
