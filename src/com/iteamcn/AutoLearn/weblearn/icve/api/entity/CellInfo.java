package com.iteamcn.AutoLearn.weblearn.icve.api.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class CellInfo {
	private String cellType;
	private String chapterId;
	private String content;
	private String courseId;
	private String dateCreated;
	private String id;
	private int intResType;
	private boolean isMission;
	private String isSyncUserId;
	private String propertyName;
	private String propertyValue;
	private String resId;
	private int resType;
	private String sectionId;
	private String sortOrder;
	private String tableName;
	private String title;
	private String userId;
	public CellInfo(JSONObject json)throws JSONException{
		try{
		this.cellType=json.isNull("CellType")?"null":json.getString("CellType");
		//this.chapterId=json.getString("ChapterId");
		//this.content=json.getString("Content");
		//this.courseId=json.getString("CourseId");
		//this.dateCreated=json.getString("DateCreated");
		this.id=json.isNull("Id")?"null":json.getString("Id");
		this.intResType=json.isNull("IntResType")?0:json.getInt("IntResType");
		this.isMission=json.getBoolean("IsMission");
		//this.isSyncUserId=json.getString("IsSyncUserId");
		//this.propertyName=json.getString("PropertyName");
		//this.propertyValue=json.getString("PropertyValue");
		//this.resId=json.getString("ResId");
		this.resType=json.isNull("ResType")?0:json.getInt("ResType");;
		//this.sectionId=json.getString("SectionId");
		//this.sortOrder=json.getString("SortOrder");
		//this.tableName=json.getString("TableName");
		//this.title=json.getString("Title");
		//this.userId=json.getString("UserId");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(json);
		}
	}
	
	public String getCellType() {
		return cellType;
	}
	public void setCellType(String cellType) {
		this.cellType = cellType;
	}
	public String getChapterId() {
		return chapterId;
	}
	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIntResType() {
		return intResType;
	}
	public void setIntResType(int intResType) {
		this.intResType = intResType;
	}
	public boolean getIsMission() {
		return isMission;
	}
	public void setIsMission(boolean isMission) {
		this.isMission = isMission;
	}
	public String getIsSyncUserId() {
		return isSyncUserId;
	}
	public void setIsSyncUserId(String isSyncUserId) {
		this.isSyncUserId = isSyncUserId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public int getResType() {
		return resType;
	}
	public void setResType(int resType) {
		this.resType = resType;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
