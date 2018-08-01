package cn.lixing.Interface.uilt;

import java.io.IOException;
import java.util.Map;


import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class ParseJsonDataUilt {
	
	public static void ParseJsonDataToMap(String jsonData){
		ObjectMapper mapper = new ObjectMapper(); 
		Map<String, Object> jsonMap=null;
		try {
			jsonMap = mapper.readValue(jsonData, Map.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Object mapData : jsonMap.entrySet()) {  
			Map.Entry<String,Object> entry = (Map.Entry<String,Object>) mapData;
			String responseData=entry.getKey()+":"+entry.getValue();
		}
	}
}