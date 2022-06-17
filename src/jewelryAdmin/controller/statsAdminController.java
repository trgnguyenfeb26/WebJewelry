package jewelryAdmin.controller;
import java.time.LocalDateTime;
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
import jewelry.Dao.statsDao;
import jewelry.Dao.productDao;
import jewelry.bean.combobox;
import jewelry.bean.stats;
import jewelry.entity.ProductEntity;

;

//import ptithcm.model.SearchBody;


@Controller
@RequestMapping("admin/stats")

public class statsAdminController {
	
	@Autowired
	statsDao statsDao;
	@Autowired
	productDao productDao;
	
	
	private int maxPage =100;
    private int pageSize =10;
    private int Page =0;
    private stats stats = new stats();
	@RequestMapping()
	public String account(ModelMap model) {
		Page =0;
		return "redirect:/admin/stats.htm?page=0";
	}
	@RequestMapping(params = { "page" })
	public String stats(ModelMap model,HttpServletRequest request,@RequestParam("page") int page) {
		Page =page;
		if(stats.getYear() ==0)
			stats.setMonth(0);
		List<Object[]> list = statsDao.listStats(stats.getType(),stats.getYear(),stats.getMonth());
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(maxPage);	
		pagedListHolder.setPageSize(pageSize);
		model.addAttribute("pagedLink", "/adorn/admin/stats.htm?page=");
		model.addAttribute("pagedListHolder", pagedListHolder);
		ArrayList <String> pd = new ArrayList<String>() ;
		for(Object[] o :list) {
			ProductEntity p = productDao.getProduct((int)o[0]);
			pd.add(p.getName());
		}
		ArrayList<combobox> cmbYear = new ArrayList<combobox>();
		ArrayList<combobox> cmbMonth = new ArrayList<combobox>();
		ArrayList<combobox> cmbType = new ArrayList<combobox>();
		{
			combobox z = new combobox();
			z.setId(2);z.setLabel("Sản phẩm đã bán");
			cmbType.add(z);
		}
		{
			combobox z = new combobox();
			z.setId(1);z.setLabel("Doanh số");
			cmbType.add(z);
		}
		{
			combobox x = new combobox();
			x.setId(0);x.setLabel("Tất cả");
			cmbYear.add(x);
		}
		LocalDateTime date = LocalDateTime.now();
		for(int i =2019;i<=date.getYear();i++) {
			String v1 ="";
			v1 = String.valueOf(i);
			combobox v = new combobox();
			v.setId(i);
			v.setLabel(v1);
			cmbYear.add(v);
		}
		{
			combobox x = new combobox();
			x.setId(0);x.setLabel("Tất cả");
			cmbMonth.add(x);
		}
		for(int i =1;i<=12;i++) {
			String v1 ="";
			v1 = String.valueOf(i);
			combobox v = new combobox();
			v.setId(i);
			v.setLabel(v1);
			cmbMonth.add(v);
		}
		
		model.addAttribute("pd", pd);
		model.addAttribute("stats", stats);
		model.addAttribute("cmbYear", cmbYear);
		model.addAttribute("cmbMonth", cmbMonth);
		model.addAttribute("cmbType", cmbType);
		return "admin/stats/stats";
	}
	@RequestMapping(params = { "stats" })
	public String stats2(ModelMap model,HttpServletRequest request,@ModelAttribute("stats") stats st) {
		stats.setType(st.getType());
		stats.setYear(st.getYear());
		stats.setMonth(st.getMonth());
		return "redirect:/admin/stats.htm?page="+Page;
		
	}
}