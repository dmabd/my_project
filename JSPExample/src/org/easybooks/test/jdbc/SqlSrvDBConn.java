package org.easybooks.test.jdbc;
import java.sql.*;
public class SqlSrvDBConn {
	private Statement stmt;
    private Connection conn;
    ResultSet rs;
    //在构造方法中创建数据库连接
    public SqlSrvDBConn(){
    	stmt=null;
    	try{
    		/**加载并注册 SQLServer 2008 的 JDBC 驱动*/
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TEST","sa","123456");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	rs=null;
    }
    //获取数据连接
    public Connection getConn(){
    	return this.conn;
    }
    //执行查询类的SQL语句，有返回集
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
    //关闭对象
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
