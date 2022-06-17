package jewelry.Dao;

import java.util.List;

import jewelry.entity.ratedEntity;

public interface ratedDao {
	 
	List<ratedEntity> listRated(int id) ;

	ratedEntity getRated(int id);

	int getCount(int id);

	boolean update(ratedEntity rated);

	List<ratedEntity> getListRated(int id);

	boolean insert(ratedEntity rated);

	boolean checkUser(int userId, int productId);
}
