package com.base.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class CheckView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int la_id;
	private String startime;
	private String endtime;
	private String userid;
	private String plant;
	private String li;
	private String basename;
	private String username;
	private String usercollage;
	private String landname;
	private String landoriented;
	private int landstatus;
	private String landdesp;
	private int times;
	public CheckView(int la_id,String startime, String endtime, String userid,
			String plant, String li, String basename, String username,
			String usercollage, String landname, String landoriented,
			int landstatus, String landdesp, int times)
	{
		super();
		this.la_id=la_id;
		this.startime = startime;
		this.endtime = endtime;
		this.userid = userid;
		this.plant = plant;
		this.li = li;
		this.basename = basename;
		this.username = username;
		this.usercollage = usercollage;
		this.landname = landname;
		this.landoriented = landoriented;
		this.landstatus = landstatus;
		this.landdesp = landdesp;
		this.times = times;
	}
	public CheckView()
	{
		super();
	}
	
	public int getLa_id()
	{
		return la_id;
	}
	public void setLa_id(int la_id)
	{
		this.la_id = la_id;
	}
	public String getStartime()
	{
		return startime;
	}
	public void setStartime(String starttime)
	{
		this.startime = starttime;
	}
	public String getEndtime()
	{
		return endtime;
	}
	public void setEndtime(String endtime)
	{
		this.endtime = endtime;
	}
	public String getUserid()
	{
		return userid;
	}
	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	public String getPlant()
	{
		return plant;
	}
	public void setPlant(String plant)
	{
		this.plant = plant;
	}
	public String getLi()
	{
		return li;
	}
	public void setLi(String li)
	{
		this.li = li;
	}
	public String getBasename()
	{
		return basename;
	}
	public void setBasename(String basename)
	{
		this.basename = basename;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsercollage()
	{
		return usercollage;
	}
	public void setUsercollage(String usercollage)
	{
		this.usercollage = usercollage;
	}
	public String getLandname()
	{
		return landname;
	}
	public void setLandname(String landname)
	{
		this.landname = landname;
	}
	public String getLandoriented()
	{
		return landoriented;
	}
	public void setLandoriented(String landoriented)
	{
		this.landoriented = landoriented;
	}
	public int getLandstatus()
	{
		return landstatus;
	}
	public void setLandstatus(int landstatus)
	{
		this.landstatus = landstatus;
	}
	public String getLanddesp()
	{
		return landdesp;
	}
	public void setLanddesp(String landdesp)
	{
		this.landdesp = landdesp;
	}
	public int getTimes()
	{
		return times;
	}
	public void setTimes(int times)
	{
		this.times = times;
	}
	
	
}
