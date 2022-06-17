package jewelry.Dao.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jewelry.Dao.statsDao;


@Transactional
@Configuration
public class statsDaoImpl implements statsDao{

	
	@Autowired
	SessionFactory ssFactory;

	@Override
	public  List<Object[]> listStats(int type,int year,int month) {
		
		String nYear ="" ,nMonth ="" , nType ="";
		if (year >0)
			nYear  = "YEAR(H.deliveryCancelDay) = " +year+ " AND ";
		if(month>0)
			nMonth = "MONTH(H.deliveryCancelDay) = " +month + " AND ";
		if(type ==1)
		       nType = "SUM(quantity*price)";
		else nType = "SUM(quantity)";
		String hql = "SELECT D.product.id ,SUM(quantity) AS TONG ,SUM(quantity*price) AS DOANHTHU \n"
				+ "FROM OrderItemEntity D\n"
				+"WHERE  D.cart.id  \n"
				+"IN (SELECT H.id \n"
				+ "FROM OrderEntity H \n"
				+" WHERE (" + nMonth+ nYear +" H.status = 1  AND D.cart.id = H.id) ) \n"
				+ "GROUP BY D.product.id \n"
				+ "ORDER BY "+ nType +" DESC ";
		try {
			Session session = ssFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			List <Object[]> list = query.list();
			return list;
		}
		catch (Exception e) {
			System.out.println("err \n" +e);
		}
		ArrayList <Object[]> list = new ArrayList<Object[]>() ;
		return list;
		// type ==1 : lọc theo doanh thu
	}
	
	
}
