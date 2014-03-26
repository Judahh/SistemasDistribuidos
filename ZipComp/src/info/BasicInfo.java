/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class BasicInfo {

   private WritableCellFormat timesBoldUnderline;
   private WritableCellFormat times;
   private String inputFile;
   private WritableWorkbook workbook;
   private WritableSheet excelSheet;

   public void setOutputFile(String inputFile) {
      this.inputFile = inputFile;
   }

   public void createSingleFileReport() throws IOException, WriteException {
      File file = new File(inputFile);
      WorkbookSettings wbSettings = new WorkbookSettings();

      wbSettings.setLocale(new Locale("en", "EN"));

      this.workbook = Workbook.createWorkbook(file, wbSettings);
      this.workbook.createSheet("Report", 0);
      this.excelSheet = this.workbook.getSheet(0);
      createTimeLabel(excelSheet);
   }

   public void createMultiFileReport() throws IOException, WriteException {
      File file = new File(inputFile);
      WorkbookSettings wbSettings = new WorkbookSettings();

      wbSettings.setLocale(new Locale("en", "EN"));

      this.workbook = Workbook.createWorkbook(file, wbSettings);
      this.workbook.createSheet("Report", 0);
      this.excelSheet = this.workbook.getSheet(0);
      createFinalTimeLabel(excelSheet);
   }

   public void close() throws IOException, WriteException {
      this.workbook.write();
      this.workbook.close();
   }

   private void createFinalTimeLabel(WritableSheet sheet)
           throws WriteException {
      // Lets create a times font
      WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
      // Define the cell format
      times = new WritableCellFormat(times10pt);
      // Lets automatically wrap the cells
      times.setWrap(true);

      // create create a bold font with unterlines
      WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
              UnderlineStyle.SINGLE);
      timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
      // Lets automatically wrap the cells
      timesBoldUnderline.setWrap(true);

      CellView cv = new CellView();
      cv.setFormat(times);
      cv.setFormat(timesBoldUnderline);
      cv.setAutosize(true);

      // Write a few headers
      addCaption(sheet, 0, 0, "File");
      addCaption(sheet, 1, 0, "Time");

   }

   private void createTimeLabel(WritableSheet sheet)
           throws WriteException {
      // Lets create a times font
      WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
      // Define the cell format
      times = new WritableCellFormat(times10pt);
      // Lets automatically wrap the cells
      times.setWrap(true);

      // create create a bold font with unterlines
      WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
              UnderlineStyle.SINGLE);
      timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
      // Lets automatically wrap the cells
      timesBoldUnderline.setWrap(true);

      CellView cv = new CellView();
      cv.setFormat(times);
      cv.setFormat(timesBoldUnderline);
      cv.setAutosize(true);

      // Write a few headers
      addCaption(sheet, 0, 0, "File");
      addCaption(sheet, 1, 0, "Time");

   }

   private void createTimeLabel(WritableSheet sheet, int number)
           throws WriteException {
      // Lets create a times font
      WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
      // Define the cell format
      times = new WritableCellFormat(times10pt);
      // Lets automatically wrap the cells
      times.setWrap(true);

      // create create a bold font with unterlines
      WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
              UnderlineStyle.SINGLE);
      timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
      // Lets automatically wrap the cells
      timesBoldUnderline.setWrap(true);

      CellView cv = new CellView();
      cv.setFormat(times);
      cv.setFormat(timesBoldUnderline);
      cv.setAutosize(true);

      // Write a few headers
      addCaption(sheet, 0, 0, "File");
      for (int index = 0; index < number; index++) {
         if (number == 1) {
            addCaption(sheet, index + 1, 0, "Time");
         } else {
            addCaption(sheet, index + 1, 0, "Time " + index);
         }
      }

   }

   public void addTime(long time, int column, int row) throws WriteException,
           RowsExceededException {
      addLong(this.excelSheet, column, row, time);
   }

   public void addTime(String name, long time, int row) throws WriteException,
           RowsExceededException {
      addLabel(this.excelSheet, 0, row, name);
      addLong(this.excelSheet, 1, row, time);
   }

   public void addTime(WritableSheet sheet, String name, long time, int row) throws WriteException,
           RowsExceededException {
      addLabel(sheet, 0, row, name);
      addLong(sheet, 1, row, time);
   }

   public void addCaption(WritableSheet sheet, int column, int row, String s)
           throws RowsExceededException, WriteException {
      Label label;
      label = new Label(column, row, s, timesBoldUnderline);
      sheet.addCell(label);
   }

   public void addNumber(WritableSheet sheet, int column, int row,
           Integer integer) throws WriteException, RowsExceededException {
      Number number;
      number = new Number(column, row, integer, times);
      sheet.addCell(number);
   }

   public void addDouble(int column, int row,
           double longNumber) throws WriteException, RowsExceededException {
      Number number;
      number = new Number(column, row, longNumber, times);
      this.excelSheet.addCell(number);
   }
   
   public void addLong(int column, int row,
           long longNumber) throws WriteException, RowsExceededException {
      Number number;
      number = new Number(column, row, longNumber, times);
      this.excelSheet.addCell(number);
   }

   public void addLong(WritableSheet sheet, int column, int row,
           long longNumber) throws WriteException, RowsExceededException {
      Number number;
      number = new Number(column, row, longNumber, times);
      sheet.addCell(number);
   }

    public void addStrings(String string0, String string1, String string2, String string3, String string4, String string5, String string6, int row)
           throws WriteException, RowsExceededException {
      addStrings(this.excelSheet, 0, row, string0, string1, string2, string3, string4, string5, string6);
   }
   
   public void addStrings(String string0, String string1, String string2, int row)
           throws WriteException, RowsExceededException {
      addStrings(this.excelSheet, 0, row, string0, string1, string2);
   }
   
   public void addStrings(String string0, String string1, int row)
           throws WriteException, RowsExceededException {
      addStrings(this.excelSheet, 0, row, string0, string1);
   }
   
   public void addString(String string0, int row)
           throws WriteException, RowsExceededException {
      addString(this.excelSheet, 0, row, string0);
   }

   
   public void addStrings(WritableSheet sheet, int column, int row, String string0, String string1, String string2, String string3, String string4, String string5, String string6)
           throws WriteException, RowsExceededException {
      Label label;
      label = new Label(column, row, string0, times);
      sheet.addCell(label);
      label = new Label(column + 1, row, string1, times);
      sheet.addCell(label);
      label = new Label(column + 2, row, string2, times);
      sheet.addCell(label);
      label = new Label(column + 3, row, string3, times);
      sheet.addCell(label);
      label = new Label(column + 4, row, string4, times);
      sheet.addCell(label);
      label = new Label(column + 5, row, string5, times);
      sheet.addCell(label);
      label = new Label(column + 6, row, string6, times);
      sheet.addCell(label);
   }
   
   public void addStrings(WritableSheet sheet, int column, int row, String string0, String string1, String string2)
           throws WriteException, RowsExceededException {
      Label label;
      label = new Label(column, row, string0, times);
      sheet.addCell(label);
      label = new Label(column + 1, row, string1, times);
      sheet.addCell(label);
      label = new Label(column + 2, row, string2, times);
      sheet.addCell(label);
   }
   
   public void addStrings(WritableSheet sheet, int column, int row, String string0, String string1)
           throws WriteException, RowsExceededException {
      Label label;
      label = new Label(column, row, string0, times);
      sheet.addCell(label);
      label = new Label(column + 1, row, string1, times);
      sheet.addCell(label);
   }
   
   public void addString(WritableSheet sheet, int column, int row, String string0)
           throws WriteException, RowsExceededException {
      Label label;
      label = new Label(column, row, string0, times);
      sheet.addCell(label);
   }

   public void addLabel(WritableSheet sheet, int column, int row, String s)
           throws WriteException, RowsExceededException {
      Label label;
      label = new Label(column, row, s, times);
      sheet.addCell(label);
   }

   public void addLabel(int column, int row, String s)
           throws WriteException, RowsExceededException {
      Label label;
      label = new Label(column, row, s, times);
      this.excelSheet.addCell(label);
   }

   public void addLabel(int row, String s)
           throws WriteException, RowsExceededException {
      Label label;
      label = new Label(0, row, s, times);
      this.excelSheet.addCell(label);
   }
}
