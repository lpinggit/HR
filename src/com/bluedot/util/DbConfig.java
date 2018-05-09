package com.bluedot.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class DbConfig {
	private JdbcInfo jdbcInfo;
    //1.私有的静态的实例
	private static DbConfig config;
	//2.构造函数是私有：防止外部调用构造函数产生实例
	private DbConfig()
	{
		Document doc;
		SAXReader reader=new SAXReader();
		//reader.read(new File(""));//J2SE可以这样用，文件的路径固定
	      try {
		    doc=reader.read(Class.forName("com.bluedot.util.DbConfig").getClassLoader().getResourceAsStream("config.xml"));
		    //doc=reader.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml"));
			Element urlElement=(Element)doc.selectObject("/config/url");
		    Element usernameElement=(Element)doc.selectObject("/config/username");
		    Element passwordElement=(Element)doc.selectObject("/config/password");
		    String url=urlElement.getStringValue();
		    String username=usernameElement.getStringValue();
		    String password=passwordElement.getStringValue();
		    jdbcInfo=new JdbcInfo();
		    jdbcInfo.setUrl(url);
		    jdbcInfo.setUsername(username);
		    jdbcInfo.setPassword(password);
	      } catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//3.有一个公共的静态方法获取实例
	public static synchronized DbConfig getInstance()
	{
		if(config==null)
		{
			config=new DbConfig();
		}
		
		return config;
	}
	public JdbcInfo getJdbcInfo()
	{
		return jdbcInfo;
		
	}

}
