package com.iteamcn.AutoLearn.weblearn.icve.api.handler;

import java.io.IOException;

import org.json.JSONObject;

import com.iteamcn.AutoLearn.util.ContentType;
import com.iteamcn.AutoLearn.util.Encoding;
import com.iteamcn.AutoLearn.util.Logger;
import com.iteamcn.AutoLearn.weblearn.icve.api.MessageCode;
import com.iteamcn.AutoLearn.weblearn.icve.api.Url;
import com.iteamcn.AutoLearn.weblearn.util.ApiArgs;
import com.iteamcn.AutoLearn.weblearn.util.ApiHandler;
import com.iteamcn.AutoLearn.weblearn.util.HttpHandler;
import com.iteamcn.AutoLearn.weblearn.util.HttpMessage;
import com.iteamcn.AutoLearn.weblearn.util.InnerMessage;

public class AnswerPaperHandler extends ApiHandler {

	public AnswerPaperHandler(HttpHandler httpHandler) {
		super(httpHandler);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public InnerMessage doHttp(ApiArgs arg) throws Exception {
		// TODO 自动生成的方法存根
		return doHttp(arg.argsToString(), false);
	}

	@Override
	public InnerMessage doHttp(String arg,boolean withoutArgHead) throws Exception {
		// TODO 自动生成的方法存根
		InnerMessage newMessage=new InnerMessage();
		HttpMessage message = null;
		if(withoutArgHead)return newMessage;
		try{
			message=httpHandler.post(Url.POST_ANSWER_PAPER,arg, ContentType.FORM, Encoding.UTF8);
			if(message.getContentType().equals(ContentType.JSON)){
				newMessage.setObject((JSONObject)message.getContent());
				newMessage.setContent((JSONObject)message.getContent());
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
