package com.iteamcn.AutoLearn.weblearn.moke;
import org.json.JSONObject;


public class Plan {
	private int chapterId;
	private int sectionId;
	private int unitId;
	private int status;
	private int type;
	public Plan(int chapterId, int sectionId, int unitId, int status, int type) {
		this.chapterId = chapterId;
		this.sectionId = sectionId;
		this.unitId = unitId;
		this.status = status;
		this.type = type;
	}
	public Plan(JSONObject json){
		this.chapterId=json.getInt("chapterId");
		this.sectionId=json.getInt("sectionId");
		this.unitId=json.getInt("unitId");
		this.status=json.getInt("status");
		this.type=json.getInt("type");
	}
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
