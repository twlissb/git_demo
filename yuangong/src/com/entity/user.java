package com.entity;
/**
 * ��Ա��
 * @author AORUS
 *
 */
public class user {
	private int id;//��Աid
	private String loginname;//��¼��
	private String name;//����
	private String pwd;//����
	private int sex;//�Ա�
	private int age;//����
	private int bumenid;//����id
	private int zhiid;//ְ��id
	private int roleid;//��ɫid
	private String bumenname;
	private String zhiname;
	private String rolename;
	
	public String getBumenname() {
		return bumenname;
	}
	public void setBumenname(String bumenname) {
		this.bumenname = bumenname;
	}
	public String getZhiname() {
		return zhiname;
	}
	public void setZhiname(String zhiname) {
		this.zhiname = zhiname;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getBumenid() {
		return bumenid;
	}
	public void setBumenid(int bumenid) {
		this.bumenid = bumenid;
	}
	public int getZhiid() {
		return zhiid;
	}
	public void setZhiid(int zhiid) {
		this.zhiid = zhiid;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	
}
