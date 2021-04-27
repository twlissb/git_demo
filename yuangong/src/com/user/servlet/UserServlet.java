package com.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Layui;
import com.entity.Menu;
import com.entity.Role;
import com.entity.zhi;
import com.entity.bumen;
import com.entity.user;
import com.user.service.UserServiceImpl;
import com.utils.EmptyUtils;
import com.utils.PrintUtil;
import com.utils.ReturnResult;
import com.web.AbstractServlet;

@WebServlet("/User")
public class UserServlet extends AbstractServlet {

	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return UserServlet.class;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	/**
	 * 登录并查询当前用户的权限
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginName = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		ReturnResult result = new ReturnResult();
		user user = new UserServiceImpl().login(loginName);
		if (user.getLoginname().equals(loginName) && user.getPwd().equals(pwd)) {
			List<Menu> list = new UserServiceImpl().selectmenu(user.getRoleid());
			request.getSession().setAttribute("list", list);
			request.getSession().setAttribute("user", user);
			return result.returnSuccess("登陆成功");
		} else {
			return result.returnFail("登陆失败");
		}
	}

	/**
	 * 查询当前人拥有的按钮权限
	 */
	public String anniu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String uid = request.getParameter("uid");
		List<Menu> list2 = new UserServiceImpl().selectann(Integer.valueOf(id), Integer.valueOf(uid));
		request.getSession().setAttribute("list2", list2);
		return "you";
	}

	/**
	 * 查询所有员工
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectall(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("uname");
		Layui<user> layui = new Layui<user>();
		layui.setCode(0);
		layui.setCount(new UserServiceImpl().count());
		layui.setMsg("");
		layui.setData(new UserServiceImpl().selectall());
		PrintUtil.write(layui, response);
	}

	/**
	 * 查询部门下拉框
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult bumen(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnResult result = new ReturnResult();
		List<bumen> list = new UserServiceImpl().bumen();
		result.returnSuccess(list);
		return result;
	}

	/**
	 * 查询职称下拉框
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult zhi(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnResult result = new ReturnResult();
		List<com.entity.zhi> list = new UserServiceImpl().zhi();
		result.returnSuccess(list);
		return result;
	}

	/**
	 * 查询角色下拉框
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult role(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnResult result = new ReturnResult();
		List<Role> list = new UserServiceImpl().role();
		result.returnSuccess(list);
		return result;
	}
	/**
	 * 新增用户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public int addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginname = request.getParameter("name");
		String pwd = request.getParameter("pass");
		String name = request.getParameter("realName");
		int sex = Integer.parseInt(request.getParameter("sex"));
		int age=Integer.parseInt(request.getParameter("age"));
		int bumenid = Integer.parseInt(request.getParameter("role"));
		int zhiid = Integer.parseInt(request.getParameter("role2"));
		int roleid = Integer.parseInt(request.getParameter("role3"));
		user user = new user();
		user.setLoginname(loginname);;
		user.setName(name);
		user.setPwd(pwd);
		user.setSex(sex);
		user.setAge(age);
		user.setBumenid(bumenid);
		user.setZhiid(zhiid);
		user.setRoleid(roleid);
		int isYes = 0; // 返回信息
		int row = new UserServiceImpl().addUser(user);
		if (row > 0) { // 判断用户是否增加成功
			// 根据用户登录名查询用户的信息
			isYes = 1;
		}
		return isYes;
	}
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public int  delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		int count = 0;
		int row = new UserServiceImpl().delUser(userid);	//先删除权限--> 角色 -->用户
		if(row != -1) {
			count = 1;
		}
		return count;
	}
	/**
	 * 查询修改的用户的信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult oneuser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReturnResult result = new ReturnResult();
		int userid = Integer.parseInt(request.getParameter("userid"));
		user user = new UserServiceImpl().oneuser(userid);
		result.returnSuccess(user);
		return result;
	}
	/**
	 * 查询用户是否有部门
	 * @param request
	 * @param response
	 * @return 没有返回0
	 * @throws ServletException
	 * @throws IOException
	 */
	public int ifbumen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int row = 0;
		int userid = Integer.parseInt(request.getParameter("userid"));
		row = new UserServiceImpl().ifbumen(userid);
		return row;
	}
	/**
	 * 查询用户是否有职称
	 * @param request
	 * @param response
	 * @return 没有返回0
	 * @throws ServletException
	 * @throws IOException
	 */
	public int ifzhi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int row = 0;
		int userid = Integer.parseInt(request.getParameter("userid"));
		row = new UserServiceImpl().ifzhi(userid);
		return row;
	}
	/**
	 * 查询用户是否有角色
	 * @param request
	 * @param response
	 * @return 没有返回0
	 * @throws ServletException
	 * @throws IOException
	 */
	public int ifrole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int row = 0;
		int userid = Integer.parseInt(request.getParameter("userid"));
		row = new UserServiceImpl().ifrole(userid);
		return row;
	}
	/**
	 * 根据用户id查询用户的部门
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult userbumen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReturnResult result = new ReturnResult();
		int userid = Integer.parseInt(request.getParameter("userid"));
		bumen bumen = new UserServiceImpl().userbumen(userid);
		if(EmptyUtils.isEmpty(bumen)) {
			result.returnSuccess(0);
		}else {
			result.returnSuccess(bumen);
		}
		return result;
	}
	/**
	 * 根据用户id查询用户的职称
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult userzhi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReturnResult result = new ReturnResult();
		int userid = Integer.parseInt(request.getParameter("userid"));
		zhi zhi = new UserServiceImpl().userzhi(userid);
		if(EmptyUtils.isEmpty(zhi)) {
			result.returnSuccess(0);
		}else {
			result.returnSuccess(zhi);
		}
		return result;
	}
	/**
	 * 根据用户id查询用户的角色
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult userrole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReturnResult result = new ReturnResult();
		int userid = Integer.parseInt(request.getParameter("userid"));
		Role role = new UserServiceImpl().userrole(userid);
		if(EmptyUtils.isEmpty(role)) {
			result.returnSuccess(0);
		}else {
			result.returnSuccess(role);
		}
		return result;
	}
	/**
	 * 管理员修改用户信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public int upduser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("uid"));	//编号
		String loginname = request.getParameter("name");	//登录名
		String pwd = request.getParameter("pass");	//密码
		String name = request.getParameter("realName");	//昵称
		int age = Integer.parseInt(request.getParameter("age"));	//年龄
		int sex = Integer.parseInt(request.getParameter("sex"));	//性别
		int bumenid = Integer.parseInt(request.getParameter("role"));	//修改后部门id
		int zhiid = Integer.parseInt(request.getParameter("role2"));	//修改后职称id
		int roleid = Integer.parseInt(request.getParameter("role3"));	//修改后角色id
		
		int isYes = 0;//记录到哪步
		user user  = new user();	//修改后的值
		user.setId(userid);
		user.setLoginname(loginname);
		user.setPwd(pwd);
		user.setAge(age);
		user.setName(name);
		user.setSex(sex);
		user.setBumenid(bumenid);
		user.setZhiid(zhiid);
		user.setRoleid(roleid);
		
		int row = new UserServiceImpl().upduser(user);	//修改用户信息
		if(row != -1) {
			isYes = 1;//1用户信息修改成功
		}
		return isYes;
	}
}
