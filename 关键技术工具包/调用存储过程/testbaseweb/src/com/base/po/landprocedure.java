package com.base.po;

import java.io.Serializable;

public class landprocedure implements Serializable
{
	private int la_id;
	private String startTime;
	private String endTime;
	private int status;
	private String bname;
	private int lid;
	private String applicantId;
	private int times;
	public landprocedure()
	{
		super();
	}
	public landprocedure(int la_id, String startTime, String endTime,
			int status, String bname, int lid, String applicantId, int times)
	{
		super();
		this.la_id = la_id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.bname = bname;
		this.lid = lid;
		this.applicantId = applicantId;
		this.times = times;
	}
	public int getLa_id()
	{
		return la_id;
	}
	public void setLa_id(int la_id)
	{
		this.la_id = la_id;
	}
	public String getStartTime()
	{
		return startTime;
	}
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}
	public String getEndTime()
	{
		return endTime;
	}
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public String getBname()
	{
		return bname;
	}
	public void setBname(String bname)
	{
		this.bname = bname;
	}
	public int getLid()
	{
		return lid;
	}
	public void setLid(int lid)
	{
		this.lid = lid;
	}
	public String getApplicantId()
	{
		return applicantId;
	}
	public void setApplicantId(String applicantId)
	{
		this.applicantId = applicantId;
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
