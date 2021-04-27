package com.role.service;

import java.util.List;

import com.entity.Layui;
import com.entity.MenudtreeData;
import com.entity.Role;
import com.entity.user;
import com.entity.Menu;

public interface roleService {
	/**
	 * 全部角色
	 * @param num
	 * @param num1
	 * @return
	 */
	List<user> allrole();
	/**
	 * 角色数
	 * @return
	 */
	int count();
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	int addRole(Role role);
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	int updRole(Role role);
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	int delRole(int id);
	/**
	 * 修改的角色信息
	 * @param roleid
	 * @return
	 */
	Role allRoleByRoleid(int roleid);
	/**
	 * 所有权限
	 * @return
	 */
	Layui<MenudtreeData> dtreeJson();
	/**
	 * 当前角色权限
	 * @param userid
	 * @return
	 */
	List<Menu> menuByUserId(int userid);
	int grantMenuByUserid(int userid, String[] array);//(分配权限) 1---更具用户id删除用户拥有的权限		2--在根据用户选择的权限给用户添加权限
	int delUser_MenuByUserid(int userid);//1---更具用户id删除用户拥有的权限
	int addUser_MenuByUserid(int userid,int menuid);// 2.根据用户选择的权限给用户添加权限
}
