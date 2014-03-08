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
   protected long time;
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
      } catch (WriteException ex) {
         Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   protected long addTotal(List<Compactor> threads, int wave) {
      long time = 0;
      for (int index = 0; index < threads.size(); index++) {
         long newTime = threads.get(index).getTime();
         time += newTime;
         try {
            this.basicInfo.addLong(wave + 1, index + 1, newTime);
         } catch (WriteException writeException) {
            Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, writeException);
         }
      }
      try {
         this.basicInfo.addLong(wave + 1, threads.size() + 1, time);
      } catch (WriteException writeException) {
         Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, writeException);
      }
      return time;
   }

   private void addAverageTotal(double total) {
      try {
         System.out.println("total="+total);
         this.basicInfo.addDouble(1, this.numberOfFiles + 2, total);
      } catch (WriteException writeException) {
         Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, writeException);
      }
   }

   public int getNumber() {
      return number;
   }

   public long getTime() {
      return time;
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
      this.time = 0;
      this.numberOfFiles = 0;
      this.basicInfo = new BasicInfo();
   }

   public GenericCompactor(String size, String quantity, String type, int number) {
      this.size = size;
      this.quantity = quantity;
      this.type = type;
      this.way = "Generic";
      this.number = number;
      this.time = 0;
      this.numberOfFiles = 0;
      this.basicInfo = new BasicInfo();
   }

   public long totalTime(int wave) {
      return 0;
   }

   @Override
   public void run() {
      for (int index = 0; index < this.number; index++) {
         long time = totalTime(index);
         System.out.println("" + time);
         this.time += time;
         System.out.println("" + this.time);
      }
      addAverageTotal((double)this.time / this.number);
      try {
         basicInfo.close();
      } catch (IOException | WriteException exception) {
         Logger.getLogger(GenericCompactor.class.getName()).log(Level.SEVERE, null, exception);
      }
   }
}
