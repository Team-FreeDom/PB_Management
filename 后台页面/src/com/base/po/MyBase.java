package com.base.po;

public class MyBase {

    
    private int id;//记录id
    private String bid;//基地id
    private String name;//基地名称
    private String type;//基地类型
    private String landarea;//基地面积
    private String constructionarea;//建筑面积
    private int undertake;//一次性可承担人数
    private String applydp;//申请部门
    private String land_address;//通讯地址
    private String username;//联系人姓名
    private String phone;//联系人电话
    private String material_path;//申请材料地址
    private String userid;//用户id
    private String mmajor;//面向专业   
    private String statusdigital ;//状态文字
    private int statusid;//状态id;
    private String reason;//拒绝理由
    private String applytime;//申请时间
    private String resperson;//申请时间   
    private String buildtime;//申请时间   
    private String endtime;//申请时间   
    private String cooperativeUnit;//合作单位名称
    private String collegeName;//学院联系人
    private String collegePhone;//学院联系人电话
    
    public String getCooperativeUnit() {
		return cooperativeUnit;
	}

	public void setCooperativeUnit(String cooperativeUnit) {
		this.cooperativeUnit = cooperativeUnit;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getCollegePhone() {
		return collegePhone;
	}

	public void setCollegePhone(String collegePhone) {
		this.collegePhone = collegePhone;
	}

	public String getBuildtime() {
		return buildtime;
	}

	public void setBuildtime(String buildtime) {
		this.buildtime = buildtime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getResperson() {
		return resperson;
	}

	public void setResperson(String resperson) {
		this.resperson = resperson;
	}

	public MyBase(int id, String bid, String name, String type,
	    String landarea, String constructionarea, int undertake,
	    String applydp, String land_address, String username, String phone,
	    String material_path, String userid, String mmajor, int valid_date,
	    String statusdigital, int statusid, String reason, String applytime) {
	super();
	this.id = id;
	this.bid = bid;
	this.name = name;
	this.type = type;
	this.landarea = landarea;
	this.constructionarea = constructionarea;
	this.undertake = undertake;
	this.applydp = applydp;
	this.land_address = land_address;
	this.username = username;
	this.phone = phone;
	this.material_path = material_path;
	this.userid = userid;
	this.mmajor = mmajor;
	this.statusdigital = statusdigital;
	this.statusid = statusid;
	this.reason = reason;
	this.applytime = applytime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    public MyBase() {
	super();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
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
    public String getLand_address() {
        return land_address;
    }
    public void setLand_address(String land_address) {
        this.land_address = land_address;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMaterial_path() {
        return material_path;
    }
    public void setMaterial_path(String material_path) {
        this.material_path = material_path;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getMmajor() {
        return mmajor;
    }
    public void setMmajor(String mmajor) {
        this.mmajor = mmajor;
    }

    public String getStatusdigital() {
        return statusdigital;
    }
    public void setStatusdigital(String statusdigital) {
        this.statusdigital = statusdigital;
    }
    public int getStatusid() {
        return statusid;
    }
    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }
    
}
