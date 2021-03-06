package com.first.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.first.vo.BoardVO;


public class BoardExcelWriter {

	public void xlsxWriter(List<BoardVO> list) {
        // ��ũ�� ����
        XSSFWorkbook workbook = new XSSFWorkbook();
        // ��ũ��Ʈ ����
        XSSFSheet sheet = workbook.createSheet();
        // �� ����
        XSSFRow row = sheet.createRow(0);
        // �� ����
        XSSFCell cell;
        
        // ��� ���� ����
        cell = row.createCell(0);
        cell.setCellValue("b_code");
        
        cell = row.createCell(1);
        cell.setCellValue("b_id");
        
        cell = row.createCell(2);
        cell.setCellValue("b_title");
        
        cell = row.createCell(3);
        cell.setCellValue("b_context");
        
        BoardVO vo;
        
        for(int rowIdx=0; rowIdx < list.size(); rowIdx++) {
            vo = list.get(rowIdx);
            
            // �� ����
            row = sheet.createRow(rowIdx+2);
            
            //�࿡ ���� �� ���� : createCell
            cell = row.createCell(0);
            cell.setCellValue(Integer.toString(vo.getB_code()));
            
            cell = row.createCell(1);
            cell.setCellValue(vo.getB_id());
            
            cell = row.createCell(2);
            cell.setCellValue(vo.getB_title());
            
            cell = row.createCell(3);
            cell.setCellValue(vo.getB_context());
            
        }

        File file = new File("C:\\excel\\testWrite.xlsx");
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(workbook!=null) workbook.close();
                if(fos!=null) fos.close();
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


	}
	
}
