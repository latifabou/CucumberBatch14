package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    static Workbook book;
    static Sheet sheet;
    //this method will open the Excel book
    public static void openExcel(String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            book = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //this method will open the Excel work sheet
    public static void getSheet(String sheetName){
        sheet = book.getSheet(sheetName);
    }
    //this method will give the row count
    public static int getRowCount(){
        //the number of physically defined rows in this sheet. Not include the empty row
        return sheet.getPhysicalNumberOfRows();
    }
    //this method will give the column count
    public static int getColsCount(int rowIndex){
        //getPhysicalNumberOfCells returns the number of cell in a row that have a content
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    //this method will give the cell data in string format
    public static String getCellData(int rowIndex, int colIndex){
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }

//this method will return list of maps having all the data from Excel file

    public static List<Map<String, String>> excelListIntoMap
            (String filePath, String sheetName){
        openExcel(filePath);
        getSheet(sheetName);

        //creating a list of maps for all the rows
        List<Map<String, String>> listData = new ArrayList<>();

        //loops - outer loop is always take care of rows
        for (int row=1; row<getRowCount(); row++){
            //creating a map for every row
            Map<String, String> map = new LinkedHashMap<>();

            for (int col=0; col<getColsCount(row); col++){
                map.put(getCellData(0, col), getCellData(row, col));
            }
            listData.add(map);
        }
        return listData;

    }

}
