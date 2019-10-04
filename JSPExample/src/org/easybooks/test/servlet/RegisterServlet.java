package org.easybooks.test.servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.easybooks.test.jdbc.SqlSrvDBConn;
import org.easybooks.test.model.vo.*;

public class RegisterServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("gb2312");
		String usr=request.getParameter("username");		//获取提交注册的用户名
		String pwd=request.getParameter("password");		//获取提交注册的密码
		//向数据库中插入新用户名和密码
		PreparedStatement pstmt=null;
		SqlSrvDBConn sqlsrvdb=new SqlSrvDBConn();
		Connection ct=sqlsrvdb.getConn();
		try{
			pstmt=ct.prepareStatement("insert into userTable values(?,?)");
			pstmt.setString(1, usr);
			pstmt.setString(2, pwd);
			pstmt.executeUpdate();
			response.sendRedirect("login.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}
