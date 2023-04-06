package org.shahdat.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;
import java.util.*;

public class ExcelUtils {
    private static XSSFCell Cell;
    private static XSSFRow Row;

    public static Iterator<Object[]> getExcelData(String sheetName) {
        try {
            InputStream inputStream = ExcelUtils.class.getClassLoader().getResourceAsStream("TestData.xlsx");
            assert inputStream != null;
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            XSSFSheet sh = wb.getSheet(sheetName);
            XSSFRow row = sh.getRow(0);
            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();
            Cell cell;
            List<Object[]> paramObj = new ArrayList<>();
            String[] headers = new String[noOfCols];

            for (int i = 0; i < noOfCols; i++) {
                Cell headerCell = row.getCell(i);
                headers[i] = getCellValue(headerCell);
            }

            for (int i = 1; i < noOfRows; i++) {
                Map<String, String> cellMap = new HashMap<>();
                for (int j = 0; j < headers.length; j++) {
                    row = sh.getRow(i);
                    if (row == null) {
                        cellMap.put(headers[j], "");
                    } else {
                        cell = row.getCell(j);
                        String value = getCellValue(cell);
                        if ("n/a".equalsIgnoreCase(value)) {
                            cellMap.put(headers[j], "");
                        } else {
                            cellMap.put(headers[j], value);
                        }
                    }
                }
                paramObj.add(new Object[]{cellMap});
            }
            return paramObj.iterator();
        } catch (Exception e) {
            System.out.println("The exception is: " + e.getMessage());
            return null;
        }
    }

    private static String getCellValue(Cell cell) {
        DataFormatter formatter = new DataFormatter();
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return "";
        } else {
            return formatter.formatCellValue(cell).trim();
        }
    }



}