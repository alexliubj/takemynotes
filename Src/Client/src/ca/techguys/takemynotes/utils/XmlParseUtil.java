package ca.techguys.takemynotes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.techguys.takemynotes.beans.News;

import android.util.Log;

/**
 * XML parser will change to JSON.....
 * @author Alex.Liu
 * @date 2014.1.29
 * @email alexliubo@gmail.com
 */
public class XmlParseUtil {
	
	private static HashMap<String, ArrayList> hashMap=new HashMap<String, ArrayList>();

	public static ArrayList<News> getNewsFromNet(String url,boolean isRefresh) throws Throwable{
		if(!isRefresh){
			if(hashMap.get(url)!=null){
				return hashMap.get(url);
			}
		}
		ArrayList<News> newsList = DOMPersonService.getNews(url);
		hashMap.put(url, newsList);
		return newsList;
	}
	
}
