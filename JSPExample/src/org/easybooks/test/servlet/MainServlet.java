package org.easybooks.test.servlet;
import java.sql.*;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.easybooks.test.jdbc.SqlSrvDBConn;
import org.easybooks.test.model.vo.*;
public class MainServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("gb2312");				//设置请求编码
		String usr=request.getParameter("username");		//获取提交的用户名
		String pwd=request.getParameter("password");		//获取提交的密码
		boolean validated=false;							//验证成功标识
		SqlSrvDBConn sqlsrvdb=new SqlSrvDBConn();
		HttpSession session=request.getSession();			//获得会话对象，用来保存当前登录用户的信息
		UserTable user=null;
    	//先获得UserTable对象，如果是第一次访问该页，用户对象肯定为空，但如果是第二次甚至是第三次，就直接登录主页而无须再次重复验证该用户的信息
    	user=(UserTable)session.getAttribute("user");
    	//如果用户是第一次进入，会话中尚未存储user持久化对象，故为null
    	if(user==null){
    		//查询userTable表中的记录
    		String sql="select * from userTable";
    		ResultSet rs=sqlsrvdb.executeQuery(sql);		//取得结果集
    		try {
				while(rs.next())
				{
					if((rs.getString("username").trim().compareTo(usr)==0)&&(rs.getString("password").compareTo(pwd)==0)){
						user=new UserTable();				//创建持久化的JavaBean对象user
						user.setId(rs.getInt(1));
						user.setUsername(rs.getString(2));
						user.setPassword(rs.getString(3));
						session.setAttribute("user", user);	//把user对象存储在会话中
						validated=true;						//标识为true表示验证成功通过
					}
				}
	    		rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		sqlsrvdb.closeStmt();
        }
        else{
        	validated=true;									//该用户在之前已登录过并成功验证，故标识为true表示无须再验了
        }
        if(validated)
        {
            //验证成功，应该去主界面，主界面中包含了所有留言信息，所以要从留言表中查出来，并暂存在会话中
        	ArrayList al=new ArrayList();
        	try{
        		String sql="select * from lyTable";
        		ResultSet rs=sqlsrvdb.executeQuery(sql);	//取得结果集
        		while(rs.next()){
        			LyTable ly=new LyTable();
        			ly.setId(rs.getInt(1));
        			ly.setUserId(rs.getInt(2));        			 
        			ly.setDate(rs.getDate(3));
        			ly.setTitle(rs.getString(4));
        			ly.setContent(rs.getString(5));
        			al.add(ly);
        		}
        		rs.close();
        	}catch(SQLException e){
        		e.printStackTrace();
        	}
        	sqlsrvdb.closeStmt();
        	session.setAttribute("al", al);
        	//然后跳转到main.jsp
        	response.sendRedirect("main.jsp");
        }
        else{
            //验证失败跳转到error.jsp
        	response.sendRedirect("error.jsp");
        }
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}
