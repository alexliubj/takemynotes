package ca.techguys.takemynotes.net;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ca.techguys.takemynotes.beans.CategoryItem;
import ca.techguys.takemynotes.beans.CommonModel;
import ca.techguys.takemynotes.beans.ResultModel;
import ca.techguys.takemynotes.beans.UniversalModel;
import ca.techguys.takemynotes.beans.Note;

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
	
//
//	/**
//
//	 * @param str
//	 * @return
//	 */
//	public UniversalModel<ShopTypeModel<ShopTypeChildModel>> parseShopType(String str)
//			throws JsonSyntaxException {
//		UniversalModel<ShopTypeModel<ShopTypeChildModel>> model = new UniversalModel<ShopTypeModel<ShopTypeChildModel>>();
//		Type type = new TypeToken<UniversalModel<ShopTypeModel<ShopTypeChildModel>>>() {
//		}.getType();
//		model = new Gson().fromJson(str, type);
//		return model;
//	}
//
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
//
//	/**
//	 * @param str
//	 * @return
//	 */
//	public ShopInfoModel ShopInfoPares(String str) throws JsonSyntaxException {
//		Gson gson = new Gson();
//		Type type = new TypeToken<ShopInfoModel>() {
//		}.getType();
//		ShopInfoModel model = new ShopInfoModel();
//		model = gson.fromJson(str, type);
//		return model;
//	}
//
	/**
	 * @param str
	 * @return
	 * @throws JsonSyntaxException
	 */
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
	
	
	
//
//	/**
//	 * @param str
//	 * @return
//	 */
//	public UniversalModel<BusinessInformationModel> parseRecentlyView(String str)
//			throws JsonSyntaxException {
//		Type type = new TypeToken<UniversalModel<BusinessInformationModel>>() {
//		}.getType();
//		UniversalModel<BusinessInformationModel> model = new UniversalModel<BusinessInformationModel>();
//		model = new Gson().fromJson(str, type);
//		return model;
//	}
//
//	/**
//	 * @param str
//	 * @return
//	 */
//	public UpdataVersionModel UpdataVersionPares(String str) {
//		Gson gson = new Gson();
//		Type type = new TypeToken<UpdataVersionModel>() {
//		}.getType();
//		UpdataVersionModel model = new UpdataVersionModel();
//		model = gson.fromJson(str, type);
//		return model;
//	}
//
//	public UniversalModel UniversalPares(String str) {
//		Gson gson = new Gson();
//		Type type = new TypeToken<UniversalModel>() {
//		}.getType();
//		UniversalModel model = new UniversalModel();
//		model = gson.fromJson(str, type);
//		return model;
//	}
//
//	/**
//	 * @param json
//	 * @return
//	 * @throws JsonSyntaxException
//	 */
//	public UniversalModel<AccountBookModel> parseAccounBookInfo(String json)
//			throws JsonSyntaxException {
//		UniversalModel<AccountBookModel> temp = new UniversalModel<AccountBookModel>();
//		Type type = new TypeToken<UniversalModel<AccountBookModel>>() {
//		}.getType();
//		temp = new Gson().fromJson(json, type);
//		return temp;
//	}
//
//	public UniversalModel<LikeAppModel> parseLikeApp(String json) throws JsonSyntaxException {
//		UniversalModel<LikeAppModel> temp = new UniversalModel<LikeAppModel>();
//		Type type = new TypeToken<UniversalModel<LikeAppModel>>() {
//		}.getType();
//		temp = new Gson().fromJson(json, type);
//		return temp;
//	}
//
//	public UniversalModel parseSendWeiBoresult(String json) throws JsonSyntaxException {
//		UniversalModel temp = new Gson().fromJson(json, UniversalModel.class);
//		return temp;
//	}

}
