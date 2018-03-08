package com.iteamcn.AutoLearn.weblearn.moke;
import org.json.JSONObject;


public class LoginInfo {
	private int orgId;
	private String password;
	private boolean rememberMe;
	private String type;
	private String username;
	public LoginInfo(int orgId, String username, String password,
			String type, boolean rememberMe) {
		this.orgId = orgId;
		this.password = password;
		this.rememberMe = rememberMe;
		this.type = type;
		this.username = username;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String toJson(){
		JSONObject json=new JSONObject();
		json.put("orgId", orgId);
		json.put("username", username);
		json.put("password", password);
		json.put("type", type);
		json.put("rememberMe", rememberMe);
		return json.toString();
	}
	
}
