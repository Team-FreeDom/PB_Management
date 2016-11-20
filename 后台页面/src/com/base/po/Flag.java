package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flag")
public class Flag {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;//标志位
	private String desc;//标志数字描述
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Flag() {
		super();
	}
	
}
