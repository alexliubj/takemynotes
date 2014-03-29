package ca.techguys.takemynotes.net;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ca.techguys.takemynotes.beans.CategoryItem;
import ca.techguys.takemynotes.beans.CommonModel;
import ca.techguys.takemynotes.beans.ResultModel;
import ca.techguys.takemynotes.beans.UniversalModel;
import ca.techguys.takemynotes.beans.Note;
import ca.techguys.takemynotes.beans.Comment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


public class Parse {

	/**
	 * @param str
	 * @return
	 */
	public CategoryItem LogParse(String str) {
		Gson gson = new Gson();
		Type type = new TypeToken<CategoryItem>() {
		}.getType();
		CategoryItem model = new CategoryItem();
		model = gson.fromJson(str, type);
		return model;
	}
	
	public ResultModel ResultParse(String str) {
		Gson gson = new Gson();
		Type type = new TypeToken<ResultModel>() {
		}.getType();
		ResultModel model = new ResultModel();
		model = gson.fromJson(str, type);
		return model;
	}
	public ArrayList<CategoryItem> GetCategory(String str)
			throws JsonSyntaxException {
		Gson gson = new Gson(); 
	    JsonParser parser = new JsonParser(); 
	    JsonArray Jarray = parser.parse(str).getAsJsonArray(); 
	    ArrayList<CategoryItem> lcs = new ArrayList<CategoryItem>(); 
	    for(JsonElement obj : Jarray ){ 
	    	CategoryItem cse = gson.fromJson( obj , CategoryItem.class); 
	        lcs.add(cse); 
	    }

		return lcs;
	}
	/**
	 * @param str
	 * @return
	 */
	public CommonModel CommonPares(String str) {
		Gson gson = new Gson();
		Type type = new TypeToken<CommonModel>() {
		}.getType();
		CommonModel model = new CommonModel();
		model = gson.fromJson(str, type);
		return model;
	}

	public ArrayList<Comment> GetListComments(String str)
			throws JsonSyntaxException {
		Gson gson = new Gson(); 
	    JsonParser parser = new JsonParser(); 
	    JsonArray Jarray = parser.parse(str).getAsJsonArray(); 
	    ArrayList<Comment> lcs = new ArrayList<Comment>(); 
	    for(JsonElement obj : Jarray ){ 
	    	Comment cse = gson.fromJson( obj , Comment.class); 
	        lcs.add(cse); 
	    }
		return lcs;
	}
	
	public ArrayList<Note> GetNotesByCategory(String str)
			throws JsonSyntaxException {
		Gson gson = new Gson(); 
	    JsonParser parser = new JsonParser(); 
	    JsonArray Jarray = parser.parse(str).getAsJsonArray(); 
	    ArrayList<Note> lcs = new ArrayList<Note>(); 
	    for(JsonElement obj : Jarray ){ 
	    	Note cse = gson.fromJson( obj , Note.class); 
	        lcs.add(cse); 
	    }
		return lcs;
	}
}
