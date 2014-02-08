package ca.techguys.takemynotes.beans;

import android.app.Activity;
import android.widget.Toast;

public class ToastUtil {
	public static void showText(final Activity activity, final String content) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(activity, content,
						Toast.LENGTH_SHORT);
				toast.show();
			}
		});
	}

	public static void showText(final Activity activity, final int stringId) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(activity, activity.getResources()
						.getString(stringId), Toast.LENGTH_SHORT);
				toast.show();
			}
		});
	}
}
