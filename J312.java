package day6_20;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class J312 {
	 // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  //cj为最新的
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/db?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    static final String USER = "root";//用户名
    static final String PASS = "liwenwu.610";//密码
 
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
//-------------------------------------------------------------------------------
            String sql;
            sql = "INSERT INTO t_user(username, pwd,regTime)VALUES(?,?,?)";//websites为数据库
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, "liwenwu");
            ps.setString(2, "zheshi.mima");
            ps.setDate(3, new Date(new java.util.Date().getTime()));
            ps.executeUpdate();
//-------------------------------------------------------------------------------            
            String print = "SELECT id,username,pwd,regTime FROM t_user";
            ResultSet rs = stmt.executeQuery(print);
            while(rs.next()){
                System.out.print("id:"+rs.getInt("id"));
                System.out.print(",username: " + rs.getString("username"));
                System.out.print(", pwd: " + rs.getString("pwd"));
                System.out.print(", regTime: " + rs.getDate("regTime"));
                System.out.print("\n");
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
