package cn.lixing.Interface.uilt;

import static cn.lixing.Interface.uilt.FileCloseUilt.close;
import static cn.lixing.Interface.uilt.PropertiesDataUilt.getPropertiesData;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class SaveResponseDataToFile {
	public static void writeResponseDataToFile(Map<String, List<Object>>interFaces,int SheetIndex) {
		
		List<Object>interFaceDetailNames=interFaces.get("interFaceDetailName");
		List<Object>requsetUrlsList=interFaces.get("requestUrl");
		List<Object>requsetDatasList=interFaces.get("requestData");
		List<Object>responseDatasList=interFaces.get("responseData");
		List<Object>responseTimesList=interFaces.get("responseTime");
		
		List<Object>interFaceLabelNamesList=interFaces.get("interFaceLabelName");
		List<Object>urlLabelNamesList=interFaces.get("urlLabelName");
		List<Object>requestLabelNamesList=interFaces.get("requestLabelName");
		List<Object>responseLabelNamesList=interFaces.get("responseLabelName");
		List<Object>responseTimeLabelNamesList=interFaces.get("responseTimeLabelName");
		XSSFWorkbook workbook =null;
		BufferedOutputStream out=null;
		BufferedInputStream in=null;
		XSSFSheet sheet=null;
		try {
			in=new BufferedInputStream(new FileInputStream(getPropertiesData("interfaceFile")));
			workbook=new XSSFWorkbook(in); 
			sheet = workbook.getSheetAt(SheetIndex);
			
			
			sheet.setColumnWidth(0, 50*256);
			sheet.setColumnWidth(1, 50*256);
			sheet.setColumnWidth(2, 50*256);
			
			XSSFRow row = sheet.getRow(0);
			for(int i=0;i<responseDatasList.size();i++) {
				row.createCell(0).setCellValue((String) interFaceLabelNamesList.get(i));
				row.createCell(1).setCellValue((String) urlLabelNamesList.get(i));
				row.createCell(2).setCellValue((String) requestLabelNamesList.get(i));
				row.createCell(3).setCellValue((String) responseLabelNamesList.get(i));
				row.createCell(4).setCellValue((String) responseTimeLabelNamesList.get(i));
			}
			for(int i=0;i<responseDatasList.size();i++) {
				row = sheet.getRow(i+1);
				row.createCell(0).setCellValue((String) interFaceDetailNames.get(i));
				row.createCell(1).setCellValue((String) requsetUrlsList.get(i));
				row.createCell(2).setCellValue((String) requsetDatasList.get(i));
				row.createCell(3).setCellValue((String) responseDatasList.get(i));
				row.createCell(4).setCellValue((String) responseTimesList.get(i)+"ms");
			}
			out=new BufferedOutputStream(new FileOutputStream(getPropertiesData("interfaceFile")));
			
			 
		      
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(out);
			System.out.println("数据保存成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
		close(in);
		close(workbook);
		close(out);
	}
}
