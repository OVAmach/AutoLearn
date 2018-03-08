package com.iteamcn.AutoLearn.weblearn.icve;
import com.iteamcn.AutoLearn.weblearn.IWebLearn;
import com.iteamcn.AutoLearn.weblearn.SimpleLoginInfo;
import com.iteamcn.AutoLearn.weblearn.util.HttpHandler;
public class Icve implements IWebLearn{
	private HttpHandler httpHandler=new IcveHttpHandler();
	@Override
	public int login(SimpleLoginInfo info) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void learnAll() {
		// TODO Auto-generated method stub
		
	}

	public HttpHandler getHttpHandler() {
		return httpHandler;
	}

	public void setHttpHandler(HttpHandler httpHandler) {
		this.httpHandler = httpHandler;
	}
	


}
