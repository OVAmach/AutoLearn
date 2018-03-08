package com.iteamcn.AutoLearn.weblearn.icve.api.handler;

import java.io.IOException;
import java.util.Random;

import com.iteamcn.AutoLearn.util.ContentType;
import com.iteamcn.AutoLearn.util.Logger;
import com.iteamcn.AutoLearn.weblearn.icve.api.MessageCode;
import com.iteamcn.AutoLearn.weblearn.icve.api.Url;
import com.iteamcn.AutoLearn.weblearn.util.ApiArgs;
import com.iteamcn.AutoLearn.weblearn.util.ApiHandler;
import com.iteamcn.AutoLearn.weblearn.util.HttpHandler;
import com.iteamcn.AutoLearn.weblearn.util.HttpMessage;
import com.iteamcn.AutoLearn.weblearn.util.InnerMessage;

public class VerifyCodeHandler extends ApiHandler {

	public VerifyCodeHandler(HttpHandler httpHandler) {
		super(httpHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InnerMessage doHttp(ApiArgs arg) throws Exception {
		// TODO Auto-generated method stub
		return doHttp(arg.argsToString(),false);

	}

	@Override
	public InnerMessage doHttp(String arg,boolean withoutArgHead) throws Exception {
		// TODO 自动生成的方法存根
		InnerMessage newMessage=new InnerMessage();
		HttpMessage message = null;
		try{
			message=httpHandler.get(Url.GET_VERIFY_CODE, "t="+new Random().nextFloat());
			if(message.getContentType().equals(ContentType.JPEG)){
				newMessage.setContent(message.getContent());
				newMessage.setCode(MessageCode.SUCCESS);
				
			}else{
				newMessage.setCode(MessageCode.NET_FAILED);
			}
		}catch(IOException e){
			Logger.getLogger().error("网络错误",e.fillInStackTrace());
			newMessage.setCode(MessageCode.NET_ERROR);
		}catch(Exception e){
			Logger.getLogger().error("内部错误",e.fillInStackTrace());
			newMessage.setCode(MessageCode.INTERNAL_ERROR);
		}

		return newMessage;
	}

}
