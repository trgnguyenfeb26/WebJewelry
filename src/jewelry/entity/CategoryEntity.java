package jewelry.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class CategoryEntity {
	@Id
	@GeneratedValue
	private int cate_id;
	private String cate_name;
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Collection<ProductEntity> products;

	public int getId() {
		return cate_id;
	}

	public void setId(int cate_id) {
		this.cate_id = cate_id;
	}

	public String getName() {
		return cate_name;
	}

	public void setName(String cate_name) {
		this.cate_name = cate_name;
	}

	public Collection<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Collection<ProductEntity> products) {
		this.products = products;
	}

}
