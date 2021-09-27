package com.orangehrm.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcelFile {
	
	public String[][] getExcelData(String filename, String sheetName){
		String[][] data= null;
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(filename); // to read file
			XSSFWorkbook wb = new XSSFWorkbook(fis); // letting java understand that the file is excel workbook file 
			XSSFSheet sh = wb.getSheet(sheetName);
			XSSFRow row = sh.getRow(0);
			int noOfRows = sh.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();
			Cell cell;
			data = new String [noOfRows-1][noOfCols];
			for(int i=1; i<noOfRows; i++ ) {
				for(int j=0 ; j<noOfCols ;j++) {
					row = sh.getRow(i);
					cell=row.getCell(j);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					data[i-1][j] =cell.getStringCellValue();
				}
			}	
						
		} catch (Exception e) {	
			e.printStackTrace();			
		}		
		
		return data;
	}
	

}
