package com.role.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.entity.Layui;
import com.entity.Menu;
import com.entity.MenudtreeData;
import com.entity.Role;
import com.entity.user;
import com.entity.Menu;
import com.menu.service.menuServiceImpl;
import com.role.dao.roleDao;
import com.role.dao.roleDaoImpl;
import com.utils.DataBaseUtil;
import com.utils.TransferArray;

public class roleServiceImpl implements roleService {
	Connection conn=DataBaseUtil.getConnection();
	roleDao dao=new roleDaoImpl(conn);
	@Override
	public int addRole(Role role) {
		// TODO Auto-generated method stub
		return dao.addRole(role);
	}

	@Override
	public int updRole(Role role) {
		// TODO Auto-generated method stub
		return dao.updRole(role);
	}

	@Override
	public int delRole(int id) {
		// TODO Auto-generated method stub
		return dao.delRole(id);
	}

	@Override
	public Role allRoleByRoleid(int roleid) {
		// TODO Auto-generated method stub
		return dao.allRoleByRoleid(roleid);
	}

	@Override
	public List<user> allrole() {
		// TODO Auto-generated method stub
		return dao.allrole();
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return dao.Count();
	}

	@Override
	public Layui<MenudtreeData> dtreeJson() {
		List<Menu> allMenu = new menuServiceImpl().allMenu();
		Layui<MenudtreeData> layui = new Layui<MenudtreeData>();
		layui.setCode(0);
		layui.setCount(0);
		layui.setMsg("");
		List<MenudtreeData> dataList = new ArrayList<MenudtreeData>();
		for (Menu menu : allMenu) {
			MenudtreeData data = new MenudtreeData();
			data.setId(menu.getId());
			data.setParentId(menu.getMfatherid());
			data.setTitle(menu.getMname());
			data.setCheckArr("0");
			dataList.add(data);
		}
		layui.setData(dataList);
		return layui;
	}

	@Override
	public List<Menu> menuByUserId(int roleid) {
		// TODO Auto-generated method stub
		return dao.menuByUserId(roleid);
	}

	@Override
	public int grantMenuByUserid(int userid, String[] array) {
		int row = this.delUser_MenuByUserid(userid);	//先根据用户id去删除权限
		int[] arrs = TransferArray.StringToInt(array);
		int count = 0;
		if(row != -1) {
			for (int i = 0; i < arrs.length; i++) {
				int yes = this.addUser_MenuByUserid(userid, arrs[i]);	//循环增加数据
				if(yes > 0) {	
					count++;//如果添加成功++ 记录增加了几条数据
				}
			}
		}
		return count;
	}

	@Override
	public int delUser_MenuByUserid(int roleid) {
		// TODO Auto-generated method stub
		return dao.delUser_MenuByUserid(roleid);
	}

	@Override
	public int addUser_MenuByUserid(int roleid, int menuid) {
		// TODO Auto-generated method stub
		return dao.addUser_MenuByUserid(roleid, menuid);
	}

}
