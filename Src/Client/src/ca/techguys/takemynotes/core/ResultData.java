package ca.techguys.takemynotes.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.graphics.Bitmap;

/**
 * Request result
 * @author Alex.Liu
 * @date 2014.1.29
 * @email alexliubo@gmail.com
 */
@SuppressWarnings({ "rawtypes","unused" })
public class ResultData
{
  private static boolean dataReady = false;
  public static  boolean hasCache=false;
  public static  boolean passInMore=false;
  protected static final String ERROR_NETWROK_TIMEOUT = "NetworkTimeout";
//  private static ArrayList result = new ArrayList();
  private static HashMap<Integer,ArrayList> resultMap = new HashMap<Integer,ArrayList>();
  public static boolean isDataReady(int taskid)
  {
	  ArrayList result =getResult(taskid);
	  return (result!=null && !result.isEmpty());
  }

  public static ArrayList getResult(int taskid)
  {
	 ArrayList result = resultMap.get(taskid);
	 if(result==null){
		 result = new ArrayList();
		 resultMap.put(taskid, result);
	 }
    return result;
  }
  public static boolean isNetworkTimeout(int taskid){
	  ArrayList result = resultMap.get(taskid);
	  if(result!=null){
		try {
			Object s = result.get(result.size()-1);
			return (ERROR_NETWROK_TIMEOUT.equals(s));
		} catch (Exception e) {
		}
	  }
	  return false;
  }
  public final static HashMap<String,Bitmap> imageMaps = new HashMap<String,Bitmap>();

  public static Bitmap getImage(String picurl){
	  return imageMaps.get(picurl);
  }
  public static void putImage(String picurl,Bitmap bm){
	  imageMaps.put(picurl, bm);
  }
  
  public static void clearImage(){
	  if(null!=imageMaps && !imageMaps.isEmpty()){
		  Iterator iter = imageMaps.entrySet().iterator();
		  while(iter.hasNext())
		  {
			  Map.Entry entry = (Map.Entry)iter.next();
			  Bitmap value = (Bitmap)entry.getValue();
			  if(null!=value)
			  {
				  value = null;
			  }
		  }
		  imageMaps.clear();
	  }
  }

}
