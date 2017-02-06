package com.base.po;

import javax.persistence.Entity;

@Entity
public class Classarragecourse {

	private int id;//编号
	private int week;//周次
	private String starttime;//开始时间
	private String endtime;//结束时间
	private String content;//实验内容
	private String source;//实验来源
	private String site;//实习地点
	private String category;//实习类别
	private String form;//实习形式
    private String telephone;//联系人电话
    private int aim;//目的
    private String expense;//经费
    private int cid;//对应课程表的主键
    private String guideTeacher;//指导老师
    private String assistant;//实验员
    private String remark;//备注
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getAim() {
		return aim;
	}
	public void setAim(int aim) {
		this.aim = aim;
	}
	public String getExpense() {
		return expense;
	}
	public void setExpense(String expense) {
		this.expense = expense;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getGuideTeacher() {
		return guideTeacher;
	}
	public void setGuideTeacher(String guideTeacher) {
		this.guideTeacher = guideTeacher;
	}
	public String getAssistant() {
		return assistant;
	}
	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Classarragecourse() {
		super();
	}
    
    
}
