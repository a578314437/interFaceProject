package cn.lixing.Interface.test;

import static cn.lixing.Interface.uilt.ExecutionRequest.*;
import static cn.lixing.Interface.uilt.PropertiesDataUilt.*;
import static cn.lixing.Interface.uilt.SaveResponseDataToFile.*;

public class ActionTest {
	public static void main(String[] args) throws Exception {
		int sheetIndex=Integer.parseInt(getPropertiesData("SheetIndex"));
		writeResponseDataToFile(readRequestData(sheetIndex),sheetIndex);
	}
	
}
