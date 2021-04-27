package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.utils.DataBaseUtil;

/**
 * 数据库管理！
 * 
 * @author Administrator
 *
 */
public class BaseDao {
	// 声明Connection对象属�?�！
	private Connection conn = null;

	/**
	 * 带参构�?�方法！
	 * 
	 * @param conn
	 */
	public BaseDao(Connection conn) {
		// 通过构�?�方法的形式给Connection赋�?�！
		this.conn = conn;
	}

	/**
	 * 增！删！改！操作！！
	 * 
	 * @param sql
	 * @param parms
	 * @return
	 */
	protected int excuteUpdate(String sql, Object[] parms) {
		// 声明PreparedStatement对象�?
		PreparedStatement pstms = null;
		// 声明变量接受受影响行数！
		int result = -1;
		try {
			// 执行SQL语句�?
			pstms = conn.prepareStatement(sql);
			// 判断返回值结果是否为空！
			if (parms != null) {
				// 循环遍历Object数组�?
				for (int i = 0; i < parms.length; i++) {
					// 循环为SQL语句赋�?�！
					pstms.setObject(i + 1, parms[i]);
				}
				// 调用增删改方法！
				result = pstms.executeUpdate();

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			// 关闭资源�?
			DataBaseUtil.closeAll(null, pstms, null);
		}
		return result;
	}

	/**
	 * 查询操作�?
	 * 
	 * @param sql
	 * @param parms
	 * @return
	 */
	protected ResultSet excuteQuery(String sql, Object[] parms) {
		// 声明PreparedStatement对象�?
		PreparedStatement pstms = null;
		// 声明ResultSet对象用于返回查询到的结果集！
		ResultSet result = null;
		try {
			// 执行SQL语句�?
			pstms = conn.prepareStatement(sql);
			// 判断返回值结果是否为空！
			if (parms != null) {
				// 循环遍历Object数组�?
				for (int i = 0; i < parms.length; i++) {
					// 循环为SQL语句赋�?�！
					pstms.setObject(i + 1, parms[i]);
				}
			}
			// 调用查询方法�?
			result = pstms.executeQuery();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
}
