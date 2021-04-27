package com.menu.service;

import java.sql.Connection;
import java.util.List;

import com.entity.Button;
import com.entity.Menu;
import com.menu.dao.menuDao;
import com.menu.dao.menuDaoImpl;
import com.utils.DataBaseUtil;

public class menuServiceImpl implements menuService {
	Connection conn=DataBaseUtil.getConnection();
	menuDao dao=new menuDaoImpl(conn);
	@Override
	public List<Menu> allMenu() {
		// TODO Auto-generated method stub
		return dao.allMenu();
	}
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return dao.count();
	}
	@Override
	public int addMenu(Menu menu) {
		// TODO Auto-generated method stub
		return dao.addMenu(menu);
	}
	@Override
	public List<Button> buttonType() {
		// TODO Auto-generated method stub
		return dao.buttonType();
	}
	@Override
	public List<Menu> menuByBtn3(int type) {
		// TODO Auto-generated method stub
		return dao.menuByBtn3(type);
	}
	@Override
	public int modifyByMenuidIsDelete(int menuid) {
		// TODO Auto-generated method stub
		return dao.modifyByMenuidIsDelete(menuid);
	}
	@Override
	public Menu allMenuById(int id) {
		// TODO Auto-generated method stub
		return dao.allMenuById(id);
	}
	@Override
	public int upMenu(Menu menu) {
		// TODO Auto-generated method stub
		return dao.upMenu(menu);
	}

}
