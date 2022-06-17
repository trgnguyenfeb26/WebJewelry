package jewelry.model;

import jewelry.entity.ProductEntity;

public class CartItem {
	private int quantity;
	private ProductEntity product;

	public CartItem(int quantity, ProductEntity product) {
		super();
		this.quantity = quantity;
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public double getTamTinh() {
		return (product.getPrice() * quantity * (1 - ((float)product.getDiscount() / 100)));
	}
	public double getPrice()
	{
		return (product.getPrice() * (1 - ((float)product.getDiscount() / 100)));
	}
}
