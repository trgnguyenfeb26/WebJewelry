package jewelry.Dao;

import java.util.List;


public interface statsDao {
	 
	List<Object[]> listStats(int type,int year,int month) ;
}
