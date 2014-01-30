package ca.techguys.takemynotes.beans;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * HTTP Request Result
 * @author Alex.Liu
 * @date 2014.1.29
 * @email alexliubo@gmail.com
 *
 */
public class ApplicationData {
	
	ArrayList<Object> listdata = new ArrayList<Object>();
	
	public static HashMap<String, Boolean> newsReadFlag=new HashMap<String, Boolean>();
	
	public ArrayList<Object> getListdata() {
		return listdata;
	}
	public void setListdata(ArrayList<Object> listdata) {
		this.listdata = listdata;
	}
}
