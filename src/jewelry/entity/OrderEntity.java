package jewelry.entity;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Cart")
public class OrderEntity {
	@Id
	@GeneratedValue
	private int id;
	private String deliveryAddress;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date buyDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date deliveryCancelDay;
	private int status;
	@ManyToOne
	@JoinColumn(name = "u_id")
	private UserEntity user;
	@OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
	private List<OrderItemEntity> cartItems;

	public float getTotalPrice() {
		float r = 0;
		for (int i = 0; i < cartItems.size(); i++) {
			r += cartItems.get(i).getTamTinh();
		}
		return r;
	}
	public float getTotalPriceOrder() {
		float r = 0;
		for (int i = 0; i < cartItems.size(); i++) {
			r += cartItems.get(i).getPrice();
		}
		return r;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Date getDeliveryCancelDay() {
		return deliveryCancelDay;
	}

	public void setDeliveryCancelDay(Date deliveryCancelDay) {
		this.deliveryCancelDay = deliveryCancelDay;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<OrderItemEntity> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<OrderItemEntity> cartItems) {
		this.cartItems = cartItems;
	}

}
