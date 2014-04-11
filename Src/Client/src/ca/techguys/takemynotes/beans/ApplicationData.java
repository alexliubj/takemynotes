package ca.techguys.takemynotes.beans;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.os.Build;
import android.os.Vibrator;
import android.telephony.TelephonyManager;

/**
 * HTTP Request Result
 * @author Alex.Liu
 * @date 2014.1.29
 * @email alexliubo@gmail.com
 *
 */
public class ApplicationData extends Application{
	
	static ArrayList<Object> listdata = new ArrayList<Object>();
	
	static UserInfo userinfo = new UserInfo();
	static ArrayList<CategoryItem> cateItem = new ArrayList<CategoryItem>();
	
	public static ArrayList<CategoryItem> getCategoryList()
	{
		return cateItem;
	}
	
	public static void SetCategoryList(ArrayList<CategoryItem> listCategory)
	{
		cateItem = listCategory;
	}
	
	public static UserInfo GetUserInforamtion()
	{
		return userinfo;
	}
	
	public static void SetUserInfor(UserInfo userinfor )
	{
		userinfo = userinfor;
	}
	public static HashMap<String, Boolean> newsReadFlag=new HashMap<String, Boolean>();
	
	public ArrayList<Object> getListdata() {
		return listdata;
	}
	public void setListdata(ArrayList<Object> listdata) {
		this.listdata = listdata;
	}
	
	
	

	public static final String TAB_WOCHACHA = "TAB_WOCHACHA";
	public static final String TAB_SHOPPING = "TAB_SHOPPING";
	public static final String TAB_SOCIAL_SECURITY = "TAB_SOCIALSECURITY";
	public static final String TAB_UTILITIES = "TAB_UTILITIES";
	public static final String TAB_MORE = "TAB_MORE";
	public static final String TAB_INSURANCE_POLICY = "TAB_INSURANCE_POLICY";
	public static final String TAB_PROVIDENT_FUND = "TAB_PROVIDENT_FUND";
	public static boolean TABHOSTCREAT = false;

	public static String mDid;// 
	public static String mDname;// 
	public static final String mLanguage = "zh";// 
	public static String mDeviceVersion;// 
	public static String mAppVersion;// 
	public static String mModel;// 
	public static String mDevicetoken = "fsjlkfs";// PUSH Token
	public static String mFrom;// 
	public static int mNetType;// 
	public static final String PLATFORM = "android";// 
	public static String mAppType = "xxxxx";// 
	public static final String mAppID = "2";// 

	public static final String EXTRAS_FILM_INFO = "com.itotem.kunshan.imovie.file.info";
	public static Location location;

	public Vibrator mVibrator01;
	public static String mAddrStr;// current address

	public static Double longitude;
	public static Double dimension;
	public static boolean isCancel = true;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initHeader();

	}

	private void initHeader() {
		// mMemberId = getUid();
		mAppVersion = getVersion();
		// mImei = getEncryptCode(getIMEI());
		// mDid = getEncryptCode("011472001975695");
		mDname = Build.BRAND;
		mDeviceVersion = Build.VERSION.SDK;
		mModel = Build.MODEL;
	}
	private String getVersion() {
		try {
			return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * IMEI
	 * */
	private String getIMEI() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

	/**
	 * exitAcivity
	 */
	public static void exit(final Activity activity) {
		Builder dialog = new AlertDialog.Builder(activity);
		dialog.setTitle("Are you sure Exit?");
		dialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				int sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
				ActivityManager manager = (ActivityManager) activity
						.getSystemService(ACTIVITY_SERVICE);
				if (sdkVersion < 8) {
					manager.restartPackage(activity.getPackageName());
					System.exit(0);
				} else {
					Intent startMain = new Intent(Intent.ACTION_MAIN);
					startMain.addCategory(Intent.CATEGORY_HOME);
					startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					activity.startActivity(startMain);
					System.exit(0);
				}
			}
		});
		dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});
		dialog.show();
	}


	public static boolean isPicture(String pInput, String pImgeFlag) throws Exception {
		
		if (pInput == null || pInput.equals("")) {
			
			return false;
		}
	
		String tmpName = pInput.substring(pInput.lastIndexOf(".") + 1, pInput.length());
		
		String imgeArray[][] = { { "bmp", "0" }, { "dib", "1" }, { "gif", "2" }, { "jfif", "3" },
				{ "jpe", "4" }, { "jpeg", "5" }, { "jpg", "6" }, { "png", "7" }, { "tif", "8" },
				{ "tiff", "9" }, { "ico", "10" } };
		
		for (int i = 0; i < imgeArray.length; i++) {
			
			if (pImgeFlag != null && imgeArray[i][0].equals(tmpName.toLowerCase())
					&& imgeArray[i][1].equals(pImgeFlag)) {
				return true;
			}
			if (pImgeFlag == null && imgeArray[i][0].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

}
