package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MaintainApply
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;  //维修基地编号
	private String pro_name;  //维修项目名称
	private String bid;  //维修基地id
	private String username;  //申报用户名
	private String address;   //维修地址ַ
	private String reason;   //维修原因说明
	private String file;  //附加材料路径ַ
	private double money;  //维修预算金额
	private String apply_time; //申请时间
	private int status;  //申请状态值
	private String userid;  //申请的用户id
	private double actualmoney;  //维修所需实际金额
	public MaintainApply()
	{
		super();
	}
	public MaintainApply(int id, String pro_name, String bid, String username,
			String address, String reason, String file, double money,
			String apply_time, int status, String userid,double actualmoney)
	{
		super();
		this.id = id;
		this.pro_name = pro_name;
		this.bid = bid;
		this.username = username;
		this.address = address;
		this.reason = reason;
		this.file = file;
		this.money = money;
		this.apply_time = apply_time;
		this.status = status;
		this.userid = userid;
		this.actualmoney=actualmoney;
	}
	public MaintainApply(String pro_name, String bid, String username,
			String address, String reason, String file, double money,
			String apply_time, int status, double actualmoney) {
		super();
		this.pro_name = pro_name;
		this.bid = bid;
		this.username = username;
		this.address = address;
		this.reason = reason;
		this.file = file;
		this.money = money;
		this.apply_time = apply_time;
		this.status = status;
		this.actualmoney = actualmoney;
	}
	public double getActualmoney()
	{
		return actualmoney;
	}
	public void setActualmoney(double actualmoney)
	{
		this.actualmoney = actualmoney;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getPro_name()
	{
		return pro_name;
	}
	public void setPro_name(String pro_name)
	{
		this.pro_name = pro_name;
	}
	public String getBid()
	{
		return bid;
	}
	public void setBid(String bid)
	{
		this.bid = bid;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getReason()
	{
		return reason;
	}
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	public String getFile()
	{
		return file;
	}
	public void setFile(String file)
	{
		this.file = file;
	}
	public double getMoney()
	{
		return money;
	}
	public void setMoney(double money)
	{
		this.money = money;
	}
	public String getApply_time()
	{
		return apply_time;
	}
	public void setApply_time(String apply_time)
	{
		this.apply_time = apply_time;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public String getUserid()
	{
		return userid;
	}
	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	
}
