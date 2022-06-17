package jewelry.entity;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
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

@Entity
@Table(name="rated")
public class ratedEntity {
	@Id
	@GeneratedValue
	@Column(name ="ratedId")
	private int id;
	@ManyToOne
	@JoinColumn(name="accountId")
	private UserEntity account;
	@ManyToOne
	@JoinColumn(name="productId")
	private ProductEntity product;
	@Column(name ="content")
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date")
	private Date date;
	
	@Column(name ="feedback")
	private String feedback;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;	
	}
	public UserEntity getAccount() {
		return account;
	}
	public void setAccount(UserEntity account) {
		this.account = account;	
	}
	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;	
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void  setDate(Date date) {
		this.date = date;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}