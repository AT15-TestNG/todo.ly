package utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class XssReader {
    private static XSSFSheet excelWorksheet;
    public static void setExcelWorksheet(String path, String sheetName) throws IOException {
        FileInputStream excelFile = new FileInputStream(path);
        XSSFWorkbook excelWorkbook = new XSSFWorkbook(excelFile);
        excelWorksheet = excelWorkbook.getSheet(sheetName);
    }

    public static String getCellData(int rowNum, int colNum) {
        XSSFCell cell = excelWorksheet.getRow(rowNum).getCell(colNum);
        return cell.getStringCellValue();
    }
}
