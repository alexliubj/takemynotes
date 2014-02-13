package ca.techguys.takemynotes.beans;

import java.util.List;

public class UniversalModel<T> {
	private int result;
	private int page;
	private int totalpage;
	private int total;
	private String errormessage;
	private List<T> list;
	private List data;

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "UniversalModel [result=" + result + ", page=" + page
				+ ", totalpage=" + totalpage + ", total=" + total
				+ ", errormessage=" + errormessage + ", list=" + list + "]";
	}

}
