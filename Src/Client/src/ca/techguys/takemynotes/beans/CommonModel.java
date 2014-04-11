package ca.techguys.takemynotes.beans;

import java.io.Serializable;

public class CommonModel  implements Serializable{
	private int result;
	private String errormessage;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

}
