/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Compactor.GenericCompactor;
import Parallel.ParallelCompactor;
import Serial.SerialCompactor;
import info.BasicInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.write.WriteException;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class ZipComp {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
//      List<GenericCompactor> threads = new ArrayList();
      List<CompactorInformation> compactorInformation = new ArrayList();
      int[] qty = {10, 50, 100};
      String[] type = {"Text", "Image", "Audio"};//, "Video", "Exe"};
      
      for (int typeNumber = 0; typeNumber < 2; typeNumber++) {
         for (String type1 : type) {
            for (int index1 = 100; index1 < 100000; index1 = index1 * 10) {
               String sizeString;
               if (index1 >= 1000) {
                  sizeString = index1 / 1000 + "MB";
               } else {
                  sizeString = index1 + "KB";
               }
               for (int index2 = 0; index2 < qty.length; index2++) {
                  if (typeNumber < 1) {
                     compactorInformation.add(new CompactorInformation(sizeString, qty[index2] + "", type1, "Serial"));
                  } else {
                     compactorInformation.add(new CompactorInformation(sizeString, qty[index2] + "", type1, "Parallel"));
                  }
                  System.out.println("numero:" + compactorInformation.size());
               }
            }
         }
      }

      for (int index = 0; index < compactorInformation.size(); index++) {
         try {
            GenericCompactor compactor;
            if (compactorInformation.get(index).getWay().equals("Serial")) {
               compactor = new SerialCompactor(compactorInformation.get(index).getSize(), compactorInformation.get(index).getQuantity(), compactorInformation.get(index).getType());

            } else {
               compactor = new ParallelCompactor(compactorInformation.get(index).getSize(), compactorInformation.get(index).getQuantity(), compactorInformation.get(index).getType());
            }
            compactor.start();
            System.out.println("Thread " + index + " of " + compactorInformation.size() + " started!");
            System.out.println("Information:");
            System.out.println("Way: " + compactorInformation.get(index).getWay());
            System.out.println("Size: " + compactorInformation.get(index).getSize());
            System.out.println("Type: " + compactorInformation.get(index).getType());
            System.out.println("Quantity: " + compactorInformation.get(index).getQuantity());
            compactor.join();
            compactorInformation.get(index).setTotalTime(compactor.getTotalTime());
            compactorInformation.get(index).setAverageTime(compactor.getAverageTime());
            compactorInformation.get(index).setMaxTime(compactor.getMaxTime());
            compactorInformation.get(index).setMinTime(compactor.getMinTime());
         } catch (InterruptedException ex) {
            Logger.getLogger(ZipComp.class.getName()).log(Level.SEVERE, null, ex);
         }
      }

      BasicInfo basicInfo = new BasicInfo();
      basicInfo.setOutputFile("Files/" + "BasicInfo.xls");

      try {
         basicInfo.createMultiFileReport();
         basicInfo.addTime("Available processors (cores)", Runtime.getRuntime().availableProcessors(), 0);
         basicInfo.addTime("Free memory (bytes)", Runtime.getRuntime().freeMemory(), 1);
         long maxMemory = Runtime.getRuntime().maxMemory();

         basicInfo.addTime("Maximum memory (bytes)", (long) (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory), 2);
         basicInfo.addTime("Total memory available to JVM (bytes)", Runtime.getRuntime().totalMemory(), 3);
         basicInfo.addStrings("Compress", "Serial Time", "Serial Max Time", "Serial Min Time", "Parallel Time", "Parallel Max Time", "Parallel Min Time", 4);

      } catch (WriteException writeException) {
         Logger.getLogger(ParallelCompactor.class.getName()).log(Level.SEVERE, null, writeException);
      } catch (IOException ex) {
         Logger.getLogger(ZipComp.class.getName()).log(Level.SEVERE, null, ex);
      }

      for (int index = 0; index < compactorInformation.size()/2; index++) {
         try {
            basicInfo.addString("Size:" + compactorInformation.get(index).getSize() + ", Quantity:" + compactorInformation.get(index).getQuantity() + ", Type:" + compactorInformation.get(index).getType(), index + 5);
            basicInfo.addDouble(1, index + 5, compactorInformation.get(index).getAverageTime());
            basicInfo.addLong(2, index + 5, compactorInformation.get(index).getMaxTime());
            basicInfo.addLong(3, index + 5, compactorInformation.get(index).getMinTime());
            basicInfo.addDouble(4, index + 5, compactorInformation.get(index+(compactorInformation.size()/2)).getAverageTime());
            basicInfo.addLong(5, index + 5, compactorInformation.get(index+(compactorInformation.size()/2)).getMaxTime());
            basicInfo.addLong(6, index + 5, compactorInformation.get(index+(compactorInformation.size()/2)).getMinTime());
         } catch (WriteException writeException) {
            Logger.getLogger(ParallelCompactor.class.getName()).log(Level.SEVERE, null, writeException);
         }
      }

      try {
         basicInfo.close();
      } catch (IOException | WriteException exception) {
         Logger.getLogger(ParallelCompactor.class.getName()).log(Level.SEVERE, null, exception);
      }
   }

}
