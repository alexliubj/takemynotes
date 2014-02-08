package ca.techguys.takemynotes.beans;
/**
 * 
 * @author fancy
 *
 */
public class LogUtil {
	public static final String TAG = "MeiJiKa";
	public static boolean canPrint = true;

	public static void i(String msg) {
		if(canPrint)
			android.util.Log.i(TAG, msg);
	}
	
	public static void e(String msg) {
		if(canPrint)
			android.util.Log.e(TAG, msg);
	}
	public static void i(String tag,String msg) {
		if(canPrint)
			android.util.Log.i(tag, msg);
	}
	
	public static void e(String tag,String msg) {
		if(canPrint)
			android.util.Log.e(tag, msg);
	}
	
}
