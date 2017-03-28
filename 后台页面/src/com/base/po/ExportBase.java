package com.base.po;

public class ExportBase {

	private String id;
    private String name;//基地名称
    private String type;//基地类型
    private String landarea;//土地面积
    private String constructionarea;//建筑面积
    private int undertake;//可承担人数
    private String applydp;//部门名称
    private int deptId;//部门编号
    private String land_address;//地址
    private String facemajor;//面向专业
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLandarea() {
		return landarea;
	}
	public void setLandarea(String landarea) {
		this.landarea = landarea;
	}
	public String getConstructionarea() {
		return constructionarea;
	}
	public void setConstructionarea(String constructionarea) {
		this.constructionarea = constructionarea;
	}
	public int getUndertake() {
		return undertake;
	}
	public void setUndertake(int undertake) {
		this.undertake = undertake;
	}
	public String getApplydp() {
		return applydp;
	}
	public void setApplydp(String applydp) {
		this.applydp = applydp;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getLand_address() {
		return land_address;
	}
	public void setLand_address(String land_address) {
		this.land_address = land_address;
	}
	public String getFacemajor() {
		return facemajor;
	}
	public void setFacemajor(String facemajor) {
		this.facemajor = facemajor;
	}
	public ExportBase() {
		super();
	}
    
    
    
}
