package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.entity.BaseDao;
import com.entity.Menu;
import com.entity.Role;
import com.entity.bumen;
import com.entity.user;
import com.entity.zhi;
import com.utils.DataBaseUtil;

public class UserDaoImpl extends BaseDao implements UserDao {
	/**
	 * 日志
	 */
	public static Logger logger = Logger.getLogger(BaseDao.class.getName());

	public UserDaoImpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 登录
	 */
	@Override
	public user login(String loginName) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		user user = null;
		String sql = "SELECT id,loginname,NAME,pwd,sex,age,bumenid,zhiid,roleid FROM USER WHERE loginname=?";
		Object[] parms = { loginName };
		try {
			rs = super.excuteQuery(sql, parms);
			if (rs != null) {
				while (rs.next()) {
					user = new user();
					user.setId(rs.getInt(1));
					user.setLoginname(rs.getString(2));
					user.setName(rs.getString(3));
					user.setPwd(rs.getString(4));
					user.setSex(rs.getInt(5));
					user.setAge(rs.getInt(6));
					user.setBumenid(rs.getInt(7));
					user.setZhiid(rs.getInt(8));
					user.setRoleid(rs.getInt(9));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			DataBaseUtil.closeAll(rs, null, null);
		}
		return user;
	}

	@Override
	public List<Menu> selectmenu(int id) {
		// TODO Auto-generated method stub
		List<Menu> list = new ArrayList<>();
		ResultSet rs = null;
		Menu menu = null;
		String sql = "SELECT id,mname,mfatherid,TYPE,url,js,btn,is_status FROM is_menu WHERE id IN(SELECT menuid FROM menu_role WHERE roleid=?)";
		Object[] parms = { id };
		try {
			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				menu = new Menu();
				menu.setId(rs.getInt(1));
				menu.setMname(rs.getString(2));
				menu.setMfatherid(rs.getInt(3));
				menu.setType(rs.getInt(4));
				menu.setUrl(rs.getString(5));
				menu.setJs(rs.getString(6));
				list.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询当前人拥有的按钮权限
	 */
	@Override
	public List<Menu> selectann(int id, int userid) {
		List<Menu> list = new ArrayList<>();
		ResultSet rs = null;
		Menu menu = null;
		String sql = "SELECT id,mname,mfatherid,TYPE,url,js,btn,is_status FROM `is_menu` WHERE `type`=3 AND mfatherid=? AND id IN(SELECT menuid FROM `menu_role` WHERE roleid=?)";
		Object[] parms = { id, userid };
		try {
			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				menu = new Menu();
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

	/**
	 * 查询所有员工
	 */
	@Override
	public List<user> selectall() {
		List<user> list = new ArrayList<>();
		ResultSet rs = null;
		user user = null;
		String sql = "SELECT a.id aid,loginname,a.name aname,pwd,sex,age,bumenid,zhiid,roleid,b.name bname,c.name cname,d.name dname FROM `user` a,bumen b,zhi c,role d WHERE a.bumenid=b.id AND a.zhiid=c.id AND a.roleid=d.id";
		Object[] parms = {};
		try {
			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				user = new user();
				user.setId(rs.getInt("aid"));
				user.setLoginname(rs.getString("loginname"));
				user.setName(rs.getString("aname"));
				user.setPwd(rs.getString("pwd"));
				user.setSex(rs.getInt("sex"));
				user.setAge(rs.getInt("age"));
				user.setBumenid(rs.getInt("bumenid"));
				user.setZhiid(rs.getInt("zhiid"));
				user.setRoleid(rs.getInt("roleid"));
				user.setBumenname(rs.getString("bname"));
				user.setZhiname(rs.getString("cname"));
				user.setRolename(rs.getString("dname"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int count() {
		ResultSet rs = null;
		int count = 0;
		String sql = "SELECT COUNT(*) FROM `user`";
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

	@Override
	public List<bumen> bumen() {
		List<bumen> list = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT id,NAME FROM `bumen`";
		Object[] parms = {};
		try {
			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				bumen bumen = new bumen();
				bumen.setId(rs.getInt(1));
				bumen.setName(rs.getString(2));
				list.add(bumen);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<zhi> zhi() {
		List<zhi> list = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT id,NAME FROM `zhi`";
		Object[] parms = {};
		try {
			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				zhi zhi = new zhi();
				zhi.setId(rs.getInt(1));
				zhi.setName(rs.getString(2));
				list.add(zhi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Role> role() {
		List<Role> list = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT id,NAME FROM `role`";
		Object[] parms = {};
		try {
			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt(1));
				role.setName(rs.getString(2));
				list.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int addUser(user user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO `user`(`loginname`,`name`,`pwd`,`sex`,`age`,`bumenid`,`zhiid`,`roleid`) VALUES(?,?,?,?,?,?,?,?)";
		Object[] parms = { user.getLoginname(), user.getName(), user.getPwd(), user.getSex(), user.getAge(),
				user.getBumenid(), user.getZhiid(), user.getRoleid() };
		return this.excuteUpdate(sql, parms);
	}

	@Override
	public int delUser(int id) {
		String sql = "DELETE FROM `user` WHERE id=?";
		Object[] parms = { id };
		return this.excuteUpdate(sql, parms);
	}

	@Override
	public user oneuser(int id) {
		ResultSet rs = null;
		user user = null;
		String sql = "SELECT a.id aid,loginname,a.name aname,pwd,sex,age,bumenid,zhiid,roleid,b.name bname,c.name cname,d.name dname FROM `user` a,bumen b,zhi c,role d WHERE a.bumenid=b.id AND a.zhiid=c.id AND a.roleid=d.id AND a.id=?";
		Object[] parms = { id };
		try {
			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				user = new user();
				user.setId(rs.getInt("aid"));
				user.setLoginname(rs.getString("loginname"));
				user.setName(rs.getString("aname"));
				user.setPwd(rs.getString("pwd"));
				user.setSex(rs.getInt("sex"));
				user.setAge(rs.getInt("age"));
				user.setBumenid(rs.getInt("bumenid"));
				user.setZhiid(rs.getInt("zhiid"));
				user.setRoleid(rs.getInt("roleid"));
				user.setBumenname(rs.getString("bname"));
				user.setZhiname(rs.getString("cname"));
				user.setRolename(rs.getString("dname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int ifbumen(int id) {
		ResultSet rs = null;
		int count = 0;
		String sql = "SELECT bumenid FROM `user` WHERE id=?";
		Object[] parms = {id};
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

	@Override
	public int ifzhi(int id) {
		ResultSet rs = null;
		int count = 0;
		String sql = "SELECT zhiid FROM `user` WHERE id=?";
		Object[] parms = {id};
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

	@Override
	public int ifrole(int id) {
		ResultSet rs = null;
		int count = 0;
		String sql = "SELECT roleid FROM `user` WHERE id=?";
		Object[] parms = {id};
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

	@Override
	public com.entity.bumen userbumen(int id) {
		ResultSet rs = null;
		bumen bumen=new bumen();
		String sql = "SELECT id,NAME FROM bumen WHERE id=(SELECT bumenid FROM USER WHERE id=?)";
		Object[] parms = {id};
		try {
			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				bumen.setId(rs.getInt(1));
				bumen.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bumen;
	}

	@Override
	public com.entity.zhi userzhi(int id) {
		ResultSet rs = null;
		zhi zhi=new zhi();
		String sql = "SELECT id,NAME FROM zhi WHERE id=(SELECT zhiid FROM USER WHERE id=?)";
		Object[] parms = {id};
		try {
			rs = super.excuteQuery(sql, parms);
			while (rs.next()) {
				zhi.setId(rs.getInt(1));
				zhi.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zhi;
	}

	@Override
	public com.entity.Role userrole(int id) {
		ResultSet rs = null;
		Role role=new Role();
		String sql = "SELECT id,NAME FROM role WHERE id=(SELECT roleid FROM USER WHERE id=?)";
		Object[] parms = {id};
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
	public int upduser(user user) {
		String sql="UPDATE `user` SET loginname=?,NAME=?,pwd=?,sex=?,age=?,bumenid=?,zhiid=?,roleid=? where id=?";
		Object[] parms = {user.getLoginname(),user.getName(),user.getPwd(),user.getSex(),user.getAge(),user.getBumenid(),user.getZhiid(),user.getRoleid(),user.getId()};
		return this.excuteUpdate(sql, parms);
	}

}
