package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="landLayout")
public class LandLayout {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int x_axis;
	private int y_axis;
	private int width;
	private int height;
	private int bid;
	private int lid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public LandLayout() {
		super();
	}
	
}
