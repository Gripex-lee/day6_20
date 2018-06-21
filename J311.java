package day6_20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class J311 {
	 // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  //cj为最新的
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/db?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";

    static final String USER = "root";//用户名
    static final String PASS = "liwenwu.610";//密码
 
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

//----------------------------------------------------------------------------------------------------------------
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql1;
            sql1 = "INSERT INTO t_user (username,pwd,regTime)VALUES('zhadan','12d56',NOW())";//websites为数据库
            stmt.execute(sql1);//执行SQL语句,需要注意与executeQuery的区别
            String sql2 = "INSERT INTO t_user (username,pwd,regTime)VALUES('zhadsan','123d6',NOW())";//websites为数据库
            stmt.execute(sql2);//执行SQL语句,需要注意与executeQuery的区别
//----------------------------------------------------------------------------------------------------------------        
            System.out.println("删除前：");
            //输出
            String print = "SELECT username,pwd,regTime FROM t_user";//websites为数据库
            ResultSet rs = stmt.executeQuery(print);//执行SQL语句
            // 展开结果集数据库
            while(rs.next()){
                String username = rs.getString("username");
                String pwd = rs.getString("pwd");
                String regTime = rs.getString("regTime");
    
                // 输出数据
                System.out.print("username: " + username);
                System.out.print(", pwd: " + pwd);
                System.out.print(", regTime: " + regTime);
                System.out.print("\n");
            }
            //删除后：
            String sql_new = "DELETE FROM t_user where id=5";//websites为数据库
            stmt.execute(sql_new);
            System.out.println("删除后：");
            //输出
            String prints = "SELECT username,pwd,regTime FROM t_user";//websites为数据库
            ResultSet rss = stmt.executeQuery(prints);//执行SQL语句
            // 展开结果集数据库
            while(rss.next()){
                String username = rss.getString("username");
                String pwd = rss.getString("pwd");
                String regTime = rss.getString("regTime");
    
                // 输出数据
                System.out.print("username: " + username);
                System.out.print(", pwd: " + pwd);
                System.out.print(", regTime: " + regTime);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("End!");
    }
}
