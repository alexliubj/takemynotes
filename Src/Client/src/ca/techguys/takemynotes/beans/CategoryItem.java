package ca.techguys.takemynotes.beans;

import java.io.Serializable;
public class CategoryItem implements Serializable{

	private String idCategory;
	private String CategoryName;
	private String CateImg;
	public String getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(String idCategory) {
		this.idCategory = idCategory;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public String getCateImg() {
		return CateImg;
	}
	public void setCateImg(String cateImg) {
		CateImg = cateImg;
	}
	@Override
	public String toString() {
		return "CategoryItem [idCategory=" + idCategory + ", CategoryName="
				+ CategoryName + ", CateImg=" + CateImg + "]";
	}

}
