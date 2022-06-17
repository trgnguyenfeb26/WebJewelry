package jewelry.model;

import java.util.List;

public class Cart {
	private List<CartItem> cartItems;

	public Cart(List<CartItem> cartItems) {
		super();
		this.cartItems = cartItems;
	}

	public float getTotalPrice() {
		float r = 0;
		for (int i = 0; i < cartItems.size(); i++) {
			r += cartItems.get(i).getTamTinh();
		}
		return r;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

}
