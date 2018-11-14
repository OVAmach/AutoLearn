package com.iteamcn.AutoLearn.weblearn.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.json.JSONObject;

import com.iteamcn.AutoLearn.util.ContentType;
import com.iteamcn.AutoLearn.util.Logger;
import com.iteamcn.AutoLearn.util.NetWorkException;
import com.iteamcn.AutoLearn.weblearn.IHttpMethod;

public abstract class HttpHandler implements IHttpMethod{
	private Cookies cookies=new Cookies();

	public Cookies getCookies() {
		return cookies;
	}

	public void setCookies(Cookies cookies) {
		this.cookies = cookies;
	}
	public void handleCookie(Header[] header){
		for(Header h:header){
			if(h.getName().equalsIgnoreCase("Set-Cookie")){
				String[] c=h.getValue().split(";")[0].split("=");
				if(c.length==2){
					cookies.setCookie(c[0], c[1], true);
				}else{
					cookies.removeCookie(c[0]);
				}
				
			}
		}
	}
	public HttpMessage getResponseContent(HttpMethodBase method) throws Exception {
		HttpMessage message=new HttpMessage();
		switch (method.getStatusCode()) {
		case HttpStatus.SC_OK:
			String contentType=method.getResponseHeader("Content-Type").getValue().split(";")[0];
			if(ContentType.JSON.equalsIgnoreCase(contentType)) {
					message.setContentType(ContentType.JSON);
					BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));  
					StringBuffer stringBuffer = new StringBuffer();  
					String str = "";  
					while((str = reader.readLine())!=null){  
						stringBuffer.append(str);  
					}  
					String ts = stringBuffer.toString();  
					message.setContent(new JSONObject(ts));
			}else if(ContentType.JPEG.equalsIgnoreCase(contentType)) {
					message.setContentType(ContentType.JPEG);
					message.setContent(method.getResponseBody());
			}else {
					Logger.getLogger().error("未处理的ContentType:"+contentType);
					//throw new NetWorkException();
			}
			break;

		default:
			Logger.getLogger().error("未处理的Http状态:"+method.getStatusCode());
			break;
		}
		return message;
	}
}
