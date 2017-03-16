package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public final class Admin {

	@Id
	private long id;//权限表id
	private String name;//用户类型说明
	private int upow; //用户权限值
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUpow() {
		return upow;
	}

	public void setUpow(int upow) {
		this.upow = upow;
	}

	public Admin() {
		super();
	}
	
}
