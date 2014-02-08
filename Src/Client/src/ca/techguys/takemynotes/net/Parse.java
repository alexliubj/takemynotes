package ca.techguys.takemynotes.net;

import java.lang.reflect.Type;
import java.util.List;

import ca.techguys.takemynotes.beans.CommonModel;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


public class Parse {

//	/**
//	 * @param str
//	 * @return
//	 */
//	public LogInModel LogParse(String str) {
//		Gson gson = new Gson();
//		Type type = new TypeToken<LogInModel>() {
//		}.getType();
//		LogInModel model = new LogInModel();
//		model = gson.fromJson(str, type);
//		return model;
//	}
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
//	/**
//	 * @param str
//	 * @return
//	 * @throws JsonSyntaxException
//	 */
//	public UniversalModel<BusinessInformationModel> ShopFavlistPares(String str)
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
