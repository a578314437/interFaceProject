package cn.lixing.Interface.uilt;

import static cn.lixing.Interface.uilt.FileCloseUilt.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDataUilt {
	public static String getPropertiesData(String keyVaule) {
		Properties ps=new Properties();
		BufferedInputStream in=null;
		String classPath=System.getProperty("user.dir");
		try {
			in=new BufferedInputStream(new FileInputStream(classPath+"\\File\\http.properties"));
			ps.load(in);
		} catch (IOException e) {
			close(in);
			e.printStackTrace();
		}
		close(in);
		return ps.getProperty(keyVaule);
	}
}
