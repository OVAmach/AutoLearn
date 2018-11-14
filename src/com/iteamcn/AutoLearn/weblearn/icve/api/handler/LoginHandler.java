package com.iteamcn.AutoLearn.weblearn.icve.api.handler;

import java.io.IOException;
import java.net.URLDecoder;

import org.json.JSONObject;

import com.iteamcn.AutoLearn.util.ContentType;
import com.iteamcn.AutoLearn.util.Encoding;
import com.iteamcn.AutoLearn.util.Logger;
import com.iteamcn.AutoLearn.weblearn.icve.api.ApiCode;
import com.iteamcn.AutoLearn.weblearn.icve.api.MessageCode;
import com.iteamcn.AutoLearn.weblearn.icve.api.Url;

import com.iteamcn.AutoLearn.weblearn.icve.api.entity.UserInfo;
import com.iteamcn.AutoLearn.weblearn.util.ApiArgs;
import com.iteamcn.AutoLearn.weblearn.util.ApiHandler;
import com.iteamcn.AutoLearn.weblearn.util.HttpHandler;
import com.iteamcn.AutoLearn.weblearn.util.HttpMessage;
import com.iteamcn.AutoLearn.weblearn.util.InnerMessage;

public class LoginHandler extends ApiHandler {

	public LoginHandler(HttpHandler httpHandler) {
		super(httpHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InnerMessage doHttp(ApiArgs arg) throws Exception {
		// TODO Auto-generated method stub+
		return doHttp(arg.argsToString(),false);
	}

	@Override
	public InnerMessage doHttp(String arg,boolean withoutArgHead) throws Exception {
		// TODO 自动生成的方法存根
		InnerMessage newMessage=new InnerMessage();
		if(withoutArgHead)return newMessage;
		HttpMessage message = null;
		try{
			message=httpHandler.post(Url.POST_LOGIN, arg, ContentType.FORM, Encoding.UTF8);
			if(message.getContentType().equals(ContentType.JSON)){
				JSONObject json=(JSONObject)message.getContent();
				if(json.getInt("code")==ApiCode.LOGIN_SUCCESS){
					json.put("nickname", URLDecoder.decode(json.optString("nickname","null"), Encoding.UTF8));
				}
				newMessage.setObject(new UserInfo(json));
				newMessage.setContent(json);
				newMessage.setCode(MessageCode.SUCCESS);
			}else{
				newMessage.setCode(MessageCode.NET_FAILED);
			}
		}catch(IOException e){
			e.printStackTrace();
			Logger.getLogger().error("网络错误",e.fillInStackTrace());
			newMessage.setCode(MessageCode.NET_ERROR);
		}catch(Exception e){
			e.printStackTrace();
			Logger.getLogger().error("内部错误",e.fillInStackTrace());
			newMessage.setCode(MessageCode.INTERNAL_ERROR);
		}

		return newMessage;
	}
	
}
