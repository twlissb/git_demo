package com.menu.service;

import java.util.List;

import com.entity.Button;
import com.entity.Menu;

public interface menuService {
	List<Menu> allMenu();
	int count();
	/**
	 * 新增权限
	 * @param menu
	 * @return
	 */
	int addMenu(Menu menu);
	/**
	 * 查询所有按钮样式
	 * @return
	 */
	List<Button> buttonType();
	/**
	 * 	选择目录的时候去查询对应的上级目录
	 * @return
	 */
	List<Menu> menuByBtn3(int type);
	/**
	 * 伪删除权限
	 * @param menuid
	 * @return
	 */
	int modifyByMenuidIsDelete(int menuid);
	/**
	 * 修改的权限信息
	 * @param id
	 * @return
	 */
	Menu allMenuById(int id);
	/**
	 * 修改权限
	 * @param menu
	 * @return
	 */
	int upMenu(Menu menu);
}
