package com.iteamcn.AutoLearn.weblearn.util;

public class InnerMessage extends Message {
	private Object content;
	private Object object;
	private int code;
	public InnerMessage() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
