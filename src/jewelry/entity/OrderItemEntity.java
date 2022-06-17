package jewelry.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CartItem")
public class OrderItemEntity {
	@Id
	@GeneratedValue
	private int id;
	private int quantity;
	private float price;
	@ManyToOne
	@JoinColumn(name = "pro_id")
	private ProductEntity product;
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private OrderEntity cart;

	public double getTamTinh() {
		return (price * quantity * (1 - (float)product.getDiscount() / 100));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = (float) d;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public OrderEntity getCart() {
		return cart;
	}

	public void setCart(OrderEntity cart) {
		this.cart = cart;
	}
}
