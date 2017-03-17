package com.base.po;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Startplan {
	
	@Id
	private String id;
	private String work;
	private String apply_start;
	private String apply_end;
	private String rent_start;
	private String rent_end;
	private Long limitday;
	
	public Startplan(String id, String work, String apply_start,
			String apply_end, String rent_start, String rent_end, Long limitday) {
		super();
		this.id = id;
		this.work = work;
		this.apply_start = apply_start;
		this.apply_end = apply_end;
		this.rent_start = rent_start;
		this.rent_end = rent_end;
		this.limitday = limitday;
	}
	public Long getLimitday() {
		return limitday;
	}
	public void setLimitday(Long limitday) {
		this.limitday = limitday;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	
	public String getRent_start() {
		return rent_start;
	}
	public void setRent_start(String rent_start) {
		this.rent_start = rent_start;
	}
	public String getRent_end() {
		return rent_end;
	}
	public void setRent_end(String rent_end) {
		this.rent_end = rent_end;
	}
	public String getApply_start() {
		return apply_start;
	}
	public void setApply_start(String apply_start) {
		this.apply_start = apply_start;
	}
	public String getApply_end() {
		return apply_end;
	}
	public void setApply_end(String apply_end) {
		this.apply_end = apply_end;
	}
	public Startplan(String id,String work, String apply_start, String apply_end,
			String rent_start, String rent_end) {
		super();
		this.id = id;
		this.work=work;
		this.apply_start = apply_start;
		this.apply_end = apply_end;
		this.rent_start = rent_start;
		this.rent_end = rent_end;
	}
	public Startplan() {
		super();
	}
	

}
