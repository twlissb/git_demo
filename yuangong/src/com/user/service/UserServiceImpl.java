package com.user.service;

import java.sql.Connection;
import java.util.List;

import com.entity.Menu;
import com.entity.Role;
import com.entity.user;
import com.user.dao.UserDao;
import com.user.dao.UserDaoImpl;
import com.utils.DataBaseUtil;

public class UserServiceImpl implements UserService {
	Connection conn=DataBaseUtil.getConnection();
	UserDao dao=new UserDaoImpl(conn);
	@Override
	public user login(String loginName) {
		// TODO Auto-generated method stub
		return dao.login(loginName);
	}
	@Override
	public List<Menu> selectmenu(int id) {
		// TODO Auto-generated method stub
		return dao.selectmenu(id);
	}
	@Override
	public List<Menu> selectann(int id, int userid) {
		// TODO Auto-generated method stub
		return dao.selectann(id, userid);
	}
	@Override
	public List<user> selectall() {
		// TODO Auto-generated method stub
		return dao.selectall();
	}
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return dao.count();
	}
	@Override
	public List<com.entity.bumen> bumen() {
		// TODO Auto-generated method stub
		return dao.bumen();
	}
	@Override
	public List<com.entity.zhi> zhi() {
		// TODO Auto-generated method stub
		return dao.zhi();
	}
	@Override
	public List<Role> role() {
		// TODO Auto-generated method stub
		return dao.role();
	}
	@Override
	public int addUser(user user) {
		// TODO Auto-generated method stub
		return dao.addUser(user);
	}
	@Override
	public int delUser(int id) {
		// TODO Auto-generated method stub
		return dao.delUser(id);
	}
	@Override
	public user oneuser(int id) {
		// TODO Auto-generated method stub
		return dao.oneuser(id);
	}
	@Override
	public int ifbumen(int id) {
		// TODO Auto-generated method stub
		return dao.ifbumen(id);
	}
	@Override
	public int ifzhi(int id) {
		// TODO Auto-generated method stub
		return dao.ifzhi(id);
	}
	@Override
	public int ifrole(int id) {
		// TODO Auto-generated method stub
		return dao.ifrole(id);
	}
	@Override
	public com.entity.bumen userbumen(int id) {
		// TODO Auto-generated method stub
		return dao.userbumen(id);
	}
	@Override
	public com.entity.zhi userzhi(int id) {
		// TODO Auto-generated method stub
		return dao.userzhi(id);
	}
	@Override
	public Role userrole(int id) {
		// TODO Auto-generated method stub
		return dao.userrole(id);
	}
	@Override
	public int upduser(user user) {
		// TODO Auto-generated method stub
		return dao.upduser(user);
	}

}
