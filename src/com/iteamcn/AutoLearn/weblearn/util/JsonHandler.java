package com.iteamcn.AutoLearn.weblearn.util;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class JsonHandler {
	protected JSONObject json;
	public JsonHandler(JSONObject json){
		this.json=json;
	}
	public abstract InnerMessage doHandle() throws JSONException;
}
