package com.iteamcn.AutoLearn.weblearn.icve;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import com.iteamcn.AutoLearn.weblearn.util.HttpHandler;
import com.iteamcn.AutoLearn.weblearn.util.HttpMessage;

public class IcveHttpHandler extends HttpHandler {
	@Override
	public HttpMessage post(String url, String content, String contentType,
			String encoding) throws Exception {
		// TODO 自动生成的方法存根
		HttpMessage message=null;
		HttpClient httpClient = new HttpClient();  
		PostMethod post = new PostMethod(url);  
		post.setRequestHeader("Accept","*/*");
		//      post.setRequestHeader("Accept-Encoding","gzip, deflate");
		post.setRequestHeader("Accept-Language","zh-cn,zh;q=0.8");
		post.setRequestHeader("Connection","keep-alive");
		post.setRequestHeader("Content-Length",content.length()+"");
		post.setRequestHeader("X-Requested-With","XMLHttpRequest");
		post.setRequestHeader("Cookie",getCookies().getAllCookie()); 
		post.setRequestHeader("Host","www.icve.com.cn");  
		post.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:50.0) Gecko/20100101 Firefox/50.0");
		RequestEntity entity = new StringRequestEntity(content, contentType, encoding);  
		post.setRequestEntity(entity);  
		httpClient.executeMethod(post);
		
		//开始处理Response
		handleCookie(post.getResponseHeaders());
//		message.addMessage("statusCode", post.getStatusCode());
		message=getResponseContent(post);
		
		return message;
	}

	@Override
	public HttpMessage get(String url, String content) throws Exception {
		// TODO 自动生成的方法存根
		HttpMessage message=null;
		HttpClient httpClient = new HttpClient();  
		GetMethod get = new GetMethod(url+"?"+content);  
		get.setRequestHeader("Accept","*/*");
		//      post.setRequestHeader("Accept-Encoding","gzip, deflate");
		get.setRequestHeader("Accept-Language","zh-cn,zh;q=0.8");
		get.setRequestHeader("Connection","keep-alive");
		get.setRequestHeader("Cookie",getCookies().getAllCookie()); 
		get.setRequestHeader("Host","www.icve.com.cn");  
		get.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:50.0) Gecko/20100101 Firefox/50.0");
		httpClient.executeMethod(get);
		message=getResponseContent(get);
		//开始处理Response
		handleCookie(get.getResponseHeaders());
		return message;
	}


}
