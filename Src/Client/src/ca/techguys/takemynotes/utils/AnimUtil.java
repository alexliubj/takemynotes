package ca.techguys.takemynotes.utils;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * For animation
 * @author Alex.Liu
 * @date 2014.1.29
 * @email alexliubo@gmail.com
 */

public class AnimUtil {

	public static Animation getInAnim(){
		Animation inFromRight = new TranslateAnimation(Animation.ABSOLUTE,
				+320.0f, Animation.ABSOLUTE, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(850);
		inFromRight.setInterpolator(new AccelerateDecelerateInterpolator());
		return inFromRight;
	}
	public static Animation getOutAnim(){
		Animation outtoLeft = new TranslateAnimation(Animation.ABSOLUTE, 0.0f,
				Animation.ABSOLUTE, -320.0f, Animation.RELATIVE_TO_PARENT,
				0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(850);
		outtoLeft.setInterpolator(new AccelerateDecelerateInterpolator());
		return outtoLeft;
	}
	
}
