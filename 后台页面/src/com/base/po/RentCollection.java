package com.base.po;

import java.util.List;

public class RentCollection {

	private String id;
	private int x;
	private int y;
	private int width;
	private int height;
	private int bid;
	private String lname;
	private String plantingContent;
	private int landArea;
	private int buildingArea;
	private int Afford;
	private String collage;
	private String name;
	private String planting;
	private int Lineup;
	private int tag;
	private List<RentAdd> data;
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
		
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public List<RentAdd> getData() {
		return data;
	}
	public void setData(List<RentAdd> data) {
		this.data = data;
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
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPlantingContent() {
		return plantingContent;
	}
	public void setPlantingContent(String plantingContent) {
		this.plantingContent = plantingContent;
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
	public int getAfford() {
		return Afford;
	}
	public void setAfford(int afford) {
		Afford = afford;
	}
	public String getCollage() {
		return collage;
	}
	public void setCollage(String collage) {
		this.collage = collage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlanting() {
		return planting;
	}
	public void setPlanting(String planting) {
		this.planting = planting;
	}
	public int getLineup() {
		return Lineup;
	}
	public void setLineup(int lineup) {
		Lineup = lineup;
	}
	public RentCollection() {
		super();
	}
	
	
}
