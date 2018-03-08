package com.iteamcn.AutoLearn.weblearn.util;

public class HttpMessage extends Message {
	public HttpMessage(){
		super();
		this.addMessage("Content-Type", null);
		this.addMessage("Content",null);
	}
	public String getContentType(){
		Object contentType=this.getMessage("Content-Type");
		return contentType instanceof String?(String)contentType:null;
	}
	public Object getContent(){
		return this.getMessage("Content");
	}
	public void setContentType(String contentType){
		this.addMessage("Content-Type", contentType, true);
	}
	public void setContent(Object content){
		this.addMessage("Content", content, true);
	}
}
