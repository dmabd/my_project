package org.easybooks.test.servlet;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import org.easybooks.test.jdbc.SqlSrvDBConn;
import org.easybooks.test.model.vo.*;
public class AddServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("gb2312");
		String title=request.getParameter("title");			//获取留言的标题
		String content=request.getParameter("content");		//获取留言的内容
		HttpSession session=request.getSession();
		//从会话中取出当前用户对象
		UserTable user=(UserTable)session.getAttribute("user");
		//建立留言表对应的JavaBean对象，把数据封装进去
		LyTable ly=new LyTable();
		ly.setUserId(user.getId());							//获取当前登录用户的id
		ly.setDate(new Date(System.currentTimeMillis()));	//获取当前系统时间
		ly.setTitle(title);
		ly.setContent(content);
		ArrayList al=(ArrayList)session.getAttribute("al");
		al.add(ly);											//新添加的留言要保存一份到会话中，这样在刷新主页时就无须每次都去查询数据库留言表了
		//向数据库中插入新的留言记录
		PreparedStatement pstmt=null;
		SqlSrvDBConn sqlsrvdb=new SqlSrvDBConn();
		Connection ct=sqlsrvdb.getConn();
		try{
			pstmt=ct.prepareStatement("insert into lyTable values(?,?,?,?)");
			pstmt.setInt(1, ly.getUserId());
			pstmt.setDate(2, ly.getDate());
			pstmt.setString(3, ly.getTitle());
			pstmt.setString(4, ly.getContent());
			pstmt.executeUpdate();
			response.sendRedirect("main.jsp");
		}catch(SQLException e){
			e.printStackTrace();
			response.sendRedirect("liuyan.jsp");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}
