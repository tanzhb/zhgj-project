package com.congmai.zhgj.core.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	private Workbook wb;
    private Sheet sheet;
    private Row row;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
	  /**
     * 
     * @param filepath Excel文件路径
     * @throws Exception
     */
    public ExcelReader(String filepath) throws Exception {
        InputStream is = new FileInputStream(filepath);
        wb = WorkbookFactory.create(is);
        sheet = wb.getSheetAt(0);
    }
    
    public ExcelReader(InputStream is)  throws Exception {
        wb = WorkbookFactory.create(is);
        sheet = wb.getSheetAt(0);
    }
    
    public ExcelReader(String filepath,int sheetNo) throws Exception {
        InputStream is = new FileInputStream(filepath);
        wb = WorkbookFactory.create(is);
        sheet = wb.getSheetAt(sheetNo);
    }
    
    public ExcelReader(InputStream is,int sheetNo)  throws Exception {
        wb = WorkbookFactory.create(is);
        sheet = wb.getSheetAt(sheetNo);
    }
    
    /**
     * 读取Excel数据内容
     */
    public void readExcelContent(RowHandler rowHandler) throws Exception {
        readExcelContent(rowHandler,0);
    }
    
    /**
     * 读取Excel数据内容
     */
    public void readExcelContent(RowHandler rowHandler,int startRow) throws Exception {
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        if(startRow > rowNum){
            return;
        }
        for (int i = startRow ; i <= rowNum; i++) {
            row = sheet.getRow(i);
            if(row == null){
                continue;
            }
            int colNum = row.getPhysicalNumberOfCells();
            List<Object> cellValueList = new ArrayList<Object>(colNum);
            for (int j = 0; j <= colNum; j++) {
                cellValueList.add(getCellFormatValue(row.getCell(j)));
            }
            rowHandler.handle(cellValueList,i);
        }
    }
    

    /**
     * 根据XSSFCell类型设置数据
     * @param xssfCell
     * @return
     */
    private Object getCellFormatValue(Cell cell) {
    	Object cellvalue = null;
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    cellvalue = date;
                } else {
                    // 如果是纯数字取得当前Cell的数值
                	DecimalFormat df = new DecimalFormat("0");  
                	cellvalue = df.format(cell.getNumericCellValue()); 
                }
                break;
            }
            // 如果当前Cell的Type为STRING
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = "";
            }
        }
        return cellvalue;
    }


    public interface RowHandler {
        void handle(final List<Object> row,final int i) throws Exception;
    }
}
