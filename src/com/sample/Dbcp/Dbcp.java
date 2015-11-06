package com.sample.Dbcp;
import  java.sql.*;
public class Dbcp
{
	private  Connection ct=null;
	public Connection getConn()
		{
		try
			{
					//连接数据库
		 		Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
				ct=DriverManager.getConnection("jdbc:mysql://112.74.205.71:3306/test?autoReconnect=true","root","yeyuan0110");//连接数据库
			}
catch (Exception ex) {
	ex.printStackTrace();
					 }
			return  ct;
	
	   }
	public static void close(ResultSet rs,PreparedStatement pstm,Connection conn){
		if(rs!=null){
		try{	rs.close();}
		catch(SQLException e){
			e.printStackTrace();
		}
			rs=null;
		}
		if(pstm!=null){
			try{	pstm.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			
			pstm=null;
		}
		if(conn!=null){
			try{	conn.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			conn=null;
		}
	}
	
	
	public static void close(Connection conn,ResultSet rs) {
		if(rs!=null){
		try{	rs.close();}
		catch(SQLException e){
			e.printStackTrace();
		}
			rs=null;
		}
		if(conn!=null){
			try{	conn.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			conn=null;
		}
	}
	
	public static void close(Connection conn,PreparedStatement pstm){
		if(pstm!=null){
			try{	pstm.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			
			pstm=null;
		}
		if(conn!=null){
			try{	conn.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			conn=null;
		}
	}
	
	public static void close(PreparedStatement pstm) {
		if(pstm!=null){
			try{	pstm.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			
			pstm=null;
		}
	}
	
	public static void close(Connection conn,Statement stm,ResultSet rs){

		if(stm!=null){
			try{	stm.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			
			stm=null;
		}
		if(rs!=null){
			try{	rs.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
				rs=null;
			}
			if(conn!=null){
				try{	conn.close();}
				catch(SQLException e){
					e.printStackTrace();
				}
				
				conn=null;
			}
	}
	
	
}
