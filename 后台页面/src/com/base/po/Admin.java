package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public final class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int adminid;//权限表id
	private String adminType;//用户类型说明
	private int adminValue; //用户权限值
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public String getAdminType() {
		return adminType;
	}
	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}
	public int getAdminValue() {
		return adminValue;
	}
	public void setAdminValue(int adminValue) {
		this.adminValue = adminValue;
	}
	public Admin() {
		super();
	}
	
}
