package com.entity;
/**
 * 角色表
 * @author AORUS
 *
 */
public class Role {
	private int id;//角色编号
	private String name;//角色名
	private int count;//该角色人数
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
	
}
