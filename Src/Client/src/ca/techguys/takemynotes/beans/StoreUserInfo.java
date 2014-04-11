package ca.techguys.takemynotes.beans;
import java.io.Serializable;
public class StoreUserInfo implements Serializable {

	private String idUsers;
	private String Name;
	private String UserImg;
	private String Email;
	public String getIdUsers() {
		return idUsers;
	}
	public void setIdUsers(String idUsers) {
		this.idUsers = idUsers;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getUserImg() {
		return UserImg;
	}
	public void setUserImg(String userImg) {
		UserImg = userImg;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	private String Address;
	private String City;
	@Override
	public String toString() {
		return "UserInfo [idUsers=" + idUsers + ", Name=" + Name + ", UserImg="
				+ UserImg + ", Email=" + Email + ", Address=" + Address
				+ ", City=" + City + ", Province=" + Province + "]";
	}
	private String Province;
	
}
