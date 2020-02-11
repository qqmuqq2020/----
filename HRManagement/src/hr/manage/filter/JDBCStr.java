package hr.manage.filter;

import java.io.InputStream;
import java.util.Properties;

public class JDBCStr {
	static JDBCStr objjdbc;
	static Properties properties = new Properties();
	//单例模式  实例有且只有一个
	public JDBCStr() throws Exception
	{
		     // 使用ClassLoader加载properties配置文件生成对应的输入流
		     InputStream in = JDBCStr.class.getClassLoader().getResourceAsStream(
		    		 "DB.properties");
		     // 使用properties对象加载输入流
		     properties.load(in);
	}
	//定义当前类的实例
	public static JDBCStr GetInstance() throws Exception
	{
		if(objjdbc!=null)
		{
			return objjdbc;
		}
		objjdbc=new JDBCStr();
		return objjdbc;
		
	}
	//编写业务代码
	public static String GetValue(String key)
	{
		return properties.getProperty(key);
	}
	
}
