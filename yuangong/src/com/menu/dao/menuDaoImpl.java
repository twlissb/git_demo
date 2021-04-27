package com.menu.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.entity.BaseDao;
import com.entity.Button;
import com.entity.Menu;

public class menuDaoImpl extends BaseDao implements menuDao {
	public menuDaoImpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Menu> allMenu() {
		List<Menu> list = new ArrayList<>();
		ResultSet rs = null;

		String sql = "SELECT * FROM `is_menu` where is_status=0";
		Object[] parms = {};
		try {

			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt(1));
				menu.setMname(rs.getString(2));
				menu.setMfatherid(rs.getInt(3));
				menu.setType(rs.getInt(4));
				menu.setUrl(rs.getString(5));
				menu.setJs(rs.getString(6));
				menu.setBtn(rs.getString(7));
				list.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int count() {
		int count = 0;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM `is_menu`";
		Object[] parms = {};
		try {

			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	/**
	 * 查询所有的按钮样式
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public List<Button> buttonType() {
		List<Button> list = new ArrayList<>();
		ResultSet rs = null;

		String sql = "SELECT * FROM `ls_butten`";
		Object[] parms = {};
		try {

			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				Button button = new Button();
				button.setId(rs.getInt(1));
				button.setBtntype(rs.getString(2));
				list.add(button);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public List<Menu> menuByBtn3(int type) {
		List<Menu> list = new ArrayList<>();
		ResultSet rs = null;

		String sql = "SELECT * FROM `is_menu` where type=?";
		Object[] parms = {type};
		try {

			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt(1));
				menu.setMname(rs.getString(2));
				menu.setMfatherid(rs.getInt(3));
				menu.setType(rs.getInt(4));
				menu.setUrl(rs.getString(5));
				menu.setJs(rs.getString(6));
				menu.setBtn(rs.getString(7));
				list.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int addMenu(Menu menu) {
		String sql="INSERT INTO `is_menu`(`mname`,`mfatherid`,`type`,`url`,`js`,`btn`) VALUES(?,?,?,?,?,?)";
		Object[] parms={menu.getMname(),menu.getMfatherid(),menu.getType(),menu.getUrl(),menu.getJs(),menu.getBtn() };
		
		return this.excuteUpdate(sql, parms);
	}

	@Override
	public int modifyByMenuidIsDelete(int menuid) {
		// TODO Auto-generated method stub
		String sql="UPDATE `is_menu` SET `is_status`=1 WHERE `id`=?";
		Object[] parms={menuid };
		return this.excuteUpdate(sql, parms);
	}

	@Override
	public Menu allMenuById(int id) {
		Menu menu = new Menu();
		ResultSet rs = null;
		String sql = "SELECT * FROM `is_menu` WHERE id=?";
		Object[] parms = {id};
		try {

			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				menu.setId(rs.getInt(1));
				menu.setMname(rs.getString(2));
				menu.setMfatherid(rs.getInt(3));
				menu.setType(rs.getInt(4));
				menu.setUrl(rs.getString(5));
				menu.setJs(rs.getString(6));
				menu.setBtn(rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public int upMenu(Menu menu) {
		String sql = "UPDATE `is_menu` SET mname=?,mfatherid=?,`type`=?,btn=?,url=?  WHERE id = ?";
		Object[] parms = {menu.getMname(),menu.getMfatherid(),menu.getType(),menu.getBtn(),menu.getUrl(),menu.getId()};
		return this.excuteUpdate(sql, parms);
	}
}
