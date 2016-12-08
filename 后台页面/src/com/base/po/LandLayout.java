package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class LandLayout {
	
	@Id	
	private String id;
	private int x_axis;
	private int y_axis;
	private int width;
	private int height;
	private int bid;
	private String lid;	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getX_axis() {
		return x_axis;
	}
	public void setX_axis(int x_axis) {
		this.x_axis = x_axis;
	}
	public int getY_axis() {
		return y_axis;
	}
	public void setY_axis(int y_axis) {
		this.y_axis = y_axis;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public LandLayout() {
		super();
	}
	
}
