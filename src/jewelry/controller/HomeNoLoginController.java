package jewelry.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.tomcat.util.bcel.Const;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jewelry.entity.OrderItemEntity;
import jewelry.entity.CategoryEntity;
import jewelry.entity.OrderEntity;
import jewelry.entity.ProductEntity;
import jewelry.entity.UserEntity;
import jewelry.model.Cart;
import jewelry.model.CartItem;

@Transactional
@Controller()
public class HomeNoLoginController {
	@Autowired
	SessionFactory factory;

	public static int MAX_RESULTS = 8;

	@RequestMapping("nologin/home")
	public String searchByCategoryId(ModelMap model,
			@RequestParam(value = "categoryId", required = false, defaultValue = "-1") int categoryId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {

		List<ProductEntity> productEntities = categoryId != -1 ? getProducts(categoryId) : getProducts();

		PagedListHolder pagedListHolder = new PagedListHolder<>(productEntities);
		pagedListHolder.setPage(page - 1);
		pagedListHolder.setPageSize(MAX_RESULTS);

		model.addAttribute("pageListHolder", pagedListHolder);

		return "home";
	}
	@RequestMapping(value="nologin/home", params="btnsearch")
	public String  searchPro(HttpServletRequest request,ModelMap model,
			@RequestParam(value = "categoryId", required = false, defaultValue = "-1") int categoryId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		List<ProductEntity> productEntities = this.searchProducts(request.getParameter("searchInput"));
		PagedListHolder pagedListHolder = new PagedListHolder<>(productEntities);
		
		pagedListHolder.setPage(page - 1);
		pagedListHolder.setPageSize(MAX_RESULTS);

		model.addAttribute("pageListHolder", pagedListHolder);
		return  "home";
	}

	public List<ProductEntity> searchProducts(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity where name LIKE :name";
		Query query = session.createQuery(hql);
		query.setParameter("name","%" + name + "%");
		return query.list();
	}


	@ModelAttribute("categories")
	public List<CategoryEntity> getCategories() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity";
		Query query = session.createQuery(hql);
		List<CategoryEntity> list = query.list();
		return list;
	}

	public List<ProductEntity> getProducts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity";
		Query query = session.createQuery(hql);
		return query.list();
	}

	public List<ProductEntity> getProducts(int categoryId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity AS p WHERE p.category.id = :categoryId";
		Query query = session.createQuery(hql);
		query.setParameter("categoryId", categoryId);
		return query.list();
	}

	private int isExisting(int id, List<CartItem> cartItem) {
		for (int i = 0; i < cartItem.size(); i++) {
			if (cartItem.get(i).getProduct().getId() == id) {
				return i;
			}
		}
		return -1;
	}

	public ProductEntity getProduct(int id) {
		return (ProductEntity) factory.getCurrentSession().get(ProductEntity.class, id);
	}

}
