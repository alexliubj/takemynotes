package ca.techguys.takemynotes.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

import ca.techguys.takemynotes.beans.News;
import ca.techguys.takemynotes.beans.News;

public class DOMPersonService {

	public static ArrayList<News> getNews(String resUrl) throws Exception{
		ArrayList<News> newslList = new ArrayList<News>();
		URL url = new URL(resUrl);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(20*1000);
		InputStream inStream = conn.getInputStream();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document dom = builder.parse(inStream);
		Element root = dom.getDocumentElement();
		NodeList personNodes = root.getElementsByTagName("item");
		for(int i=0 ; i<personNodes.getLength(); i++){
			Element personNode = (Element)personNodes.item(i);
			News news = new News();
//			person.setId(new Integer(personNode.getAttribute("id")));
			NodeList childNodes = personNode.getChildNodes();
			for(int j=0 ; j < childNodes.getLength(); j++){
				Node childNode = childNodes.item(j);
				if(childNode.getNodeType()==Node.ELEMENT_NODE){
					Element element = (Element)childNode;
					if("title".equals(childNode.getNodeName())){
						String name = element.getFirstChild().getNodeValue();
						news.setTitle(name);
					}else if("link".equals(childNode.getNodeName())){
						String age = element.getFirstChild().getNodeValue();
						news.setLink(age);
					}else if("description".equals(childNode.getNodeName())){
						String age = element.getFirstChild().getNodeValue();
						news.setDescription(age);
					}else if("media:content".equals(childNode.getNodeName())){
						String age = element.getAttribute("url");
						news.setImage_large(age);
					}else if("media:thumbnail".equals(childNode.getNodeName())){
						String age = element.getAttribute("url");
						news.setImage_small(age);
					}else if("media:category".equals(childNode.getNodeName())){
						String age = element.getFirstChild().getNodeValue();
						news.setCategory(age);
					}
					else if("content:encoded".equals(childNode.getNodeName())){
						String age = element.getFirstChild().getNodeValue();
						news.setWebData(age);
					}
				}
			}
			newslList.add(news);
		}
		inStream.close();
		return newslList;
	}
}
