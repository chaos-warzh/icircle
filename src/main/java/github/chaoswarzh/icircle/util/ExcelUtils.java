package github.chaoswarzh.icircle.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

  private ExcelUtils() {
  }

  public static Workbook getWorkbook(String sheetName, String[] title, String[][] values, Workbook wb){
    if (wb == null) {
        wb = new XSSFWorkbook();
    }

    Sheet sheet = wb.createSheet(sheetName);
    Row row = sheet.createRow(0);

    CellStyle style = wb.createCellStyle();
    style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

    Cell cell;

    for (int i = 0; i < title.length; i++) {
      cell = row.createCell(i);
      cell.setCellValue(title[i]);
      cell.setCellStyle(style);
    }

    for (int i = 0; i < values.length; i++) {
      row = sheet.createRow(i + 1);
      for (int j = 0; j < values[i].length; j++) {
        row.createCell(j).setCellValue(values[i][j]);
      }
    }
    return wb;
  }
}
