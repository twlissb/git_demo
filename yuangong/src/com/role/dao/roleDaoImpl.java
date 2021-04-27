package com.role.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.BaseDao;
import com.entity.Menu;
import com.entity.Role;
import com.entity.user;

public class roleDaoImpl extends BaseDao implements roleDao {

	public roleDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	public int addRole(Role role) {
		String sql = "INSERT INTO `role`(NAME) VALUES (?)";
		Object[] parms = { role.getName() };
		return this.excuteUpdate(sql, parms);
	}

	@Override
	public int updRole(Role role) {
		String sql = "UPDATE `role` SET NAME=? WHERE id=?";
		Object[] parms = { role.getName(), role.getId() };
		return this.excuteUpdate(sql, parms);
	}

	@Override
	public int delRole(int id) {
		String sql = "DELETE FROM `role` WHERE id=?";
		Object[] parms = { id };
		return this.excuteUpdate(sql, parms);
	}

	@Override
	public Role allRoleByRoleid(int roleid) {
		ResultSet rs = null;
		Role role = new Role();
		String sql = "select * from role where id=?";
		Object[] parms = { roleid };
		try {
			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				role.setId(rs.getInt(1));
				role.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public List<user> allrole() {
		List<user> list = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT b.id,b.name bname,a.name aname FROM `user` a,`role` b WHERE a.roleid=b.id";
		Object[] parms = {};
		try {

			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				user ren = new user();
				ren.setRoleid(rs.getInt(1));
				ren.setRolename(rs.getString(2));
				ren.setName(rs.getString(3));
				list.add(ren);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int Count() {
		ResultSet rs = null;
		int result = -1;
		try {
			String sql = "SELECT COUNT(1) FROM role";
			rs = super.excuteQuery(sql, null);
			if (rs != null) {
				while (rs.next()) {
					result = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			// 捕获异常！

		} finally {
		}
		return result;
	}

	@Override
	public List<Menu> menuByUserId(int roleid) {
		List<Menu> list = new ArrayList<>();
		ResultSet rs = null;

		String sql = "SELECT menuid FROM `menu_role` WHERE roleid = ?";
		Object[] parms = { roleid };
		try {

			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt(1));
				list.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int delUser_MenuByUserid(int roleid) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM `menu_role` WHERE roleid=?";
		Object[] parms = { roleid };
		return this.excuteUpdate(sql, parms);
	}

	@Override
	public int addUser_MenuByUserid(int roleid, int menuid) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO `menu_role` VALUES(?,?)";
		Object[] parms = { roleid, menuid };
		return this.excuteUpdate(sql, parms);
	}

}
