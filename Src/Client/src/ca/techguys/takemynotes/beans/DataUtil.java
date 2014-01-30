package ca.techguys.takemynotes.beans;

import android.location.Location;

/**
 * Data collection
 * @author Alex.Liu
 * @date 2014.1.29
 * @email alexliubo@gmail.com
 */
public class DataUtil {
	
	public static boolean ProgressBarDialogShowFlag = false;
	public static ThreadLocal<Boolean> localTimeout = new ThreadLocal<Boolean>();
	public static ApplicationData applicationData;
	
	public static boolean isProgressBarDialogShowFlag() {
		return ProgressBarDialogShowFlag;
	}
	public static void setProgressBarDialogShowFlag(
			boolean progressBarDialogShowFlag) {
		ProgressBarDialogShowFlag = progressBarDialogShowFlag;
	}
}
