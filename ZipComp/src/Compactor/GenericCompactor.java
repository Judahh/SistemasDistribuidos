/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compactor;

import info.BasicInfo;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.write.WriteException;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class GenericCompactor extends Thread {

   protected final String size;
   protected final String quantity;
   protected final String type;
   protected String way;
   protected long totalTime;
   protected double averageTime;
   protected long maxTime;
   protected long minTime;
   protected int number;
   protected int numberOfFiles;
   protected BasicInfo basicInfo;

   protected void initialization() {
      basicInfo.setOutputFile("Files/" + size + "/" + quantity + "/" + type + "/" + "Zipped/" + this.way + "/" + "BasicInfo.xls");
      try {
         basicInfo.createSingleFileReport();
      } catch (IOException | WriteException exception) {
         Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, exception);
      }
   }

   protected void addNames(List<Compactor> threads) {
      for (int index = 0; index < threads.size(); index++) {
         try {
            basicInfo.addLabel(index + 1, threads.get(index).getFileName());
         } catch (WriteException writeException) {
            Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, writeException);
         }
      }
      try {
         basicInfo.addLabel(threads.size() + 1, "Total");
         basicInfo.addLabel(threads.size() + 2, "Average Total");
         basicInfo.addLabel(threads.size() + 3, "Max");
         basicInfo.addLabel(threads.size() + 4, "Min");
         basicInfo.addLabel(threads.size() + 5, "Error");
      } catch (WriteException ex) {
         Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   protected long addTotal(List<Compactor> threads, int wave) {
      long tempTime = 0;
      for (int index = 0; index < threads.size(); index++) {
         long newTime = threads.get(index).getTime();
         tempTime += newTime;
         try {
            this.basicInfo.addLong(wave + 1, index + 1, newTime);
         } catch (WriteException writeException) {
            Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, writeException);
         }
      }
      try {
         this.basicInfo.addLong(wave + 1, threads.size() + 1, tempTime);
      } catch (WriteException writeException) {
         Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, writeException);
      }
      return tempTime;
   }

   private void addAverageTotal() {
      try {
         System.out.println("total=" + this.averageTime);
         this.basicInfo.addDouble(1, this.numberOfFiles + 2, this.averageTime);
      } catch (WriteException writeException) {
         Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, writeException);
      }
   }
   
   private void addError() {
      long timeError = this.maxTime - this.minTime;
      try {
         System.out.println("max=" + this.maxTime);
         this.basicInfo.addDouble(1, this.numberOfFiles + 3, this.maxTime);
         System.out.println("min=" + this.minTime);
         this.basicInfo.addDouble(1, this.numberOfFiles + 4, this.minTime);
         System.out.println("error=" + timeError);
         this.basicInfo.addDouble(1, this.numberOfFiles + 5, timeError);
      } catch (WriteException writeException) {
         Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, writeException);
      }
   }

   public int getNumber() {
      return number;
   }

   public String getWay() {
      return way;
   }

   public String getSize() {
      return size;
   }

   public String getType() {
      return type;
   }

   public String getQuantity() {
      return quantity;
   }

   public GenericCompactor(String size, String quantity, String type) {
      this.size = size;
      this.quantity = quantity;
      this.type = type;
      this.way = "Generic";
      this.number = 5;
      this.totalTime = 0;
      this.numberOfFiles = 0;
      this.basicInfo = new BasicInfo();
   }

   public GenericCompactor(String size, String quantity, String type, int number) {
      this.size = size;
      this.quantity = quantity;
      this.type = type;
      this.way = "Generic";
      this.number = number;
      this.totalTime = 0;
      this.numberOfFiles = 0;
      this.basicInfo = new BasicInfo();
   }

   public long totalTime(int wave) {
      return 0;
   }

   public long getMaxTime() {
      return maxTime;
   }

   public long getMinTime() {
      return minTime;
   }

   public long getTotalTime() {
      return totalTime;
   }

   public double getAverageTime() {
      return averageTime;
   }
   
   @Override
   public void run() {
      this.maxTime = 0;
      this.minTime = Long.MAX_VALUE;
      for (int index = 0; index < this.number; index++) {
         long tempTime = totalTime(index);

         if (tempTime > this.maxTime) {
            this.maxTime = tempTime;
         }

         if (tempTime < minTime) {
            this.minTime = tempTime;
         }

         System.out.println("" + tempTime);
         this.totalTime += tempTime;
         System.out.println("" + this.totalTime);
      }
      this.averageTime=(double) this.totalTime / this.number;
      addAverageTotal();
      addError();
      try {
         basicInfo.close();
      } catch (IOException | WriteException exception) {
         Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, exception);
      }
   }
}
