package com.iteamcn.AutoLearn.weblearn.icve.api.handler.local;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iteamcn.AutoLearn.weblearn.icve.api.MessageCode;
import com.iteamcn.AutoLearn.weblearn.icve.api.entity.QuestionInfo;
import com.iteamcn.AutoLearn.weblearn.util.InnerMessage;
import com.iteamcn.AutoLearn.weblearn.util.JsonHandler;

public class PaperQuestionsHandler extends JsonHandler {

	public PaperQuestionsHandler(JSONObject json) {
		super(json);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public InnerMessage doHandle() throws JSONException {
		// TODO 自动生成的方法存根
		InnerMessage message=new InnerMessage();
		List<QuestionInfo> list=new ArrayList<QuestionInfo>();
		JSONArray jsa = json.getJSONObject("data").getJSONObject("paper").getJSONArray("PaperQuestions");
		for(int i=0;i<jsa.length();i++){
			list.add(new QuestionInfo(jsa.getJSONObject(i)));
		}
		message.setObject(list);
		message.setCode(MessageCode.SUCCESS);
		return message;
	}

}
