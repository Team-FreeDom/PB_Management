package com.base.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="song")
public class song
{
	@Id
	private int username;
	private int money;
	public song(int username, int money)
	{
		super();
		this.username = username;
		this.money = money;
	}
	public song()
	{
		super();
	}
	public int getUsername()
	{
		return username;
	}
	public void setUsername(int username)
	{
		this.username = username;
	}
	public int getMoney()
	{
		return money;
	}
	public void setMoney(int money)
	{
		this.money = money;
	}
	
}
