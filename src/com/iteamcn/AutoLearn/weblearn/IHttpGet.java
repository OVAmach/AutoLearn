package com.iteamcn.AutoLearn.weblearn;

import com.iteamcn.AutoLearn.weblearn.util.HttpMessage;

public interface IHttpGet {
	public HttpMessage get(String url,String content) throws Exception;
}
