package jewelry.Dao.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jewelry.Dao.ratedDao;
import jewelry.entity.OrderEntity;
import jewelry.entity.OrderItemEntity;
import jewelry.entity.ProductEntity;
import jewelry.entity.ratedEntity;
import jewelry.entity.UserEntity;
import jewelry.entity.supportEntity;

@Transactional
@Configuration
public class ratedDaoImpl implements ratedDao{

	
	@Autowired
	SessionFactory ssFactory;

	@Override
	public  List<ratedEntity> listRated(int id) {
		
		String hql ="";
		if(id ==0) {
		   hql = "FROM ratedEntity ORDER BY id DESC";
		}
		else if(id ==1) {
			 hql = "FROM ratedEntity where (feedback is null or feedback = '') ORDER BY id DESC";
		}
		else if(id ==2) {
			 hql = "FROM ratedEntity where (feedback is not null and feedback != '') ORDER BY id DESC";
		}
		Session session = ssFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List <ratedEntity> list = query.list();
		return list;
	}
	
	@Override
	public ratedEntity getRated(int id) {
		String hql = "FROM ratedEntity where id = '" +id +"'";
		Query query = ssFactory.getCurrentSession().createQuery(hql);
		ratedEntity list = (ratedEntity) query.uniqueResult();
		return list;
	}
	@Override
	public boolean checkUser(int userId , int productId) {
		String hql = "FROM OrderEntity where ( user.id = '" +userId +"' AND status = '1' )";
		Query query = ssFactory.getCurrentSession().createQuery(hql);
		List <OrderEntity> list = query.list();
		for(OrderEntity x :list) {
			for(OrderItemEntity v :x.getCartItems()) {
				if(v.getProduct().getId() == productId)
					return false;
			}
		}
		return true;
	}
	
	@Override
	public List<ratedEntity> getListRated(int id) {
		String hql = "FROM ratedEntity where ( product.id = " +id +" )";
		Session session = ssFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List <ratedEntity> list = query.list();
		return list;
	}
	
	@Override
	public int getCount(int id) {
		String hql ="";
		if(id ==0) {
		   hql = "FROM ratedEntity ORDER BY id DESC";
		}
		else if(id ==1) {
			 hql = "FROM ratedEntity where (feedback is null or feedback = '') ORDER BY id DESC";
		}
		else if(id ==2) {
			 hql = "FROM ratedEntity where (feedback is not null and feedback != '') ORDER BY id DESC";
		}
		Session session = ssFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List <ratedEntity> list = query.list();
		Integer x = list.size();
		return x;
	}
	@Override
	public boolean update(ratedEntity rated) {
		Session session = ssFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(rated);
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
		return false;
	}
	@Override
	public boolean insert(ratedEntity rated) {
		Session session = ssFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(rated);
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
		return false;
	}
}
