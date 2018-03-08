package com.iteamcn.AutoLearn.weblearn.moke;
import org.json.JSONObject;


public class Course {
	private int id;
	private String title;
	private String cover;
	private boolean longTerm;
	private int studentNum;
	private String lecturerName;
	private String lecturerCompany;
	private int status;
	private String startTime;
	private String endTime;
	private int cycle;
	public Course(JSONObject json){
		id=json.getInt("id");
		title=json.getString("title");
		cover=json.getString("cover");
		longTerm=json.getBoolean("longTerm");
		studentNum=json.getInt("studentNum");
		lecturerName=json.getString("lecturerName");
		lecturerCompany=json.getString("lecturerCompany");
		status=json.getInt("status");
		startTime=json.getString("startTime");
		endTime=json.getString("endTime");
		cycle=json.getInt("cycle");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public boolean isLongTerm() {
		return longTerm;
	}
	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}
	public int getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	public String getLecturerName() {
		return lecturerName;
	}
	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}
	public String getLecturerCompany() {
		return lecturerCompany;
	}
	public void setLecturerCompany(String lecturerCompany) {
		this.lecturerCompany = lecturerCompany;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	
}
