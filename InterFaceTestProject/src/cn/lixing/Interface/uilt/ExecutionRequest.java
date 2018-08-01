package cn.lixing.Interface.uilt;

import static cn.lixing.Interface.uilt.FileCloseUilt.*;
import static cn.lixing.Interface.uilt.PropertiesDataUilt.*;
import static cn.lixing.Interface.uilt.ReadJsonUilt.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExecutionRequest {
	private static Logger logger=Logger.getLogger(ExecutionRequest.class);
	public static Map<String,List<Object>> readRequestData(int sheetIndex) {
		
		BufferedInputStream in=null;
		Workbook workbook=null;
		List<Object>interFaceLabelNamesList=new ArrayList<>();
		List<Object>urlLabelNamesList=new ArrayList<>();
		List<Object>requestLabelNamesList=new ArrayList<>();
		List<Object>responseLabelNamesList=new ArrayList<>();
		List<Object>consumingLabelNamesList=new ArrayList<>();
		
		List<Object>interFaceDetailNames=new ArrayList<>();
		List<Object>requestUrlsList=new ArrayList<>();
		List<Object>requestDatasList=new ArrayList<>();
		List<Object>responseDatasList=new ArrayList<>();
		List<Object>consumingsList=new ArrayList<>();
		
		Map<String,List<Object>>interFaceDataMap=new HashMap<>();
		try {
			in=new BufferedInputStream(new FileInputStream(getPropertiesData("interfaceFile")));
			workbook=WorkbookFactory.create(in);
			Sheet sheet=workbook.getSheetAt(sheetIndex);
			//获取总行数
			int rowLength=sheet.getLastRowNum();
			String interFaceLabelName=null;
			String urlLabelName=null;
			String requestLabelName=null;
			String responseLabelName=null;
			String consumingLabelName=null;
			
			String interFaceDetailName=null;
			String requestUrl=null;
			String requestData=null;
			String responseData=null;
			String responseTime;
			Row row =null;
			row= sheet.getRow(0);
			for(int i=0;i<rowLength;i++) {
				interFaceLabelName=row.getCell(0).getStringCellValue();
				urlLabelName=row.getCell(1).getStringCellValue();
				requestLabelName=row.getCell(2).getStringCellValue();
				responseLabelName=row.getCell(3).getStringCellValue();
				consumingLabelName=row.getCell(4).getStringCellValue();
				
				interFaceLabelNamesList.add(interFaceLabelName);
				urlLabelNamesList.add(urlLabelName);
				requestLabelNamesList.add(requestLabelName);
				responseLabelNamesList.add(responseLabelName);
				consumingLabelNamesList.add(consumingLabelName);
				
				interFaceDataMap.put("interFaceLabelName", interFaceLabelNamesList);
				interFaceDataMap.put("urlLabelName", urlLabelNamesList);
				interFaceDataMap.put("requestLabelName", requestLabelNamesList);
				interFaceDataMap.put("responseLabelName",responseLabelNamesList);
				interFaceDataMap.put("responseTimeLabelName",consumingLabelNamesList);
			}
			for(int i=0;i<rowLength;i++) {
				row= sheet.getRow(i+1);
				interFaceDetailName=row.getCell(0).getStringCellValue();
				logger.info(interFaceDetailName);
				requestUrl=row.getCell(1).getStringCellValue();
				logger.info(requestUrl);
				requestData=row.getCell(2).getStringCellValue();
				logger.info(requestData);
				long startTime=System.currentTimeMillis();
				responseData=getHttpJsonData(requestData,requestUrl);
				responseTime=Long.toString(System.currentTimeMillis()-startTime);
				
				logger.info(responseData);
				logger.info(responseTime);
				
				interFaceDetailNames.add(interFaceDetailName);
				requestUrlsList.add(requestUrl);
				requestDatasList.add(requestData);
				responseDatasList.add(responseData);
				consumingsList.add(responseTime);
				
				interFaceDataMap.put("interFaceDetailName", interFaceDetailNames);
				interFaceDataMap.put("requestUrl", requestUrlsList);
				interFaceDataMap.put("requestData", requestDatasList);
				interFaceDataMap.put("responseData", responseDatasList);
				interFaceDataMap.put("responseTime", consumingsList);	
			}
		} catch (FileNotFoundException e) {
			close(in);
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			close(workbook);
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			close(workbook);
			e.printStackTrace();
		} catch (IOException e) {
			close(workbook);
			e.printStackTrace();
		}
		close(in);
		close(workbook);
		return interFaceDataMap;
	}
	public static void main(String[] args) {
		Map<String, List<Object>>mapList=readRequestData(Integer.parseInt(getPropertiesData("SheetIndex")));
		for(String key:mapList.keySet()) {
			System.out.print(mapList.get(key));
		}
	}	
}
