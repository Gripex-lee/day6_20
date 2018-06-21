package day6_20;
/*
 * 连接数据库
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class J307 {
	 // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  //cj为最新的
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/db?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
/*上面的操作需要注意的东西：
 * 1.useUnicode=true&characterEncoding=utf-8&useSSL=false:
 * 		中文编码以及SSL，
 * 		不用会有警告但是还是会有结果。
 * 2.serverTimezone=UTC:（另一种实现为修改数据库变量）
 * 		在新版的数据库使用的时区与本地时区有区别，
 * 		标准时区使用的是Unix元年的时间为起始点到当前时间中间所做的动作。
 * 		国际标准时区与本地相差 8 个小时。
 */

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
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, url FROM websites";//websites为数据库
            ResultSet rs = stmt.executeQuery(sql);//执行SQL语句
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
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
