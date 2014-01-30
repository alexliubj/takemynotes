package ca.techguys.takemynotes.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import ca.techguys.takemynotes.beans.DataUtil;
import ca.techguys.takemynotes.beans.ThreadCallback;
//import ca.techguys.takemynotes.net.SystemAPI;
import ca.techguys.takemynotes.utils.ProgressBarDialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;


/**
 * Main Thread
 * @author Alex.Liu
 * @date 2014.1.29
 * @email alexliubo@gmail.com
 */
@SuppressWarnings({ "rawtypes", "unchecked","unused" })
public class MainThread
{
  private static final String path=Environment.getExternalStorageDirectory().getAbsolutePath()+"/stranger/friend/pic/";
  
  //-1 - not work, 0 - false, 1 - true
  public static int globalShowProgressBarStatus = -1;
  
  //private static SystemAPI systemApi = new SystemAPI();
  public static Context mContext;
  private static HashMap<Integer,Context> contextMap = new HashMap<Integer,Context>();
  public static boolean flag_dialog=false;
  
  public final static int DOWNLOADIMAGE=0;
  
  
  public final static int FRIEND_APPELLEE = 1009;
  

	private static Handler handler = new Handler() {
	      @Override
	      public void handleMessage(Message msg) {
	    	  
	    	  Bundle msgparas = msg.getData();
	    	  ThreadCallback taskCallback = (ThreadCallback)msgparas.get("cb");
	    	  int taskid = msgparas.getInt("taskid");
	    	  boolean isShowProgressBar = msgparas.getBoolean("isShowProgressBar");
	     	  if(taskCallback!=null){
	     		  taskCallback.onCallbackFromThread(taskid);
	     		  if(isShowProgressBar){
	     			  DataUtil.setProgressBarDialogShowFlag(false);
	     			  if(flag_dialog){
	     			  }else{
	     				  ProgressBarDialog.closePage();
	     				  flag_dialog=false;
	     			  }
	     		  }
	    	  }
	      }
	};
	final static ThreadPool threadPool = new ThreadPool(1);
	final static ThreadPool threadPool_image = new ThreadPool(5);

	public static void stopAllTask(){
		threadPool.stop();
	}
	public static void stopCurrentTask(){
		threadPool.restart();
	}
 	private static void startWork(final ThreadCallback cb,final int taskid,final ArrayList parameters,final boolean isShowProgressBar){
		try {
			Runnable task = new Runnable() {
				public void run() {
					runTask(taskid, parameters);
					Bundle msgparas = new Bundle();
					msgparas.putSerializable("cb", cb);
					msgparas.putInt("taskid", taskid);
					msgparas.putBoolean("isShowProgressBar", isShowProgressBar);
					Message msg = new Message();
					msg.what = 0;
					msg.setData(msgparas);
					handler.sendMessage(msg);
				}
			};
			threadPool.execute(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
  	}
 	
 	public static Bitmap getPicByName(String name){
 		String picName=name.substring(name.lastIndexOf("/")+1);
 		
 		File file=new File(path+picName);
 		if(file.exists()){
 			Bitmap bitmap=BitmapFactory.decodeFile(path+picName);
 			return bitmap;
 		}else{
 			return null;
 		}
 	}
 	
	public static Bitmap getHttpImage(final ThreadCallback cb,final String picurl){
		
		Bitmap bm = ResultData.imageMaps.get(picurl);
		if(bm != null){
			if(cb!=null){
//				cb.onCallbackFromThread(DOWNLOADIMAGE);
			}
			return bm;
		}
		
/*		//sd reader
		Bitmap bitmap=getPicByName(picurl);
		if(bitmap!=null){
			ResultData.putImage(picurl, bitmap);
			return bitmap;
		}*/
		
		//get image...
		try {
			Runnable task = new Runnable() {
				public void run() {
					
					/* important********
					Bitmap bm = systemApi.downloadImage(picurl);
					ResultData.putImage(picurl, bm);
					Bundle msgparas = new Bundle();
					msgparas.putSerializable("cb", cb);
					msgparas.putInt("taskid", DOWNLOADIMAGE);
					Message msg = new Message();
					msg.what = 1;//download image
					msg.setData(msgparas);
					handler.sendMessage(msg);
					*/
				}
			};
			
			threadPool_image.execute(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void savePic(Bitmap bitmap, String picName) {
		
		File file=new File(path+picName);
		FileOutputStream outputStream;
//		Log.i("test", file.exists()+"");
		if(!file.exists()){
			try {
				file.getParentFile().mkdirs();  
				file.createNewFile();
				
				outputStream = new FileOutputStream(file);
//				Log.i("test", path+picName);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
				outputStream.close();
			} catch (Exception e) {
//				Log.e("", e.toString());
			}
		}
	}
	
 	private static void runTask(final int taskid,final ArrayList parms){
    	ArrayList result = ResultData.getResult(taskid);
    	result.clear();
        switch (taskid)
        {
	        case 1:
	        	break;
	        default:
			break;	 		
        }
        Boolean localTimeOutFlag = DataUtil.localTimeout.get();
        if(localTimeOutFlag!=null && localTimeOutFlag){
        	result.add(ResultData.ERROR_NETWROK_TIMEOUT);
        }
  	}
}
