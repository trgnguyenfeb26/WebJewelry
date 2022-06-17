package jewelryAdmin.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.tomcat.jni.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jewelry.bean.UploadFile;
import jewelry.entity.CategoryEntity;
import jewelry.entity.OrderEntity;
import jewelry.entity.ProductEntity;
import jewelry.entity.UserEntity;

@Transactional
@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	@Qualifier("uploadfile")
	UploadFile baseUploadFile;

	// CATEGORY----------------------------------------------------------------
	@RequestMapping("category")
	public String categories(ModelMap model, HttpServletRequest request) {
		List<CategoryEntity> categories = this.getCategories();
		PagedListHolder pagedListHolder = new PagedListHolder<>(categories);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/category/index";
	}
	// search
	public List<CategoryEntity> getCategoryByName(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity as c where c.cate_name like :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + name + "%");
		List<CategoryEntity> list = query.list();
		return list;
	}

	@RequestMapping(value = "category", params = "btnsearch")
	public String seachCategory(HttpServletRequest request, ModelMap model, HttpSession session) {
		List<CategoryEntity> categories = this.getCategoryByName(request.getParameter("searchInput"));
		PagedListHolder pagedListHolder = new PagedListHolder<>(categories);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/category/index";
	}
	//edit
	@RequestMapping("category/edit/{id}")
	public String editCategory(@PathVariable String id, ModelMap model) {
		model.addAttribute("category", getCategory(Integer.parseInt(id)));
		return "admin/category/edit";
	}

	@RequestMapping(value = "category/edit", method = RequestMethod.POST)
	public String editCategory(ModelMap model, @ModelAttribute("category") CategoryEntity category) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(category);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công");
			model.addAttribute("url", "admin/category.htm");
			return "admin/success";
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại");
			model.addAttribute("category", getCategory(category.getId()));
		} finally {
			session.close();
		}

		return "admin/category/edit";
	}
	//add
	@RequestMapping("category/add")
	public String addCategory(ModelMap model) {
		model.addAttribute("category", new CategoryEntity());
		return "admin/category/add";
	}

	@RequestMapping(value = "category/add", method = RequestMethod.POST)
	public String addCategory(ModelMap model, @ModelAttribute("category") CategoryEntity category) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(category);
			t.commit();
			model.addAttribute("message", "Thêm thành công");
			model.addAttribute("url", "admin/category.htm");
			return "admin/success";
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm thất bại");
			model.addAttribute("category", new CategoryEntity());
		} finally {
			session.close();
		}

		return "admin/category/add";
	}
	//delete
	@RequestMapping(value="category/delete/{id}",method=RequestMethod.GET)
	public String deleteCategory(ModelMap model,@PathVariable("id")String id)
	{
		model.addAttribute("id",id);
		return "admin/category/confirm";
	}
	@RequestMapping(value = "category/delete/{id}", method = RequestMethod.POST)
	public String deleteCategory(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		CategoryEntity category = (CategoryEntity) session.get(CategoryEntity.class, id);
		
		try {
			session.delete(category);
			t.commit();
			model.addAttribute("message", "Xóa thành công");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			t.rollback();
			model.addAttribute("message", "Không thể xóa nhóm hàng đã có sản phẩm");
		} finally {
			session.close();
		}
		model.addAttribute("url", "admin/category.htm");
		return "admin/success";
	}

	// USER-----------------------------------------------------------------------------
	@RequestMapping("user")
	public String users(ModelMap model, HttpServletRequest request) {
		List<UserEntity> users = this.getUsers();
		PagedListHolder pagedListHolder = new PagedListHolder<>(users);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(6);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/user/index";
	}
	//search
	@RequestMapping(value = "user", params = "btnsearch")
	public String seachUser(HttpServletRequest request, ModelMap model, HttpSession session) {
		List<UserEntity> users = this.getUserByName(request.getParameter("searchInput"));
		PagedListHolder pagedListHolder = new PagedListHolder<>(users);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(6);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/user/index";
	}
	public List<UserEntity> getUserByName(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserEntity as u where u.StatusDelete=:ttx and u.role_id=:role and u.firstname=:name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		query.setParameter("ttx", 0);
		query.setParameter("role", 0);
		List<UserEntity> list = query.list();
		return list;
	}

	//delete
	@RequestMapping(value="user/delete/{id}",method=RequestMethod.GET)
	public String deleteUser(ModelMap model,@PathVariable("id")String id)
	{
		model.addAttribute("id",id);
		return "admin/user/confirm";
	}
	
	@RequestMapping(value = "user/delete/{id}", method = RequestMethod.POST)
	public String deleteUser(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		UserEntity user = (UserEntity) session.get(UserEntity.class, id);
		user.setStatusDelete(1);
		try {

			session.update(user);
			t.commit();

			model.addAttribute("message", "Xóa thành công");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		} finally {
			session.close();
		}
		model.addAttribute("url", "admin/user.htm");
		return "admin/success";
	}
	// PRODUCT------------------------------------------------------------
	@RequestMapping("product")
	public String products(ModelMap model, HttpServletRequest request) {
		List<ProductEntity> products = this.getProducts();
		PagedListHolder pagedListHolder = new PagedListHolder<>(products);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(6);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/product/index";
	}

	@RequestMapping(value = "product", params = "btnsearch")
	public String seachProduct(HttpServletRequest request, ModelMap model, HttpSession session) {
		List<OrderEntity> products = this.getProductByName(request.getParameter("searchInput"));
		PagedListHolder pagedListHolder = new PagedListHolder<>(products);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(6);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/product/index";
	}

	public List<OrderEntity> getProductByName(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity as c where c.name like :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + name + "%");
		List<OrderEntity> list = query.list();
		return list;
	}

	@RequestMapping("product/edit/{id}")
	public String product(@PathVariable String id, ModelMap model, HttpSession session) {
		model.addAttribute("product", getProduct(Integer.parseInt(id)));
		return "admin/product/edit";
	}

	public Integer updateProduct(ProductEntity p) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(p);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "product/edit/{id}", method = RequestMethod.POST)
	public String editProduct(ModelMap model, @ModelAttribute("product") ProductEntity product,
			@RequestParam("file") MultipartFile file,
			@PathVariable("id")int id) {
		if (file.getSize()!=0) {
			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
			String fileName = date + file.getOriginalFilename();
			String photoPath =	baseUploadFile.getBasePath() + File.separator + fileName;
			try {
				file.transferTo(new File(photoPath));
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IllegalStateException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			product.setImage(fileName);
			System.out.print("file.name(): "+file.getSize());
		}
		else
		{
			ProductEntity prod= getProduct(id);
			product.setImage(prod.getImage());
		}
		
		int test = updateProduct(product);
		if (test == 1) {

			model.addAttribute("message", "Cập nhật thành công");
			model.addAttribute("url", "admin/product.htm");
			return "admin/success";
		} else {
			System.out.println("that bai");
			model.addAttribute("message", "Cập nhật thất bại");
			model.addAttribute("category", getProduct(product.getId()));
		}
		return "admin/product/edit";
	}
	//add
	@RequestMapping("product/add")
	public String addProduct(ModelMap model, HttpSession ss) {
		ProductEntity product = new ProductEntity();
		model.addAttribute("product", product);
		List<CategoryEntity> categories = this.getCategories();
		model.addAttribute("categories", categories);
		return "admin/product/add";
	}

	@RequestMapping(value = "product/add", method = RequestMethod.POST)
	public String addProduct(ModelMap model, @ModelAttribute("product") ProductEntity product,
			@RequestParam("file") MultipartFile file) {
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
		String fileName = date + file.getOriginalFilename();
		String photoPath = baseUploadFile.getBasePath() + File.separator  + fileName;
		try {
			file.transferTo(new File(photoPath));
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IllegalStateException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		product.setImage(fileName);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(product);
			t.commit();
			
			model.addAttribute("message", "Thêm thành công");
			model.addAttribute("url", "admin/product.htm");
			return "admin/success";
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();

			model.addAttribute("message", "Thêm thất bại");
			model.addAttribute("product", new ProductEntity());
		} finally {
			session.close();
		}

		return "admin/product/add";
	}
	
	@RequestMapping(value="product/delete/{id}",method=RequestMethod.GET)
	public String deleteProduct(ModelMap model,@PathVariable("id")String id)
	{
		model.addAttribute("id",id);
		return "admin/product/confirm";
	}
	

	@RequestMapping(value = "product/delete/{id}", method = RequestMethod.POST)
	public String deleteProduct(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		ProductEntity product = (ProductEntity) session.get(ProductEntity.class, id);
		
		try {

			session.delete(product);
			t.commit();

			model.addAttribute("message", "Xoá thành công");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			t.rollback();
			model.addAttribute("message", "Không thể xóa sản phẩm đã có trong đơn hàng");
		} finally {
			session.close();
		}
		model.addAttribute("url", "admin/product.htm");
		return "admin/success";
	}

	// MODEL ATTRIBUTES
	@ModelAttribute("categories")
	public List<CategoryEntity> getCategories() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity";
		Query query = session.createQuery(hql);
		List<CategoryEntity> list = query.list();
		return list;
	}

	// FUNCTIONS
	public CategoryEntity getCategory(int id) {
		return (CategoryEntity) factory.getCurrentSession().get(CategoryEntity.class, id);
	}

	public List<UserEntity> getUsers() {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserEntity as u where u.StatusDelete=:ttx and u.role_id=:role";
		Query query = session.createQuery(hql);
		query.setParameter("ttx", 0);
		query.setParameter("role", 0);
		List<UserEntity> list = query.list();
		return list;
	}

	public UserEntity getUser(int id) {
		return (UserEntity) factory.getCurrentSession().get(UserEntity.class, id);
	}

	public List<ProductEntity> getProducts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity";
		Query query = session.createQuery(hql);
		List<ProductEntity> list = query.list();
		return list;
	}

	public ProductEntity getProduct(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ProductEntity list = (ProductEntity) query.list().get(0);
		return list;
	}

	// Cart
	@RequestMapping("order")
	public String donhang(ModelMap model, HttpSession session2, HttpServletRequest request) {
		List<OrderEntity> orders = this.getOrders(session2);
		PagedListHolder pagedListHolder = new PagedListHolder<>(orders);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/order/order";
	}

	public List<OrderEntity> getOrders(HttpSession session2) {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity ORDER BY id DESC";
		Query query = session.createQuery(hql);
		List<OrderEntity> list = query.list();
		return list;
	}

	public List<OrderEntity> getOrderByDate(Date date, HttpSession session2) {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity as c where c.buyDate=:date1";
		Query query = session.createQuery(hql);
		query.setParameter("date1", date);
		List<OrderEntity> list = query.list();
		return list;
	}

	@RequestMapping(value = "order", params = "btnsearch")
	public String seachOrder(HttpServletRequest request, ModelMap model, HttpSession session) {
		Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("searchInput"));
			System.out.print("Date: " + date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<OrderEntity> orders = this.getOrderByDate(date, session);
		PagedListHolder pagedListHolder = new PagedListHolder<>(orders);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/order/order";
	}

	@RequestMapping(value = "order/detail/{id}")
	public String getCTDDH(@PathVariable int id, ModelMap model) {
		model.addAttribute("order", factory.getCurrentSession().get(OrderEntity.class, id));
		return "admin/order/orderDetail";
	}

	public Integer updateCartStatus(OrderEntity cart) {
		Session session = factory.openSession();
		org.hibernate.Transaction t = session.beginTransaction();
		try {
			session.update(cart);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "order/{id}/huy")
	public String huy(@PathVariable int id, ModelMap model) {
		OrderEntity cart = (OrderEntity) factory.getCurrentSession().get(OrderEntity.class, id);
		cart.setStatus(-1);
		Date now = new Date();
		cart.setDeliveryCancelDay(now);
		Integer temp = this.updateCartStatus(cart);
		if (temp != 0) {
			model.addAttribute("message", "Hủy thành công");

		} else {
			model.addAttribute("message", "Hủy thất bại!");
		}

		model.addAttribute("order", factory.getCurrentSession().get(OrderEntity.class, id));
		model.addAttribute("message","Huỷ đơn hàng thành công");
		model.addAttribute("url","admin/order.htm");
		return "admin/success";
	}

	@RequestMapping(value = "order/{id}/danggiaohang")
	public String danggiaohang(@PathVariable int id, ModelMap model) {
		OrderEntity cart = (OrderEntity) factory.getCurrentSession().get(OrderEntity.class, id);
		cart.setStatus(2);
		Integer temp = this.updateCartStatus(cart);
		if (temp != 0) {
			model.addAttribute("message", "Update thất bại");
		} else {
			model.addAttribute("message", "Update thành công!");

		}

		model.addAttribute("order", factory.getCurrentSession().get(OrderEntity.class, id));
		model.addAttribute("message","Đang giao hàng cho khách");
		model.addAttribute("url","admin/order.htm");
		return "admin/success";
	}

	@RequestMapping(value = "order/{id}/giaohang")
	public String dagiaohang(@PathVariable int id, ModelMap model, HttpServletResponse response,
			HttpServletRequest request) {
		OrderEntity cart = (OrderEntity) factory.getCurrentSession().get(OrderEntity.class, id);
		cart.setStatus(1);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		Date now = new Date();
		cart.setDeliveryCancelDay(now);
		Integer temp = this.updateCartStatus(cart);
		if (temp != 0) {
			model.addAttribute("message", "Update thất bại");
		} else {
			model.addAttribute("message", "Update thành công!");
		}

		model.addAttribute("order", factory.getCurrentSession().get(OrderEntity.class, id));
		model.addAttribute("message","Đã giao hàng cho khách");
		model.addAttribute("url","admin/order.htm");
		return "admin/success";
	}
}
