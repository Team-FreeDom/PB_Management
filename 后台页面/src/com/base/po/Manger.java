package com.base.po;

import java.io.Serializable;

public class Manger implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	private String username;
	private String sex;
	private String category;// 员工类别
	private String attritube;// 员工身份属性
	private String birth;
	private String idcard;// 身份证
	private String telephone;
	private String dept;// 部门
	private String major;// 专业
	private String titles;// 职称

	public Manger(String id, String password, String username, String sex,
			String category, String attritube, String birth, String idcard,
			String telephone, String dept) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
		this.sex = sex;
		this.category = category;
		this.attritube = attritube;
		this.birth = birth;
		this.idcard = idcard;
		this.telephone = telephone;
		this.dept = dept;
	}

	public Manger() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAttritube() {
		return attritube;
	}

	public void setAttritube(String attritube) {
		this.attritube = attritube;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

}