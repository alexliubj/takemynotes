package ca.techguys.takemynotes.beans;

import java.io.Serializable;

public class UserInfo  implements Serializable {

	private String name;
	private String uiserImage;
	private String email;
	private String password;
	private String address;
	private String city;
	private String province;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUiserImage() {
		return uiserImage;
	}
	public void setUiserImage(String uiserImage) {
		this.uiserImage = uiserImage;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", uiserImage=" + uiserImage
				+ ", email=" + email + ", password=" + password + ", address="
				+ address + ", city=" + city + ", province=" + province + "]";
	}
	
	
}
