package com.entity;

import java.sql.Date;
/**
 * ���ű�
 * @author AORUS
 *
 */
public class bumen {
	private int id;//���ű��
	private String name;//������
	private int count;//��������
	private Date time;//���Ŵ���ʱ��
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
