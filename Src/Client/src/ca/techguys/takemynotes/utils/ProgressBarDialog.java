package ca.techguys.takemynotes.utils;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.beans.DataUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Progress bar
 * @author Alex.Liu
 * @date 2014.1.29
 * @email alexliubo@gmail.com
 */
public class ProgressBarDialog 
{
 
  private static ProgressDialog pdialog;

  public static synchronized void closePage(){
	  try {
		if(pdialog != null){
			  if(pdialog.isShowing())
				  pdialog.dismiss();
			  	  pdialog = null;
		  }
		
	} catch (Exception e) {
		 pdialog = null; 
		e.printStackTrace();
	}
  }
  public static boolean isshowing(){
	  return (pdialog != null && pdialog.isShowing());
  }
  public static void show(Context context){
	 try {
			//Log.v("ProgressDialog show...","1");

		if(null == pdialog && null != context){
			DataUtil.setProgressBarDialogShowFlag(true);
			Activity ui = ((Activity)context);
			pdialog = ProgressDialog.show(ui,ui.getString(R.string.wait),ui.getString(R.string.dataload),false,true);
		 }
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
}
