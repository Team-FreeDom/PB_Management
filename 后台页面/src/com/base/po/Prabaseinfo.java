package com.base.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Prabaseinfo implements Serializable {
    
    private static final long serialVersionUID = 1L;  
    @Id
    private String id;
    private String name;//鍩哄湴鍚嶇О
    private String type;//鍩哄湴绫诲瀷
    private String landarea;//鍩哄湴闈㈢Н
    private String constructionarea;//寤虹瓚闈㈢Н
    private Integer undertake;//鍙壙鎷呬汉鏁�
    private String applydp;//鐢虫姤閮ㄩ棬   
    private String land_address;//瀹炰範鍩哄湴鍦板潃
    private String username;//鑱旂郴浜哄鍚�
    private String phone;//鑱旂郴浜烘墜鏈哄彿
    private String material_path;//鐢宠鏉愭枡鍦板潃
    private String endtime;
    private String buildtime;
    private String userid;
    private String facemajor;//闈㈠悜涓撲笟
    private int star;//鏄熺骇
    private String resperson;
       
 
	public String getResperson() {
		return resperson;
	}
	public void setResperson(String resperson) {
		this.resperson = resperson;
	}
	public Prabaseinfo() {
	super();
    }
    public Prabaseinfo(String id, String name, String type, String landarea,
	    String constructionarea, int undertake, String applydp,
	    String land_address, String username, String phone,
	    String material_path, String facemajor, int star) {
	super();
	this.id = id;
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
	this.facemajor = facemajor;
	this.star = star;
    }
    
    public String getBuildtime() {
		return buildtime;
	}
	public void setBuildtime(String buildtime) {
		this.buildtime = buildtime;
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
    public int getStar() {
        return star;
    }
    public void setStar(int star) {
        this.star = star;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFacemajor() {
		return facemajor;
	}
	public void setFacemajor(String facemajor) {
		this.facemajor = facemajor;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public void setUndertake(Integer undertake) {
		this.undertake = undertake;
	}
	
    
}
