package com.base.po;

import java.util.List;

public class PracticeCollection {

	  private String courseId;//课程代码
	  private String department;//单位
	  private String courseName;//课程名称
	  private String major_oriented;//面向专业
	  private String composition;//班级组成
	  private String credit;//学分
	  private String countPeople;//学习人数
	  private String weekCount;//实习周数
	  private String tid;//教师职工号
	  private String tname;//教师名称
	  public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	private List<Classcourse> data;//实习申请表
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getMajor_oriented() {
		return major_oriented;
	}
	public void setMajor_oriented(String major_oriented) {
		this.major_oriented = major_oriented;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getCountPeople() {
		return countPeople;
	}
	public void setCountPeople(String countPeople) {
		this.countPeople = countPeople;
	}
	public String getWeekCount() {
		return weekCount;
	}
	public void setWeekCount(String weekCount) {
		this.weekCount = weekCount;
	}
	public List<Classcourse> getData() {
		return data;
	}
	public void setData(List<Classcourse> data) {
		this.data = data;
	}
	public PracticeCollection() {
		super();
	}
	  
	  
}
