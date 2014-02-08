package ca.techguys.takemynotes.beans;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class NotesSharedPreferences {

	private static Editor mSaveEditor;
	private static SharedPreferences saveInfo;
	private static NotesSharedPreferences mjkSP = new NotesSharedPreferences();
	private static Context mContext;
	private static final String SETTING_INFO = "meijika";
	private static final String IS_BIND_SINA_FLAG = "isBindSina";
	private static final String IS_BIND_QQ_FLAG = "isBindQQ";
	private static final String SINA_TOKEN = "sinaToken";
	private static final String QQ_TOKEN = "qqToken";
	private static final String QQ_TOKEN_SECRET = "qq_token_secret";

	public static NotesSharedPreferences getInstance(Context context) {
		mContext = context;
		if (saveInfo == null && mContext != null) {
			saveInfo = mContext.getSharedPreferences(SETTING_INFO,
					Context.MODE_PRIVATE);
			mSaveEditor = saveInfo.edit();
		}
		return mjkSP;
	}

	private NotesSharedPreferences() {

	}

	/**
	 * @param key
	 * @return
	 */
	public boolean isContainKey(String key) {
		return saveInfo.contains(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		return saveInfo.getString(key, "");
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getString(String key, String defaultValue) {
		return saveInfo.getString(key, defaultValue);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> getAll() {
		return (HashMap<String, String>) saveInfo.getAll();
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setString(String key, String value) {
		if (saveInfo.contains(key)) {
			mSaveEditor.remove(key);
		}
		mSaveEditor.putString(key, value);
		return mSaveEditor.commit();
	}

	/**
	 * @param key
	 * @return
	 */
	public boolean clearItem(String key) {
		mSaveEditor.remove(key);
		return mSaveEditor.commit();
	}

	/**
	 * @return
	 */
	public boolean clearAllItem() {
		mSaveEditor.clear();
		return mSaveEditor.commit();
	}

	/**
	 * @return
	 */
	public boolean setIsBindSinaFlag(boolean value) {
		mSaveEditor.putBoolean(IS_BIND_SINA_FLAG, value);
		return mSaveEditor.commit();
	}

	/**

	 * @return 
	 */
	public boolean getIsBindSinaFlag() {
		return saveInfo.getBoolean(IS_BIND_SINA_FLAG, false);
	}

	/**

	 * @param value

	 * @return
	 */
	public boolean setIsBindQQFlag(boolean value) {
		mSaveEditor.putBoolean(IS_BIND_QQ_FLAG, value);
		return mSaveEditor.commit();
	}


	public boolean getIsBindQQFlag() {
		return saveInfo.getBoolean(IS_BIND_QQ_FLAG, false);
	}


	public boolean setSinaToken(String tokenValue) {
		mSaveEditor.putString(SINA_TOKEN, tokenValue);
		return mSaveEditor.commit();
	}


	public String getSinaToken() {
		return saveInfo.getString(SINA_TOKEN, "");
	}

	/**
	 * @param tokenValue
	 * @return
	 */
	public boolean setQQToken(String tokenValue) {
		mSaveEditor.putString(QQ_TOKEN, tokenValue);
		return mSaveEditor.commit();
	}

	/**
	 * @return
	 */
	public String getQQToken() {
		return saveInfo.getString(QQ_TOKEN, "");
	}

	/**
	 * @param tokenValue
	 * @return
	 */
	public boolean setQQTokenSecret(String tokenSecretValue) {
		mSaveEditor.putString(QQ_TOKEN_SECRET, tokenSecretValue);
		return mSaveEditor.commit();
	}

	/**
	 * @return
	 */
	public String getQQTokenSecret() {
		return saveInfo.getString(QQ_TOKEN_SECRET, "");
	}

}
