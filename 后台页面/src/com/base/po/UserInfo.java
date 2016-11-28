package com.base.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class UserInfo {
	
	@Id	
	private String id;
	private String password;
	private String name;
	private String sex;	
	private String img;	
	private String birthdate;
	private String ID_number;
	private String telephone;
	private String userType;
	private String college;
	private int userRight;
	private String formerUnit;
	private String hukou;
	private String arriveTime;
	private String workTime;
	private String contactForm;
	private String workingForm;
	private String startContactTime;
	private String endContactTime;	
	public UserInfo(String id,String name, String telephone , String password,String img )
	{
		super();
		this.id=id;
		this.password = password;
		this.name = name;
		this.telephone = telephone;
		this.img=img;
	}

	public UserInfo(String id, String password, String name, String sex,
			String birthdate, String iD_number, String telephone,
			String userType, String college, int userRight)
	{
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.birthdate = birthdate;
		ID_number = iD_number;
		this.telephone = telephone;
		this.userType = userType;
		this.college = college;
		this.userRight = userRight;
	}

	public UserInfo(String id, String password, String name, String sex,
			String birthdate, String iD_number, String telephone,
			String userType,  String college, int userRight,
			String formerUnit, String hukou, String arriveTime,
			String workTime, String contactForm, String workingForm,
			String startContactTime, String endContactTime, String img)
	{
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.birthdate = birthdate;
		ID_number = iD_number;
		this.telephone = telephone;
		this.userType = userType;
		this.college = college;
		this.userRight = userRight;
		this.formerUnit = formerUnit;
		this.hukou = hukou;
		this.arriveTime = arriveTime;
		this.workTime = workTime;
		this.contactForm = contactForm;
		this.workingForm = workingForm;
		this.startContactTime = startContactTime;
		this.endContactTime = endContactTime;
		this.img = img;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getID_number() {
		return ID_number;
	}
	public void setID_number(String iD_number) {
		ID_number = iD_number;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public int getUserRight() {
		return userRight;
	}
	public void setUserRight(int userRight) {
		this.userRight = userRight;
	}
	public String getFormerUnit() {
		return formerUnit;
	}
	public void setFormerUnit(String formerUnit) {
		this.formerUnit = formerUnit;
	}
	public String getHukou() {
		return hukou;
	}
	public void setHukou(String hukou) {
		this.hukou = hukou;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getContactForm() {
		return contactForm;
	}
	public void setContactForm(String contactForm) {
		this.contactForm = contactForm;
	}
	public String getWorkingForm() {
		return workingForm;
	}
	public void setWorkingForm(String workingForm) {
		this.workingForm = workingForm;
	}
	public String getStartContactTime() {
		return startContactTime;
	}
	public void setStartContactTime(String startContactTime) {
		this.startContactTime = startContactTime;
	}
	public String getEndContactTime() {
		return endContactTime;
	}
	public void setEndContactTime(String endContactTime) {
		this.endContactTime = endContactTime;
	}
	public UserInfo() {
		super();
	}
	
	

}
