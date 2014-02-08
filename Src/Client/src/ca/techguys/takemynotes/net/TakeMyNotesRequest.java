package ca.techguys.takemynotes.net;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

public class TakeMyNotesRequest {
	private final String url = "http://mjk1.okhigh.com/";
//	private final String url = "http://m.meijika.com/";

	protected Context mContext;

	private BaseRequest baseRequest;

	public TakeMyNotesRequest(Context context) {
		baseRequest = new BaseRequest();
		mContext = context;
	}

	public String getUrl(String u, String a) {
		StringBuilder sb = new StringBuilder();
		sb.append(url).append(u).append("/").append(a);
		return sb.toString();
	}


	public String getRegisterCaptcha(String mobelNo, String key) throws IOException,
			TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(mobelNo)) {
			strParams.add(new BasicNameValuePair("mobelNo", mobelNo));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("user", "sendcode"));
	}


	public String getSignIn(String mobelNo, String key, String verificationCode, String passWord,
			String userName, String userSex, String userAddress) throws IOException,
			TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(mobelNo)) {
			strParams.add(new BasicNameValuePair("mobelNo", mobelNo));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(mobelNo)) {
			strParams.add(new BasicNameValuePair("verificationCode", verificationCode));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("passWord", passWord));
		}
		if (!TextUtils.isEmpty(mobelNo)) {
			strParams.add(new BasicNameValuePair("userName", userName));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("userSex", userSex));
		}
		// if (!TextUtils.isEmpty(key)) {
		// strParams.add(new BasicNameValuePair("userAddress", userAddress));
		// }
		return baseRequest.postRequestByHttpClient(strParams, getUrl("user", "reg"));
	}

	public String getValidateCaptcha(String mobelNo, String key, String verificationCode)
			throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(mobelNo)) {
			strParams.add(new BasicNameValuePair("mobelNo", mobelNo));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("verificationCode", verificationCode));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("user", "verificationKey"));
	}

	public String getLogin(String mobelNo, String passWord, String key) throws IOException,
			TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(mobelNo)) {
			strParams.add(new BasicNameValuePair("mobelNo", mobelNo));
		}
		if (!TextUtils.isEmpty(passWord)) {
			strParams.add(new BasicNameValuePair("passWord", passWord));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("user", "login"));
	}

	public String getFeedBack(String userId, String key, String opinion, String email)
			throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(opinion)) {
			strParams.add(new BasicNameValuePair("opinion", opinion));
		}
		if (!TextUtils.isEmpty(email)) {
			strParams.add(new BasicNameValuePair("email", email));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("help", "opinion"));
	}

	public String getShopType(String key) throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "type"));
	}


	public String getShopList(String userId, String key, String page, String shopType,
			String orderType, String distanceType, String longitude, String dimension)
			throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(page)) {
			strParams.add(new BasicNameValuePair("page", page));
		}
		if (!TextUtils.isEmpty(shopType)) {
			strParams.add(new BasicNameValuePair("shopType", shopType));
		}
		if (!TextUtils.isEmpty(orderType)) {
			strParams.add(new BasicNameValuePair("orderType", orderType));
		}
		if (!TextUtils.isEmpty(distanceType)) {
			strParams.add(new BasicNameValuePair("distanceType", distanceType));
		}
		if (!TextUtils.isEmpty(longitude)) {
			strParams.add(new BasicNameValuePair("longitude", longitude));
		}
		if (!TextUtils.isEmpty(dimension)) {
			strParams.add(new BasicNameValuePair("dimension", dimension));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "list"));
	}

	public String getShopInfo(String userId, String key, String shopId, String longitude,
			String dimension) throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(shopId)) {
			strParams.add(new BasicNameValuePair("shopId", shopId));
		}
		if (!TextUtils.isEmpty(longitude)) {
			strParams.add(new BasicNameValuePair("longitude", longitude));
		}
		if (!TextUtils.isEmpty(dimension)) {
			strParams.add(new BasicNameValuePair("dimension", dimension));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "info"));
	}

	public String getShopAddYL(String userId, String shop_id, String key) throws IOException,
			TimeoutException {
		Log.i("zzz", "quserId = " + userId);
		Log.i("zzz", "qshopId = " + shop_id);
		Log.i("zzz", "qkey = " + key);
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(shop_id)) {
			strParams.add(new BasicNameValuePair("shop_id", shop_id));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "addbrowse"));
	}

	public String getShopAddbrowse(String userId, String key, String shopId) throws IOException,
			TimeoutException {
		Log.i("zzz", "quserId = " + userId);
		Log.i("zzz", "qshopId = " + shopId);
		Log.i("zzz", "qkey = " + key);
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(shopId)) {
			strParams.add(new BasicNameValuePair("shopId", shopId));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "addbrowse"));
	}


	public String getShopBrowse(String userId, String key, String page, String shopType,
			String longitude, String dimension) throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(shopType)) {
			strParams.add(new BasicNameValuePair("shopType", shopType));
		}
		if (!TextUtils.isEmpty(page)) {
			strParams.add(new BasicNameValuePair("page", page));
		}
		if (!TextUtils.isEmpty(longitude)) {
			strParams.add(new BasicNameValuePair("longitude", longitude));
		}
		if (!TextUtils.isEmpty(dimension)) {
			strParams.add(new BasicNameValuePair("dimension", dimension));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "browse"));
	}


	public String getShopHaveGift(String userId, String key, String page) throws IOException,
			TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(page)) {
			strParams.add(new BasicNameValuePair("page", page));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "haveGift"));
	}


	public String GetShopMessagelist(String userId, String key, String page, String pageSize)
			throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(page)) {
			strParams.add(new BasicNameValuePair("page", page));
		}
		if (!TextUtils.isEmpty(pageSize)) {
			strParams.add(new BasicNameValuePair("pageSize", pageSize));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "messagelist"));
	}


	public String getShopFavlist(String userId, String key, String page) throws IOException,
			TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(page)) {
			strParams.add(new BasicNameValuePair("page", page));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "favlist"));
	}


	public String GetShopFav(String userId, String key, String shopId) throws IOException,
			TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(shopId)) {
			strParams.add(new BasicNameValuePair("shopId", shopId));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "fav"));
	}


	public String GetShopCancelFav(String userId, String key, String shopId) throws IOException,
			TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(shopId)) {
			strParams.add(new BasicNameValuePair("shopId", shopId));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "cancelFav"));
	}


	public String GetShopChangeExtensionState(String logId, String key) throws IOException,
			TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(logId)) {
			strParams.add(new BasicNameValuePair("logId", logId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		return baseRequest.postRequestByHttpClient(strParams,
				getUrl("shop", "changeExtensionState"));
	}


	public String GetHelpVersion(String appVersion, String key, String appType) throws IOException,
			TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(appVersion)) {
			strParams.add(new BasicNameValuePair("appVersion", appVersion));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(appType)) {
			strParams.add(new BasicNameValuePair("appType", appType));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("help", "version"));
	}

	public String getBuyInfo(String userId, String key) throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("buy", "info"));
	}


	public String getHelpLike(String userId, String key, String app_type, String page)
			throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(app_type)) {
			strParams.add(new BasicNameValuePair("app_type", app_type));
		}
		if (!TextUtils.isEmpty(page)) {
			strParams.add(new BasicNameValuePair("page", page));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("help", "like"));
	}


	public String testWeiBoShare(String token) throws IOException, TimeoutException {

		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		// if (!TextUtils.isEmpty(userId)) {
		// strParams.add(new BasicNameValuePair("userId", userId));
		// }
		// if (!TextUtils.isEmpty(key)) {
		// strParams.add(new BasicNameValuePair("key", key));
		// }
		strParams.add(new BasicNameValuePair("source", ""));
		strParams.add(new BasicNameValuePair("access_token", token));
		String send = URLEncoder.encode(" Test String weibo", HTTP.UTF_8);
		Log.e("Test", "MJKRe send=" + send);
		strParams.add(new BasicNameValuePair("status", send));
		strParams.add(new BasicNameValuePair("visible", "0"));
		strParams.add(new BasicNameValuePair("list_id", ""));
		strParams.add(new BasicNameValuePair("lat", "0.0"));
		strParams.add(new BasicNameValuePair("long", "0.0"));
		strParams.add(new BasicNameValuePair("annotations", ""));
		return baseRequest.postRequestByHttpClient(strParams,
				"https://api.weibo.com/2/statuses/update.json");
	}

	/**

	 * @param userId
	 * @param key
	 * @param shopId
	 * @param shopPrice
	 * @param actualPrice
	 * @param exchangePrintingNo
	 * @param exchangeGiftNo
	 * @param peopleNo
	 * @return
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public String getBuyPrinting(String userId, String key, String shopId, String shopPrice,
			String actualPrice, String exchangePrintingNo, String exchangeGiftNo, String peopleNo,
			String discountPrice, String discount) throws IOException, TimeoutException {
		Log.i("zzz", "userId = " + userId);
		Log.i("zzz", "key = " + key);
		Log.i("zzz", "shopId = " + shopId);
		Log.i("zzz", "shopPrice = " + shopPrice);
		Log.i("zzz", "actualPrice = " + actualPrice);
		Log.i("zzz", "exchangePrintingNo = " + exchangePrintingNo);
		Log.i("zzz", "exchangeGiftNo = " + exchangeGiftNo);
		Log.i("zzz", "peopleNo = " + peopleNo);
		Log.i("zzz", "discountPrice = " + discountPrice);
		Log.i("zzz", "discount = " + discount);

		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(shopId)) {
			strParams.add(new BasicNameValuePair("shopId", shopId));
		}
		if (!TextUtils.isEmpty(shopPrice)) {
			strParams.add(new BasicNameValuePair("shopPrice", shopPrice));
		}
		if (!TextUtils.isEmpty(actualPrice)) {
			strParams.add(new BasicNameValuePair("actualPrice", actualPrice));
		}
		if (!TextUtils.isEmpty(exchangePrintingNo)) {
			strParams.add(new BasicNameValuePair("exchangePrintingNo", exchangePrintingNo));
		}
		if (!TextUtils.isEmpty(exchangeGiftNo)) {
			strParams.add(new BasicNameValuePair("exchangeGiftNo", exchangeGiftNo));
		}
		if (!TextUtils.isEmpty(peopleNo)) {
			strParams.add(new BasicNameValuePair("peopleNo", peopleNo));
		}
		if (!TextUtils.isEmpty(discountPrice)) {
			strParams.add(new BasicNameValuePair("discountPrice", discountPrice));
		}
		if (!TextUtils.isEmpty(discount)) {
			strParams.add(new BasicNameValuePair("discount", discount));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("buy", "printing"));
	}


	public String getOauthSendSinaWeibo(String token, String content) throws IOException,
			TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(token)) {
			strParams.add(new BasicNameValuePair("token", token));
		}
		if (!TextUtils.isEmpty(content)) {
			strParams.add(new BasicNameValuePair("content", content));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("oauth", "sendSinaWeibo"));
	}


	public String getOauthPostweibo(String token, String content, String tokenSecret)
			throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(token)) {
			strParams.add(new BasicNameValuePair("token", token));
		}
		if (!TextUtils.isEmpty(content)) {
			strParams.add(new BasicNameValuePair("content", content));
		}
		if (!TextUtils.isEmpty(tokenSecret)) {
			strParams.add(new BasicNameValuePair("token_secret", tokenSecret));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("oauth", "postweibo"));
	}


	public String getShopGrade(String userId, String key, String shopId, String gradeService,
			String gradeDish) throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(shopId)) {
			strParams.add(new BasicNameValuePair("shopId", shopId));
		}
		if (!TextUtils.isEmpty(gradeService)) {
			strParams.add(new BasicNameValuePair("gradeService", gradeService));
		}
		if (!TextUtils.isEmpty(gradeDish)) {
			strParams.add(new BasicNameValuePair("gradeDish", gradeDish));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("shop", "grade"));

	}


	public String getUserSet(String userId, String key, String passWord, String userName,
			String userSex, String userAddress) throws IOException, TimeoutException {
		ArrayList<NameValuePair> strParams = new ArrayList<NameValuePair>();
		if (!TextUtils.isEmpty(userId)) {
			strParams.add(new BasicNameValuePair("userId", userId));
		}
		if (!TextUtils.isEmpty(key)) {
			strParams.add(new BasicNameValuePair("key", key));
		}
		if (!TextUtils.isEmpty(passWord)) {
			strParams.add(new BasicNameValuePair("passWord", passWord));
		}
		if (!TextUtils.isEmpty(userName)) {
			strParams.add(new BasicNameValuePair("userName", userName));
		}
		if (!TextUtils.isEmpty(userSex)) {
			strParams.add(new BasicNameValuePair("userSex", userSex));
		}
		if (!TextUtils.isEmpty(userAddress)) {
			strParams.add(new BasicNameValuePair("userAddress", userAddress));
		}
		return baseRequest.postRequestByHttpClient(strParams, getUrl("user", "set"));
	}

}
