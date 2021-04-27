package com.role.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Layui;
import com.entity.MenudtreeData;
import com.entity.Role;
import com.entity.user;
import com.entity.Menu;
import com.role.service.roleServiceImpl;
import com.utils.PrintUtil;
import com.utils.ReturnResult;
import com.web.AbstractServlet;

@WebServlet("/Role")
public class RoleServlet extends AbstractServlet {
	
	
	public void allrole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		Layui<user> layui = new Layui<>();
		layui.setCode(0);
		layui.setCount(new roleServiceImpl().count());
		layui.setMsg("");
		layui.setData(new roleServiceImpl().allrole());
		PrintUtil.write(layui, response);
	}
	/**
	 * 全部的权限信息 dtree 格式
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void allMenuDtree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Layui<MenudtreeData> menu = new roleServiceImpl().dtreeJson();
		PrintUtil.write(menu, response);
	}
	/**
	 * 查询角色的所有权限
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void menuByUseridType1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		List<Menu> menu = new roleServiceImpl().menuByUserId(userid);
		PrintUtil.write(menu, response);
	}
	/**
	 * 分配权限		--先根据用户id删除对应的权限,---在根据选中的权限id添加权限
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult menuByUserid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		String[] menuidList = request.getParameterValues("array");
		ReturnResult result = new ReturnResult();
		int row = new roleServiceImpl().grantMenuByUserid(userid, menuidList);
		if(row != 0 ) {
			result.returnSuccess("分配权限成功");
		}
		return result;
	}
	/**
	 * 新增角色
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public int addRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rname = request.getParameter("rname"); 
		Role role=new Role();
		role.setName(rname);
		int row = new roleServiceImpl().addRole(role);	//执行增加角色方法
		return row;
	}
	/**
	 * 修改的角色信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public Role allRoleByRoleid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		Role role = new roleServiceImpl().allRoleByRoleid(roleid);
		return role;
	}
	/**
	 * 修改
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public int updrole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rname = request.getParameter("rname"); 
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		Role role=new Role();
		role.setName(rname);
		role.setId(roleid);
		int row = new roleServiceImpl().updRole(role);
		return row;
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public int delrole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		int row = new roleServiceImpl().delRole(roleid);
		return row;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return RoleServlet.class;
	}
}
