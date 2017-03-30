package com.base.po;

public class Mymaintain {

	private int id; // 维修基地编号
	private String pro_name; // 维修项目名称
	private String username; // 申报用户名
	private String address; // 维修地址ַ
	private String reason; // 维修原因说明
	private String file; // 附加材料路径ַ
	private double money; // 维修预算金额
	private String apply_time; // 申请时间
	private String userid; // 申请的用户id
	private String bid;// 基地id
	private String basename; // 基地名称
	private int status;// 状态id
	private String descp;// 状态值
	private double actualmoney;// 实际金额
	private String refuse;// 拒绝原因

	public Mymaintain() {
		super();
	}

	public String getRefuse() {
		return refuse;
	}

	public void setRefuse(String refuse) {
		this.refuse = refuse;
	}

	public Mymaintain(int id, String pro_name, String username, String address,
			String reason, String file, double money, String apply_time,
			String userid, String bid, String basename, int status,
			String descp, double actualmoney) {
		super();
		this.id = id;
		this.pro_name = pro_name;
		this.username = username;
		this.address = address;
		this.reason = reason;
		this.file = file;
		this.money = money;
		this.apply_time = apply_time;
		this.userid = userid;
		this.bid = bid;
		this.basename = basename;
		this.status = status;
		this.descp = descp;
		this.actualmoney = actualmoney;
	}

	public double getActualmoney() {
		return actualmoney;
	}

	public void setActualmoney(double actualmoney) {
		this.actualmoney = actualmoney;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getApply_time() {
		return apply_time;
	}

	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBasename() {
		return basename;
	}

	public void setBasename(String basename) {
		this.basename = basename;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

}
