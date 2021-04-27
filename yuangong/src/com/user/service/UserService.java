package com.user.service;

import java.util.List;

import com.entity.Menu;
import com.entity.Role;
import com.entity.bumen;
import com.entity.user;
import com.entity.zhi;

public interface UserService {
	/**
	 * 登录
	 * @param loginName
	 * @return
	 */
	user login(String loginName);
	/**
	 * 查询当前用户的权限
	 * @param id
	 * @return
	 */
	List<Menu> selectmenu(int id);
	/**
	 * 查询当前人拥有的按钮权限
	 * @param id
	 * @param userid
	 * @return
	 */
	List<Menu> selectann(int id,int userid);
	/**
	 * 查询所有员工
	 * @return
	 */
	List<user> selectall();
	/**
	 * 查询总员工数
	 * @return
	 */
	int count();
	/**
	 * 新增部门下拉框
	 * @return
	 */
	List<bumen> bumen();
	/**
	 * 新增职称下拉框
	 * @return
	 */
	List<zhi> zhi();
	/**
	 * 新增角色下拉框
	 * @return
	 */
	List<Role> role();
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	int addUser(user user);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	int delUser(int id);
	/**
	 * 查询修改的用户信息
	 * @param id
	 * @return
	 */
	user oneuser(int id);
	/**
	 * 修改查询是否有部门
	 * @param id
	 * @return
	 */
	int ifbumen(int id);
	/**
	 * 修改查询是否有职称
	 * @param id
	 * @return
	 */
	int ifzhi(int id);
	/**
	 * 修改查询是否有角色
	 * @param id
	 * @return
	 */
	int ifrole(int id);
	/**
	 * 根据id查询当前用户部门
	 * @param id
	 * @return
	 */
	bumen userbumen(int id);
	/**
	 * 根据id查询当前用户职称
	 * @param id
	 * @return
	 */
	zhi userzhi(int id);
	/**
	 * 根据id查询当前用户角色
	 * @param id
	 * @return
	 */
	Role userrole(int id);
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	int upduser(user user);
}
