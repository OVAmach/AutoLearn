package com.iteamcn.AutoLearn.weblearn.icve.api.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class QuestionInfo {
	private String questionId;
	private String answerContent;
	public QuestionInfo(JSONObject json) throws JSONException{
		this.questionId=json.getString("Id");
		this.answerContent=json.getString("AnswersContent");
	}
	public String getQuestionId() {
		return questionId;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	
}
