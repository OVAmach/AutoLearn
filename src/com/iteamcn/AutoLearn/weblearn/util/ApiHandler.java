package com.iteamcn.AutoLearn.weblearn.util;

public abstract class ApiHandler {
	protected HttpHandler httpHandler=null;
	public ApiHandler(HttpHandler httpHandler){
		this.httpHandler=httpHandler;
	}
	public abstract InnerMessage doHttp(ApiArgs arg) throws Exception;
	public abstract InnerMessage doHttp(String arg,boolean withoutArgHead) throws Exception;
	public HttpHandler getHttpHandler(){
		return this.httpHandler;
	}
}
