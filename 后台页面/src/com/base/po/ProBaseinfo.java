package com.base.po;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProBaseinfo
{
	@Id
	private String id;
	private String name;
	private int type;
	private String landarea;
	private String construction;
	private int undertake;
	private int applydp;
	private String buildtime;
	private int status;
	private int star;
	private String land_address;
	private String username;
	private String phone;
	private String material_path;
	private int valid_date;
	public ProBaseinfo(String id, String name, int type, String landarea,
			String construction, int undertake, int applydp, String buildtime,
			int status, int star, String land_address, String username,
			String phone, String material_path, int valid_date)
	{
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.landarea = landarea;
		this.construction = construction;
		this.undertake = undertake;
		this.applydp = applydp;
		this.buildtime = buildtime;
		this.status = status;
		this.star = star;
		this.land_address = land_address;
		this.username = username;
		this.phone = phone;
		this.material_path = material_path;
		this.valid_date = valid_date;
	}
	public ProBaseinfo()
	{
		super();
	}
	public ProBaseinfo(String name)
	{
		super();
		this.name = name;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public String getLandarea()
	{
		return landarea;
	}
	public void setLandarea(String landarea)
	{
		this.landarea = landarea;
	}
	public String getConstruction()
	{
		return construction;
	}
	public void setConstruction(String construction)
	{
		this.construction = construction;
	}
	public int getUndertake()
	{
		return undertake;
	}
	public void setUndertake(int undertake)
	{
		this.undertake = undertake;
	}
	public int getApplydp()
	{
		return applydp;
	}
	public void setApplydp(int applydp)
	{
		this.applydp = applydp;
	}
	public String getBuildtime()
	{
		return buildtime;
	}
	public void setBuildtime(String buildtime)
	{
		this.buildtime = buildtime;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public int getStar()
	{
		return star;
	}
	public void setStar(int star)
	{
		this.star = star;
	}
	public String getLand_address()
	{
		return land_address;
	}
	public void setLand_address(String land_address)
	{
		this.land_address = land_address;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getMaterial_path()
	{
		return material_path;
	}
	public void setMaterial_path(String material_path)
	{
		this.material_path = material_path;
	}
	public int getValid_date()
	{
		return valid_date;
	}
	public void setValid_date(int valid_date)
	{
		this.valid_date = valid_date;
	}
	
}
