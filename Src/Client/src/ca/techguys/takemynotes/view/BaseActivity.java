package ca.techguys.takemynotes.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;


public abstract class BaseActivity extends Activity {
	protected int mWidth;
	protected int mHeight;
	protected Message message;
	protected String errorStr;
//	protected ImageLoader imageLoader = ImageLoader.getInstance();
	public int screenWidth, screenHeight;
//	protected DisplayImageOptions options;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.booth_special_offers).cacheOnDisc()
//				.imageScaleType(ImageScaleType.EXACT).build();
		WindowManager windowManager = getWindowManager();
	    Display display = windowManager.getDefaultDisplay();
	    screenWidth = display.getWidth();
	    screenHeight = display.getHeight();
	    
	    findViewById();
	    initData();
		setListener();
		
	}
	public abstract void findViewById();
	
	public abstract void initData();
	
	public abstract void setListener();

	public abstract void onClick(View v);
}