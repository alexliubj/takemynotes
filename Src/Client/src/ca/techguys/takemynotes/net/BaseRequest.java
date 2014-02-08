package ca.techguys.takemynotes.net;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.text.TextUtils;
import android.util.Log;

public class BaseRequest {
	private DefaultHttpClient client;
	private int connectTimeout;
	public BaseRequest(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public BaseRequest() {
		this.connectTimeout = 5000;
		// ------------------------------------------------------
		HttpParams params = new BasicHttpParams();

		ConnManagerParams.setTimeout(params, 1000);

		HttpConnectionParams.setConnectionTimeout(params, 50000);
	
		HttpConnectionParams.setSoTimeout(params, 20000);

		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);
		client = new DefaultHttpClient(conMgr, params);
	}

	/**

	 * @param params
	 * @param url
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public String getRequestByHttpClient(List<NameValuePair> params, String url)
			throws HttpException, IOException {
		String result = null;

		String strURL;

		StringBuffer sb = new StringBuffer();
		if (params != null && params.size() > 0) {
			for (NameValuePair nvp : params) {
				String value = URLEncoder.encode(nvp.getValue(), HTTP.UTF_8);
				if (nvp.getName().equals("oauth_verifier")) {
					value = nvp.getValue();
				}
				sb.append(nvp.getName()).append('=').append(value).append('&');
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		String paramsStr = sb.toString();
		if (paramsStr != null && !paramsStr.equals("")) {
			strURL = url + "?" + paramsStr;
		} else {
			strURL = url;

		}

		HttpGet request = new HttpGet(strURL);
		HttpResponse response = client.execute(request);
		int httpStatusCode = response.getStatusLine().getStatusCode();
		if (httpStatusCode == HttpStatus.SC_OK) {
			result = EntityUtils.toString(response.getEntity());
		} else {
			throw new HttpException("Error Response:" + response.getStatusLine().toString());
		}

		return result;
	}

	/**
	 * get
	 * @param url
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public InputStream getRequestByHttpClient(String url) throws HttpException, IOException {
		InputStream result = null;
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		int httpStatusCode = response.getStatusLine().getStatusCode();
		if (httpStatusCode == 200) {
			result = response.getEntity().getContent();
		} else {
			throw new HttpException("Error Response:" + response.getStatusLine().toString());
		}
		return result;
	}

	/**

	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws HttpException
	 * @throws TimeoutException
	 */
	public String postRequestByHttpClient(List<NameValuePair> params, String url)
			throws IOException, TimeoutException {
		String result = null;
		HttpPost request = new HttpPost(url);
		HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
		request.setEntity(entity);
		HttpResponse response = client.execute(request);
		int httpStatusCode = response.getStatusLine().getStatusCode();
		if (httpStatusCode == HttpStatus.SC_OK) {
			result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		} else {
			throw new TimeoutException();
		}
		return result;
	}

	/**

	 * @param stringParams
	 * @param fileParams
	 * @param url
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
//	public String postRequestByHttpClient(List<NameValuePair> stringParams,
//			List<NameValuePair> fileParams, String url) throws HttpException, IOException {
//		String result = null;
//		HttpPost request = new HttpPost(url);
//		MultipartEntity entity = new MultipartEntity();
//		for (NameValuePair snv : stringParams) {
//			entity.addPart(snv.getName(), new StringBody(snv.getValue(), Charset.forName("UTF-8")));
//		}
//
//		for (NameValuePair fnv : fileParams) {
//			File file = new File(fnv.getValue());
//			if (file.isFile() && !file.isDirectory()) {
//				entity.addPart(fnv.getName(), new FileBody(file));
//			}
//		}
//
//		request.setEntity(entity);
//
//		HttpResponse response = client.execute(request);
//
//		int httpStatusCode = response.getStatusLine().getStatusCode();
//		if (httpStatusCode == HttpStatus.SC_OK) {
//			result = EntityUtils.toString(response.getEntity());
//		} else {
//			throw new HttpException("Error Response:" + response.getStatusLine().toString());
//		}
//
//		return result;
//	}

	/**

	 * @param params

	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws HttpException
	 * @author lvmeng
	 */

	private InputStream getConnRequest(String params, String url) throws IOException {

		String strURL = (TextUtils.isEmpty(params)) ? url : (url + "?" + params);

		HttpURLConnection connect = null;
		try {
			connect = (HttpURLConnection) new URL(strURL).openConnection();
			connect.setRequestMethod("GET");
			connect.setConnectTimeout(connectTimeout);
			connect.connect();
			int code = connect.getResponseCode();
			if (code == 200) {
				return connect.getInputStream();
			} else {
				throw new IOException("Error Response:" + code);
			}
		} catch (IOException e) {
			if (e instanceof SocketTimeoutException) {
				throw new SocketTimeoutException("Time out");
			} else {
				throw e;
			}
		} finally {
			if (connect != null) {
				connect.disconnect();
			}
		}

	}

	/**

	 * @throws IOException
	 * @author lvmeng
	 */
	public InputStream getConnRequest(List<NameValuePair> params, String url) throws IOException {

		if (params != null && params.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (NameValuePair nvp : params) {
				String name = nvp.getName();
				String value = (name.equals("oauth_verifier")) ? nvp.getValue() : URLEncoder
						.encode(nvp.getValue(), HTTP.UTF_8);
				sb.append(nvp.getName()).append('=').append(value).append('&');
			}
			sb.deleteCharAt(sb.length() - 1);
			String paramsStr = sb.toString();
			if (paramsStr != null && !paramsStr.equals("")) {
				return getConnRequest(paramsStr, url);
			} else {
				return getConnRequest("", url);
			}
		} else {
			return getConnRequest("", url);
		}
	}


	public InputStream postConnRequest(String param, String url) throws IOException {
		if (TextUtils.isEmpty(param))
			throw new IOException("Error :");
		HttpURLConnection connect = null;
		try {
			connect = (HttpURLConnection) new URL(url).openConnection();
			connect.setConnectTimeout(connectTimeout);
			connect.setDoInput(true);
			connect.setDoOutput(true);
			byte[] formData = param.getBytes();
			connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connect.setRequestProperty("Content-Length", formData.length + "");
			connect.connect();
			OutputStream output = connect.getOutputStream();
			output.write(formData);
			output.flush();
			output.close();

			int code = connect.getResponseCode();
			if (code == 200) {
				return connect.getInputStream();
			} else {
				throw new IOException("" + code);
			}
		} catch (IOException e) {
			if (e instanceof SocketTimeoutException) {
				throw new SocketTimeoutException("");
			} else {
				throw e;
			}

		} finally {
			if (connect != null) {
				connect.disconnect();
			}
		}
	}


	public InputStream postConnRequest(List<NameValuePair> params, String url) throws IOException {
		if (params == null || params.size() == 0)
			throw new IOException("Error : ");

		StringBuffer sb = new StringBuffer();
		for (NameValuePair nvp : params) {
			String name = nvp.getName();
			String value = (name.equals("oauth_verifier")) ? nvp.getValue() : URLEncoder.encode(
					nvp.getValue(), HTTP.UTF_8);
			sb.append(nvp.getName()).append('=').append(value).append('&');
		}
		sb.deleteCharAt(sb.length() - 1);
		return postConnRequest(new String(sb), url);
	}

	public String inputToString(InputStream inputStream, String encoding) throws IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		inputStream.close();
		bos.close();
		if (TextUtils.isEmpty(encoding)) {
			encoding = "UTF-8";
		}
		return new String(bos.toByteArray(), encoding);
	}

}
