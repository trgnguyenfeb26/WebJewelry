package jewelry.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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

import jewelry.entity.OrderEntity;
import jewelry.entity.UserEntity;

@Transactional
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("/order")
	public String donHangOfUser(HttpSession session2, ModelMap model,HttpServletRequest request) {
		List<OrderEntity> orders = this.getOrders(session2);
		PagedListHolder pagedListHolder = new PagedListHolder<>(orders);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "user/order";
	}
	public List<OrderEntity> getOrders(HttpSession session2) {

		UserEntity user = (UserEntity) session2.getAttribute("user");
		int u_id=user.getId();
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity as c where c.user.id=:u_id";
		Query query = session.createQuery(hql);
		query.setParameter("u_id", u_id);
		List<OrderEntity> list = query.list();

		return list;
	}
	public List<OrderEntity> getOrderByDate(Date date,HttpSession session2)
	{
		UserEntity user = (UserEntity) session2.getAttribute("user");
		int u_id=user.getId();
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity as c where c.user.id=:u_id and c.buyDate=:date1";
		Query query = session.createQuery(hql);
		query.setParameter("u_id", u_id);
		query.setParameter("date1", date);
		List<OrderEntity> list = query.list();
		return list;
	}
	@RequestMapping(value = "order", params = "btnsearch")
	public String seachOrder(HttpServletRequest request, ModelMap model,HttpSession session) {		
		Date date = new Date();
		session.setAttribute("btn", "searchOrder");
		try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("searchInput"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<OrderEntity> orders = this.getOrderByDate(date,session);
		PagedListHolder pagedListHolder = new PagedListHolder<>(orders);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "user/order";
	}
	@RequestMapping(value = "/order/detail/{id}")
	public String getCTDDH(@PathVariable int id, ModelMap model) {
		model.addAttribute("order", factory.getCurrentSession().get(OrderEntity.class, id));
		return "user/orderDetail";
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

	@RequestMapping(value = "/order/{id}/huy")
	public String huy(@PathVariable("id") int id, ModelMap model, HttpServletResponse response, HttpServletRequest request) {
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
		model.addAttribute("back","Quay lại");
		model.addAttribute("url","user/order.htm");
		return "user/success";
		
	}


	@RequestMapping(value = "/order/{id}/giaohang")
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

		model.addAttribute("carts", factory.getCurrentSession().get(OrderEntity.class, id));
		model.addAttribute("message","Nhận hàng thành công");
		model.addAttribute("back","Quay lại");
		model.addAttribute("url","user/order.htm");
		return "user/success";
	}

}
