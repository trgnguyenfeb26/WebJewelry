package jewelry.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class ProductEntity {
	@Id
	@GeneratedValue
	private int id;
	@Column(name="name")
	private String name;
	private float price;
	private int discount;
	private String image;
	private String unit;
	private String content;
	private int quantity;
	@ManyToOne
	@JoinColumn(name = "cate_id")
	private CategoryEntity category;
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Collection<OrderItemEntity> orderItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Collection<OrderItemEntity> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Collection<OrderItemEntity> cartItems) {
		this.orderItems = cartItems;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
