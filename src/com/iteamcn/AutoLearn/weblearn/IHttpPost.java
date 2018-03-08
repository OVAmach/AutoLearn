package com.iteamcn.AutoLearn.weblearn;

import com.iteamcn.AutoLearn.weblearn.util.HttpMessage;

public interface IHttpPost{
	public HttpMessage post(String url,String content,String contentType,String encoding) throws Exception;
}
