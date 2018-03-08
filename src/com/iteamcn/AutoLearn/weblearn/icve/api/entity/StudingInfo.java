package com.iteamcn.AutoLearn.weblearn.icve.api.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudingInfo {
	private List<CourseInfo> courseList=new ArrayList<CourseInfo>();
	public StudingInfo(JSONObject json) throws JSONException{
		this.courseList=getCourseList(json);
		// TODO 自动生成的构造函数存根
	}
	public List<CourseInfo> getCourseList() {
		return courseList;
	}
	public static List<CourseInfo> getCourseList(JSONObject json){
		List<CourseInfo> courseList=new ArrayList<CourseInfo>();
		JSONArray jsa=json.getJSONArray("list");
		for(int i=0;i<jsa.length();i++){
			JSONObject jso=jsa.getJSONObject(i);
			courseList.add(new CourseInfo(jso));
		}
		return courseList;
	}
	
}
