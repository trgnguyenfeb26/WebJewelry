package jewelry.Dao;

import java.util.List;

import jewelry.entity.ProductEntity;
import jewelry.entity.supportEntity;

public interface supportDao {
	 
	List<supportEntity> listSupport(int id) ;

	supportEntity getSupport(int id);

	boolean insert(supportEntity support);

	boolean update(supportEntity support);

	
}
