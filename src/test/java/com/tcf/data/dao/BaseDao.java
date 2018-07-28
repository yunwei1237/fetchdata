package com.tcf.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 1.获得连接
 * 2.获得statement
 * 3.执行查询sql
 * 4.执行增删改sql
 * 5.关闭资源
 * @author Administrator
 *
 */
public class BaseDao {
	///参数定义
	private static String driverClass = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/questions";
	private static String username = "root";
	private static String password = "";
	
	/**
	 * 连接对象
	 */
	private Connection connection = null;
	/**
	 * 执行sql对象
	 */
	private PreparedStatement state = null;
	/**
	 * 初始化驱动
	 */
	static{
		//加载驱动
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获得连接
	 * @return
	 */
	public Connection getConnection(){
		//获得连接
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public boolean isInvalidConnection(){
		try {
			if(connection == null || connection.isClosed()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 获得执行sql的对象
	 * 
	 * getPreparedStatement(sql);
	 * getPreparedStatement(sql,"name","pwd");
	 * @param sql语句
	 * @param params参数
	 * @return
	 */
	public PreparedStatement getPreparedStatement(String sql,Object...params){
		if(isInvalidConnection()) getConnection();
		try {
			state = connection.prepareStatement(sql);
			for(int i = 0;i<params.length;i++){
				state.setObject(i+1, params[i]);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	/**
	 * 执行查询的sql语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public ResultSet executeQuery(String sql,Object...params){
		try {
			return getPreparedStatement(sql, params).executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 批量执行增删改
	 * @param sqls
	 * @return
	 */
	public int[] executeBatch(String[] sqls){
		if(isInvalidConnection()) getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			for(String sql : sqls){
				statement.addBatch(sql);
			}
			return statement.executeBatch();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}finally{
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 批量执行增删改
	 * @param sqls
	 * @return
	 */
	public int[] executeBatch(List<String> sqls){
		if(isInvalidConnection()) getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			for(String sql : sqls){
				statement.addBatch(sql);
			}
			return statement.executeBatch();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}finally{
			try {
				if(statement != null) statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 执行增删改的sql语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeNonQuery(String sql,Object...params){
		try {
			return getPreparedStatement(sql, params).executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 关闭资源
	 */
	public void close(){
		try {
			if(state != null) state.close();
			if(connection != null) connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
