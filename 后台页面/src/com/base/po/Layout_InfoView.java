package com.base.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class Layout_InfoView{	
	
	
	private String id;
	private int x;
	private int y;
	private int width;
	private int height;
	private String plantingContent;
	private String lname;
	private int landArea;
	private int buildingArea;
	private int bid;	
	private int Afford;
	private String aptCollege;
	private String img;
	public String getAptCollege()
	{
		return aptCollege;
	}
	public void setAptCollege(String aptCollege)
	{
		this.aptCollege = aptCollege;
	}	
	public String getImg()
	{
		return img;
	}
	public void setImg(String img)
	{
		this.img = img;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
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
	public String getPlantingContent() {
		return plantingContent;
	}
	public void setPlantingContent(String plantingContent) {
		this.plantingContent = plantingContent;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}


	public int getLandArea() {
		return landArea;
	}
	public void setLandArea(int landArea) {
		this.landArea = landArea;
	}
	public int getBuildingArea() {
		return buildingArea;
	}
	public void setBuildingArea(int buildingArea) {
		this.buildingArea = buildingArea;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getAfford() {
		return Afford;
	}
	public void setAfford(int afford) {
		Afford = afford;
	}
	public Layout_InfoView() {
		super();
	}
	
	

}
