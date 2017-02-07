package com.base.po;

//实习计划数据类
public class AllPlan {
	
	private int id;    //编号
	private String cid;//课程代码
	private int count;//人数
	private int selectedCount;//已选人数
	private String composition;//教学班组成
	private String college;//学院
	private String coursename;//课程名称
	private double weekClassify;//周学时
	private double credit;//学分
	private String courseNature;//课程性质
	private String courseCategory;//课程类别
	private String mid;//专业编号
	private String tid;//教师编号
	private String tname;//教师名称
	private String semester;//学期
	private String week;//起止周
	private String checkMethod;//考核方式
	private String major_oriented;//面向专业
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSelectedCount() {
		return selectedCount;
	}
	public void setSelectedCount(int selectedCount) {
		this.selectedCount = selectedCount;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public double getWeekClassify() {
		return weekClassify;
	}
	public void setWeekClassify(double weekClassify) {
		this.weekClassify = weekClassify;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public String getCourseNature() {
		return courseNature;
	}
	public void setCourseNature(String courseNature) {
		this.courseNature = courseNature;
	}
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getCheckMethod() {
		return checkMethod;
	}
	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}
	public AllPlan() {
		super();
	}
	public String getMajor_oriented() {
		return major_oriented;
	}
	public void setMajor_oriented(String major_oriented) {
		this.major_oriented = major_oriented;
	}
	
	
	

}
