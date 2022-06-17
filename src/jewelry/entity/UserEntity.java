package jewelry.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "[User]")
public class UserEntity {
	@Id
	@GeneratedValue
	private int id;
	@NotBlank(message = "Không được để trống tên")
	private String firstname;
	@NotBlank(message = "Không được để trống họ")
	private String lastname;
	@NotBlank(message = "Không được để trống email") 
	@Email(message = "Địa chỉ email không hợp lệ")
	private String email;
	@NotBlank(message = "Không được để trống mật khẩu")
	private String password;
	@NotBlank(message = "Không được để trống địa chỉ")
	private String address;
	@NotBlank(message = "Không được để trống số điện thoại")
	private String phone;
	private String salt;
	private String avatar;
	private int sex;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date birthday;
	private int role_id;
	private int StatusDelete;
	private int IsVerify;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Collection<OrderEntity> carts;

	public int getIsVerify() {
		return IsVerify;
	}
	public void setIsVerify(int verify) {
		this.IsVerify = verify;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public Collection<OrderEntity> getCart() {
		return carts;
	}

	public void setCart(Collection<OrderEntity> carts) {
		this.carts = carts;
	}

	public Collection<OrderEntity> getCarts() {
		return carts;
	}

	public void setCarts(Collection<OrderEntity> carts) {
		this.carts = carts;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public int getStatusDelete() {
		return StatusDelete;
	}
	public void setStatusDelete(int statusDelete) {
		StatusDelete = statusDelete;
	}
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getFullName()
	{
		return lastname+" "+firstname;
	}

}
