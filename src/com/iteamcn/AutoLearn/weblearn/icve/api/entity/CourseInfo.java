package com.iteamcn.AutoLearn.weblearn.icve.api.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class CourseInfo {
	private String courseId;
	private String schedule;
	private String title;
	
	public CourseInfo(String courseId, String schedule, String title) {
		this.courseId = courseId;
		this.schedule = schedule;
		this.title = title;
	}
	public CourseInfo(JSONObject json) throws JSONException{
		this.courseId=json.getString("id");
		this.schedule=json.getString("schedule");
		this.title=json.getString("title");
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
