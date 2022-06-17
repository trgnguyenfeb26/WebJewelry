package jewelry.Dao.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jewelry.Dao.supportDao;
import jewelry.entity.UserEntity;
import jewelry.entity.ProductEntity;
import jewelry.entity.supportEntity;

@Transactional
@Configuration
public class supportDaoImpl implements supportDao{

	
	@Autowired
	SessionFactory ssFactory;

	@Override
	public  List<supportEntity> listSupport(int id) {
		String hql ="";
		if(id ==0) {
		   hql = "FROM supportEntity ORDER BY id DESC";
		}
		else if(id ==1) {
			 hql = "FROM supportEntity where (feedback = 0) ORDER BY id DESC";
		}
		else if(id ==2) {
			 hql = "FROM supportEntity where (feedback = 1) ORDER BY id DESC";
		}
		Session session = ssFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List <supportEntity> list = query.list();
		return list;
	}
	
	@Override
	public supportEntity getSupport(int id) {
		String hql = "FROM supportEntity where id = '" +id +"'";
		Query query = ssFactory.getCurrentSession().createQuery(hql);
		supportEntity list = (supportEntity) query.uniqueResult();
		return list;
	}
	
	@Override
	public boolean insert(supportEntity support) {

		Session session = ssFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(support);
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
	public boolean update(supportEntity support) {

		Session session = ssFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(support);
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
