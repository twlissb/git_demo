package com.entity;

import java.sql.Date;
/**
 * 部门表
 * @author AORUS
 *
 */
public class bumen {
	private int id;//部门编号
	private String name;//部门名
	private int count;//部门人数
	private Date time;//部门创立时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
