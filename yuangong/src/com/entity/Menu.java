package com.entity;

public class Menu {
	private int id;//Ȩ�ޱ��
	private String mname;//Ȩ����
	private int mfatherid;//����id
	private int type;//����
	private String url;//��ַ
	private String js;//������js
	private String btn;//��ť
	private int status;//αɾ��0��ʾ,1����ʾ
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getMfatherid() {
		return mfatherid;
	}
	public void setMfatherid(int mfatherid) {
		this.mfatherid = mfatherid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public String getBtn() {
		return btn;
	}
	public void setBtn(String btn) {
		this.btn = btn;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
