package ca.techguys.takemynotes.beans;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import ca.techguys.takemynotes.R;
import ca.techguys.takemynotes.core.MainThread;
import ca.techguys.takemynotes.core.ResultData;
import ca.techguys.takemynotes.core.ThreadPool;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncImageLoader {
	private static Map<String, SoftReference<Drawable>> imageCache=new HashMap<String, SoftReference<Drawable>>();
	
	static ThreadPool threadPool_image = new ThreadPool(50);
	
	
	
	
	public static void clearImageMap(){
		if(imageCache!=null && imageCache.size()>100){//当缓存大于100的时候
			imageCache.clear();
		}
	}
	
	
	public static Drawable loadDrawable(final String imageUrl,final ImageCallback callback){
		if(imageCache.containsKey(imageUrl)){
			SoftReference<Drawable> softReference=imageCache.get(imageUrl);
			if(softReference.get()!=null){
				return softReference.get();
			}
		}
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				callback.imageLoaded((Drawable) msg.obj, imageUrl);
			}
		};
		Runnable task = new Runnable() {
			public void run() {
				Drawable drawable=loadImageFromUrl(imageUrl);
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				handler.sendMessage(handler.obtainMessage(0,drawable));
			};
		};
		try {
			threadPool_image.execute(task);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Drawable loadDrawable(String imageUrl){
		if(imageCache.containsKey(imageUrl)){
			SoftReference<Drawable> softReference=imageCache.get(imageUrl);
			if(softReference.get()!=null){
				return softReference.get();
			}else{
				return null;
			}
		}
		return null;
	}
	
	protected static Drawable loadImageFromUrl(String imageUrl) {
		try {
			URL url = new URL(imageUrl);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(10*1000);
			conn.connect();
			InputStream inputStream = conn.getInputStream();
			Drawable drawable = Drawable.createFromStream(inputStream, "src");
			inputStream.close();
			conn.disconnect();
			//************
			/*InputStream inputStream = new URL(imageUrl).openStream();
			Drawable drawable = Drawable.createFromStream(inputStream, "src");
			inputStream.close();*/
			return drawable;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public interface ImageCallback{
		public void imageLoaded(Drawable imageDrawable,String imageUrl);
	}
}
