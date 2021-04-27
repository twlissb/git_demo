package com.menu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Button;
import com.entity.Layui;
import com.entity.Menu;
import com.menu.service.menuService;
import com.menu.service.menuServiceImpl;
import com.utils.EmptyUtils;
import com.utils.PrintUtil;
import com.utils.ReturnResult;
import com.web.AbstractServlet;

@WebServlet("/Menu")
public class menuServlet extends AbstractServlet  {

	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return menuServlet.class;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	public void allMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		menuService sv=new menuServiceImpl();
		Layui<Menu> layui = new Layui<Menu>();
		layui.setCode(0);
		layui.setCount(sv.count());
		layui.setMsg("");
		layui.setData(sv.allMenu());
		PrintUtil.write(layui, response);
	}
	/**
	 * 新增权限信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult addMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReturnResult result = new ReturnResult();
		String mname = request.getParameter("mname");
		String fun = request.getParameter("mfunction");
		int type = Integer.parseInt(request.getParameter("type2"));
		String icon = request.getParameter("icon");
		String btn = request.getParameter("mbtn");
		int father = Integer.parseInt(request.getParameter("father"));
		Menu menu = new Menu();
		menu.setMname(mname);
		menu.setType(type);
		menu.setBtn(btn);
		menu.setMfatherid(father);
		
		int row = new menuServiceImpl().addMenu(menu);
		result.returnSuccess(row);
		return result;
	}
	/**
	 * 查询所有的按钮样式
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public List<Button> allButtenType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Button> btnList = new menuServiceImpl().buttonType();
		return btnList;
	}
	/**
	 * 	选择目录的时候去查询对应的上级目录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult menuByType2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type"));
		List<Menu> menuByBtn3 = new menuServiceImpl().menuByBtn3(type-1);
		ReturnResult result = new ReturnResult();
		result.returnSuccess(menuByBtn3);
		return result;
	}
	/**
	 * 删除权限信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult delMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		ReturnResult result = new ReturnResult();
		int row = new menuServiceImpl().modifyByMenuidIsDelete(menuid);
		result.returnSuccess(row);
		return result;
	}
	/**
	 * 根据权限id查询权限的信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult allMenuById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReturnResult rr = new ReturnResult();
		int id = EmptyUtils.isEmpty(request.getParameter("menuid")) ? 0:Integer.parseInt(request.getParameter("menuid"));
		if(id==0) {
			rr.returnFail("请选择需要修改的一项");
		}else {
			Menu menu = new menuServiceImpl().allMenuById(id);
			rr.returnSuccess(menu);
		}
		return rr;
	}
	/**
	 * 修改权限
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult upMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =  Integer.parseInt(request.getParameter("mid"));
		String mname = request.getParameter("mname");// 权限名
		String mfunction = request.getParameter("mfunction");// 请求路径
		int type2 = Integer.parseInt(request.getParameter("type2"));// 请求类型 
		String icon = EmptyUtils.isEmpty(request.getParameter("icon")) ? "&#xe631;":request.getParameter("icon");// icon图标
		String mbtn2 = request.getParameter("mbtn");	//执行体
		String mbtn = EmptyUtils.isEmpty(request.getParameter("mbtn")) ? mname:mbtn2;// 执行体
		int father = Integer.parseInt(request.getParameter("father"));// 父级
		Menu menu = new Menu();
		menu.setId(id);
		menu.setMname(mname);
		menu.setMfatherid(father);
		menu.setType(type2);
		menu.setUrl(mfunction);
		menu.setBtn(mbtn);
		
		int row = new menuServiceImpl().upMenu(menu);
		ReturnResult rr = new ReturnResult();
		rr.returnSuccess(row);
		return rr;
	}
}
