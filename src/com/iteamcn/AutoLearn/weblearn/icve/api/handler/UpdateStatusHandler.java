package com.iteamcn.AutoLearn.weblearn.icve.api.handler;

import java.io.IOException;

import com.iteamcn.AutoLearn.util.ContentType;
import com.iteamcn.AutoLearn.util.Encoding;
import com.iteamcn.AutoLearn.util.Logger;
import com.iteamcn.AutoLearn.weblearn.icve.api.MessageCode;
import com.iteamcn.AutoLearn.weblearn.icve.api.Url;
import com.iteamcn.AutoLearn.weblearn.util.ApiArgs;
import com.iteamcn.AutoLearn.weblearn.util.ApiHandler;
import com.iteamcn.AutoLearn.weblearn.util.HttpHandler;
import com.iteamcn.AutoLearn.weblearn.util.InnerMessage;

public class UpdateStatusHandler extends ApiHandler {

	public UpdateStatusHandler(HttpHandler httpHandler) {
		super(httpHandler);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public InnerMessage doHttp(ApiArgs arg) throws Exception {
		// TODO 自动生成的方法存根
		return doHttp(arg.argsToString(),false);
	}

	@Override
	public InnerMessage doHttp(String arg,boolean withoutArgHead) throws Exception {
		// TODO 自动生成的方法存根
		InnerMessage newMessage=new InnerMessage();
		try{
			if(withoutArgHead){
				httpHandler.post(Url.POST_UPDATE_STATUS,"cellId="+arg+"&learntime=99999999&status=2", ContentType.FORM, Encoding.UTF8);
				httpHandler.post(Url.POST_UPDATE_STATUS,"cellId="+arg+"&learntime=99999999&status=1", ContentType.FORM, Encoding.UTF8);
				httpHandler.post(Url.POST_UPDATE_STATUS,"cellId="+arg+"&learntime=99999999&status=0", ContentType.FORM, Encoding.UTF8);
			}else{
				httpHandler.post(Url.POST_UPDATE_STATUS,arg, ContentType.FORM, Encoding.UTF8);
				httpHandler.post(Url.POST_UPDATE_STATUS,arg, ContentType.FORM, Encoding.UTF8);
				httpHandler.post(Url.POST_UPDATE_STATUS,arg, ContentType.FORM, Encoding.UTF8);
			}

			newMessage.setCode(MessageCode.SUCCESS);
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
