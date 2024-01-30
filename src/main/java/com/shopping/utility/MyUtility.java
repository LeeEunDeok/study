package com.shopping.utility;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MyUtility {

	public static Map<String, String> getTodolistMap(String fileName) {
		Map<String, String> map = new HashMap<String, String>();
		
		Properties prop = getPropertiesData(fileName);
		
		Enumeration<Object> keys = prop.keys();
		
		while(keys.hasMoreElements()) {
			String command = keys.nextElement().toString();
			String className = prop.getProperty(command);
			
			map.put(command, className);
		}
		
		return map;
	}

	private static Properties getPropertiesData(String fileName) {
		// 해당 파일의 내용을 읽어 들여서 프로퍼티 목록으로 반환해 줍니다.
		FileInputStream fis = null;
		Properties prop = null;
		
		try {
			fis = new FileInputStream(fileName);
			
			prop = new Properties();
			prop.load(fis);
			System.out.print("prop.size() : ");
			System.out.println(prop.size());
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(fis != null) {fis.close();}
				
			}catch(Exception e2) {
				e2.printStackTrace();
				
			}
		}
		
		return prop;
	}
	
}
