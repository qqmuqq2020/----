package hr.manage.filter;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import com.mysql.jdbc.Connection;

@Component
public class JDBC {
	    //设置连接路径
	//public static final String url = "jdbc:mysql://127.0.0.1/OnRecordExController";

	public static Connection conn= null;

	public Connection getConnection() {
		
		
		
	/*	jdbc.driver=com.mysql.jdbc.Driver
				jdbc.url=jdbc:mysql://127.0.0.1:3306/cwg_db?characterEncoding=utf-8
				jdbc.user=root
				jdbc.password=qazQAZ123*/

	    // TODO Auto-generated constructor stub
	    try{
	    	String drive=JDBCStr.GetInstance().GetValue("jdbc.driver");
			String url=JDBCStr.GetInstance().GetValue("jdbc.url");
			String name=JDBCStr.GetInstance().GetValue("jdbc.user");
			String password=JDBCStr.GetInstance().GetValue("jdbc.password");
	        Class.forName(drive);//加载驱动
	        conn = (Connection) DriverManager.getConnection(url, name, password);    //ͨ连接数据库
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    return conn;
	}
//关闭数据库
	public  void Close(){
	    try {
	        conn.close();
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}    
	}

