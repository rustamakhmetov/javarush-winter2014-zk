package ru.javarush.winter2014.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import ru.javarush.winter2014.utilities.FieldMatch;

@Entity
@Table(name = "user")
/*@NamedQuery(name = "UserProfile.findUserByUserID", query = "SELECT usr  FROM UserProfile  as usr WHERE usr.userLoginID = :arg1")
@FieldMatch.List({ @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"), })*/
public class UserProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "First Name cannot be empty")
	@Size(min = 2, message = "First name is too small")
	private String name;

	@NotNull(message = "Invalid age. Should be not negative.")
	//@Length(min = 0, message = "Invalid age. Should be not negative.")
	private Integer age;

	/*@Length(min = 2, max = 2, message = "Invalid Middle name. Should be are two letter")
	private String middleName;

	@NotBlank(message = "User Account Number cannot be empty")
	@Pattern(regexp = "^[a-zA-Z]{2}-\\d+$", message = "Invalid Account Number. First two letter should be Alphabets and then one hyphen and then any digits. For example AA-333, BB-44")
	private String userAccountNumber;

	@Pattern(regexp = "[0-9]{3}-[0-9]{2}-[0-9]{4}", message = "Invalid SSN format")
	private String SSN;

	@NotBlank(message = "Address1 cannot be empty")
	private String address1;

	private String address2;

	@NotBlank(message = "City cannot be empty")
	private String city;

	@NotBlank(message = "State cannot be empty")
	@Length(min = 2, max = 2, message = "Invalid State. Should be are two letter")
	private String State;

	@NotBlank(message = "ZipCode cannot be empty")
	private String zipCode;

	@NotBlank(message = "Email cannot be empty")
	@org.hibernate.validator.constraints.Email(message = "Invalid Email Format")
	private String email;

	@NotBlank(message = "Login Name cannot be empty")
	private String userLoginID;

	@NotBlank(message = "Password cannot be empty")
	private String password;

	@Transient
	private String confirmPassword;*/

	@Column(name="isAdmin", columnDefinition = "BIT", length = 1)
	private Boolean admin = false;

	@Temporal(TemporalType.DATE)
	@Past(message = "Create date cannot be empty")
	private Date createDate = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

	
	

}