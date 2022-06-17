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
@Table(name="support")
public class supportEntity {
	@Id
	@GeneratedValue
	@Column(name ="Id")
	private int id;
	@Column(name ="content")
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="accountId")
	private UserEntity account;
	
	@Column(name ="feedback")
	private Boolean feedback;
	
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
	public Boolean getFeedback() {
		return feedback;
	}
	public void setFeedback(Boolean feedback) {
		this.feedback = feedback;
	}

}