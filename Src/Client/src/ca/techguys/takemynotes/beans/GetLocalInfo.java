package ca.techguys.takemynotes.beans;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.util.Log;

public class GetLocalInfo {
	/**
	 * @param c
	 * @return
	 */
	public static String getVersionName(Context c) {
		PackageManager pm = c.getPackageManager();
		PackageInfo pinfo = null;
		try {
			pinfo = pm.getPackageInfo(c.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return pinfo.versionName;
	}

	/**
	 * @param
	 * @return
	 */
	public static int getVersionCode(Context c) {
		PackageManager pm = c.getPackageManager();
		PackageInfo pinfo = null;
		try {
			pinfo = pm.getPackageInfo(c.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return pinfo.versionCode;
	}

	/**
	 */
	public static String getLocalNumber(Context ctx) {
		TelephonyManager tManager = (TelephonyManager) ctx
				.getSystemService(ctx.TELEPHONY_SERVICE);
		String number = tManager.getLine1Number();
		Log.e("zzz", "  " + number);
		return number;
	}

}
