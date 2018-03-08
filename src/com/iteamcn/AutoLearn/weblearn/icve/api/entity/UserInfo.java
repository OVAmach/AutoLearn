package com.iteamcn.AutoLearn.weblearn.icve.api.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfo {
	private String nickName;
	private String userId;
	
	public UserInfo(String nickName, String userId) {
		this.nickName = nickName;
		this.userId = userId;
	}
	public UserInfo(JSONObject json) throws JSONException{
		this.nickName=json.getString("nickname");
		this.userId=json.getString("userid");
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
