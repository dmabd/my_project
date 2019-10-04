package org.easybooks.test.jdbc;
import java.sql.*;
public class SqlSrvDBConn {
	private Statement stmt;
    private Connection conn;
    ResultSet rs;
    //�ڹ��췽���д������ݿ�����
    public SqlSrvDBConn(){
    	stmt=null;
    	try{
    		/**���ز�ע�� SQLServer 2008 �� JDBC ����*/
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TEST","sa","123456");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	rs=null;
    }
    //��ȡ��������
    public Connection getConn(){
    	return this.conn;
    }
    //ִ�в�ѯ���SQL��䣬�з��ؼ�
    public ResultSet executeQuery(String sql)
    {
        try
        {
        	stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        	rs=stmt.executeQuery(sql);
        }catch(SQLException e){
        	System.err.println("Data.executeQuery: " + e.getMessage());
        }
        return rs;
    }
    //�رն���
    public void closeStmt()
    {
        try
        {
            stmt.close();
        }catch(SQLException e){
        	System.err.println("Data.executeQuery: " + e.getMessage());
        }
    }
    public void closeConn()
    {
        try
        {
            conn.close();
        }catch(SQLException e){
        	System.err.println("Data.executeQuery: " + e.getMessage());
        }
    }
}
