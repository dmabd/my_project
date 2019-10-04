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
		String title=request.getParameter("title");			//��ȡ���Եı���
		String content=request.getParameter("content");		//��ȡ���Ե�����
		HttpSession session=request.getSession();
		//�ӻỰ��ȡ����ǰ�û�����
		UserTable user=(UserTable)session.getAttribute("user");
		//�������Ա��Ӧ��JavaBean���󣬰����ݷ�װ��ȥ
		LyTable ly=new LyTable();
		ly.setUserId(user.getId());							//��ȡ��ǰ��¼�û���id
		ly.setDate(new Date(System.currentTimeMillis()));	//��ȡ��ǰϵͳʱ��
		ly.setTitle(title);
		ly.setContent(content);
		ArrayList al=(ArrayList)session.getAttribute("al");
		al.add(ly);											//����ӵ�����Ҫ����һ�ݵ��Ự�У�������ˢ����ҳʱ������ÿ�ζ�ȥ��ѯ���ݿ����Ա���
		//�����ݿ��в����µ����Լ�¼
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
