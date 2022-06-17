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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jewelry.entity.OrderItemEntity;
import jewelry.bean.Mailer;
import jewelry.entity.CategoryEntity;
import jewelry.entity.OrderEntity;
import jewelry.entity.ProductEntity;
import jewelry.entity.UserEntity;
import jewelry.model.Cart;
import jewelry.model.CartItem;

@Transactional
@Controller()
public class HomeController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	public static int MAX_RESULTS = 8;

	@RequestMapping("home")
	public String searchByCategoryId(ModelMap model,
			@RequestParam(value = "categoryId", required = false, defaultValue = "-1") int categoryId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {

		List<ProductEntity> productEntities = categoryId != -1 ? getProducts(categoryId) : getProducts();

		PagedListHolder pagedListHolder = new PagedListHolder<>(productEntities);
		pagedListHolder.setPage(page - 1);
		pagedListHolder.setPageSize(MAX_RESULTS);

		model.addAttribute("pageListHolder", pagedListHolder);

		return "user/home";
	}
	@RequestMapping(value="home", params="btnsearch")
	public String  searchPro(HttpServletRequest request,ModelMap model,
			@RequestParam(value = "categoryId", required = false, defaultValue = "-1") int categoryId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		List<ProductEntity> productEntities = this.searchProducts(request.getParameter("searchInput"));
		PagedListHolder pagedListHolder = new PagedListHolder<>(productEntities);
		
		pagedListHolder.setPage(page - 1);
		pagedListHolder.setPageSize(MAX_RESULTS);

		model.addAttribute("pageListHolder", pagedListHolder);
		return  "user/home";
	}

	@RequestMapping("cart")
	public String cart(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		List<CartItem> cartItems = (List<CartItem>) session.getAttribute("carts");
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		}
		for (int i = 0; i < cartItems.size(); i++) {
			cartItems.get(i).setProduct(getProduct(cartItems.get(i).getProduct().getId()));
		}
		session.setAttribute("carts", cartItems);
		PagedListHolder pagedListHolder = new PagedListHolder<>(cartItems);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(3);
		pagedListHolder.setPageSize(6);
		model.addAttribute("pagedListHolder", pagedListHolder);

		return "user/cart";
	}
	
	@RequestMapping("cart/delete")
	public String deleteCart(@RequestParam("productId") int productId, HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		List<CartItem> cartItems = (List<CartItem>) session.getAttribute("carts");
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		}
		cartItems.removeIf(e -> e.getProduct().getId() == productId);
		session.setAttribute("carts", cartItems);
		return cart(request, model);
	}

	@RequestMapping("cart/add")
	public String addToCart(@RequestParam("productId") int productId, HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		List<CartItem> cartItems = (List<CartItem>) session.getAttribute("carts");
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		}
		if (isExisting(productId, cartItems) == -1) {
			cartItems.add(new CartItem(1, getProduct(productId)));
		}
		model.addAttribute("cart", new Cart(cartItems));
		session.setAttribute("carts", cartItems);
		return cart(request, model);
	}

	@RequestMapping("cart/decreaseQuantity")
	public String decreaseQuantity(@RequestParam("productId") int productId, HttpServletRequest request,
			ModelMap model) {
		HttpSession session = request.getSession();
		List<CartItem> cartItems = (List<CartItem>) session.getAttribute("carts");
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		}

		int index = isExisting(productId, cartItems);
		if (index != -1 && cartItems.get(index).getQuantity() > 1) {
			cartItems.get(index).setQuantity(cartItems.get(index).getQuantity() - 1);
		}
		session.setAttribute("carts", cartItems);
		model.addAttribute("cart", new Cart(cartItems));
		return cart(request, model);
	}
	@RequestMapping("cart/increaseQuantity")
	public String increaseQuantity(@RequestParam("productId") int productId, HttpServletRequest request,
			ModelMap model) {
		HttpSession session = request.getSession();
		List<CartItem> cartItems = (List<CartItem>) session.getAttribute("carts");
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		}
		int index = isExisting(productId, cartItems);
		if (index != -1) {
			cartItems.get(index).setQuantity(cartItems.get(index).getQuantity() + 1);
		}

		session.setAttribute("carts", cartItems);
		model.addAttribute("cart", new Cart(cartItems));
		return cart(request, model);
	}

	public Integer saveOrder(OrderEntity order, HttpSession session2) {
		Session session = factory.openSession();
		org.hibernate.Transaction t = session.beginTransaction();
		int ret = 0;
		try {
			session.save(order);
			t.commit();
			session2.setAttribute("order", order);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			t.rollback();
			ret = -1;
		} finally {
			session.close();
		}
		return ret;

	}

	public Integer saveOrderItem(OrderItemEntity orderItem) {
		Session session = factory.openSession();
		org.hibernate.Transaction t = session.beginTransaction();
		int ret = 0;
		try {
			session.save(orderItem);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			t.rollback();
			ret = -1;
		} finally {
			session.close();
		}
		return ret;

	}

	public Integer updateQuantity(ProductEntity product) {
		Session session = factory.openSession();
		org.hibernate.Transaction t = session.beginTransaction();
		try {
			session.update(product);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping("createInvoice")
	public String invoice(HttpSession session, ModelMap model) {

		UserEntity user = new UserEntity();
		user = (UserEntity) session.getAttribute("user");

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String now = formatter.format(date);
		List<CartItem> cartItems = (List<CartItem>) session.getAttribute("carts");
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		}
		for (int i = 0; i < cartItems.size(); i++) {
			cartItems.get(i).setProduct(getProduct(cartItems.get(i).getProduct().getId()));	
		}
		model.addAttribute("cart", new Cart(cartItems));
		model.addAttribute("date", now);
		model.addAttribute("user", user);

		return "user/invoice";
	}

	@RequestMapping("createOrder")
	public String order(HttpSession session, @RequestParam("address")String address,ModelMap model) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		OrderEntity order = new OrderEntity();
		order.setStatus(0);
		order.setUser(user);
		order.setDeliveryAddress(address);
		Date now = new Date();
		order.setBuyDate(now);

		List<OrderItemEntity> oderItemList = new ArrayList<OrderItemEntity>();
		List<CartItem> cartItemList = (List<CartItem>) session.getAttribute("carts");
		OrderItemEntity orderItem = new OrderItemEntity();
		// Session session1 = factory.getCurrentSession();
		if (saveOrder(order, session) == 0) {
			OrderEntity orderE = (OrderEntity) (session.getAttribute("order"));
			String maxHql = "Select max(id) FROM OrderEntity";
			// Query query = session1.createQuery(maxHql);
			// int cart_id = Integer.parseInt(query.list().get(0).toString()) ;
			ProductEntity prod = new ProductEntity();
			for (CartItem cartItem : cartItemList) {
				orderItem.setPrice(cartItem.getPrice());
				System.out.print("Price: ");
				orderItem.setCart(orderE);
				orderItem.setQuantity(cartItem.getQuantity());
				orderItem.setProduct(cartItem.getProduct());
				if (saveOrderItem(orderItem) == 0) {
					prod = cartItem.getProduct();
					prod.setQuantity(prod.getQuantity() - cartItem.getQuantity());
					updateQuantity(prod);
					System.out.print("Luu order item thanh cong");
					mailer.sendThanks(user.getEmail(), user.getFullName());
				}
			}
			session.setAttribute("carts", null);
			int u_id = ((UserEntity) (session.getAttribute("user"))).getId();
			model.addAttribute("message","Đặt hàng thành công");
			model.addAttribute("back","Xem danh sách đặt hàng");
			model.addAttribute("url","user/order.htm");
			return "user/success";

		} else {
			System.out.println("Đặt hàng thất bại");

		}

		return "redirect:/cart.htm";
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
	public List<ProductEntity> searchProducts(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity where name LIKE :name";
		Query query = session.createQuery(hql);
		query.setParameter("name","%" + name + "%");
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
