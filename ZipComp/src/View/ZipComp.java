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
      List<GenericCompactor> threads = new ArrayList();
      threads.add(new SerialCompactor("1MB", "10", "Text"));
      threads.add(new SerialCompactor("1MB", "10", "Video"));
      threads.add(new SerialCompactor("1MB", "10", "Audio"));
      threads.add(new SerialCompactor("1MB", "10", "Image"));
      threads.add(new SerialCompactor("1MB", "10", "Exe"));
      threads.add(new SerialCompactor("1MB", "50", "Text"));
      threads.add(new SerialCompactor("1MB", "50", "Video"));
      threads.add(new SerialCompactor("1MB", "50", "Audio"));
      threads.add(new SerialCompactor("1MB", "50", "Image"));
      threads.add(new SerialCompactor("1MB", "50", "Exe"));
      threads.add(new SerialCompactor("1MB", "100", "Text"));
      threads.add(new SerialCompactor("1MB", "100", "Video"));
      threads.add(new SerialCompactor("1MB", "100", "Audio"));
      threads.add(new SerialCompactor("1MB", "100", "Image"));
      threads.add(new SerialCompactor("1MB", "100", "Exe"));

      threads.add(new SerialCompactor("10MB", "10", "Text"));
      threads.add(new SerialCompactor("10MB", "10", "Video"));
      threads.add(new SerialCompactor("10MB", "10", "Audio"));
      threads.add(new SerialCompactor("10MB", "10", "Image"));
      threads.add(new SerialCompactor("10MB", "10", "Exe"));
      threads.add(new SerialCompactor("10MB", "50", "Text"));
      threads.add(new SerialCompactor("10MB", "50", "Video"));
      threads.add(new SerialCompactor("10MB", "50", "Audio"));
      threads.add(new SerialCompactor("10MB", "50", "Image"));
      threads.add(new SerialCompactor("10MB", "50", "Exe"));
      threads.add(new SerialCompactor("10MB", "100", "Text"));
      threads.add(new SerialCompactor("10MB", "100", "Video"));
      threads.add(new SerialCompactor("10MB", "100", "Audio"));
      threads.add(new SerialCompactor("10MB", "100", "Image"));
      threads.add(new SerialCompactor("10MB", "100", "Exe"));

      threads.add(new SerialCompactor("100MB", "10", "Text"));
      threads.add(new SerialCompactor("100MB", "10", "Video"));
      threads.add(new SerialCompactor("100MB", "10", "Audio"));
      threads.add(new SerialCompactor("100MB", "10", "Image"));
      threads.add(new SerialCompactor("100MB", "10", "Exe"));
      threads.add(new SerialCompactor("100MB", "50", "Text"));
      threads.add(new SerialCompactor("100MB", "50", "Video"));
      threads.add(new SerialCompactor("100MB", "50", "Audio"));
      threads.add(new SerialCompactor("100MB", "50", "Image"));
      threads.add(new SerialCompactor("100MB", "50", "Exe"));
      threads.add(new SerialCompactor("100MB", "100", "Text"));
      threads.add(new SerialCompactor("100MB", "100", "Video"));
      threads.add(new SerialCompactor("100MB", "100", "Audio"));
      threads.add(new SerialCompactor("100MB", "100", "Image"));
      threads.add(new SerialCompactor("100MB", "100", "Exe"));

      threads.add(new ParallelCompactor("1MB", "10", "Text"));
      threads.add(new ParallelCompactor("1MB", "10", "Video"));
      threads.add(new ParallelCompactor("1MB", "10", "Audio"));
      threads.add(new ParallelCompactor("1MB", "10", "Image"));
      threads.add(new ParallelCompactor("1MB", "10", "Exe"));
      threads.add(new ParallelCompactor("1MB", "50", "Text"));
      threads.add(new ParallelCompactor("1MB", "50", "Video"));
      threads.add(new ParallelCompactor("1MB", "50", "Audio"));
      threads.add(new ParallelCompactor("1MB", "50", "Image"));
      threads.add(new ParallelCompactor("1MB", "50", "Exe"));
      threads.add(new ParallelCompactor("1MB", "100", "Text"));
      threads.add(new ParallelCompactor("1MB", "100", "Video"));
      threads.add(new ParallelCompactor("1MB", "100", "Audio"));
      threads.add(new ParallelCompactor("1MB", "100", "Image"));
      threads.add(new ParallelCompactor("1MB", "100", "Exe"));

      threads.add(new ParallelCompactor("10MB", "10", "Text"));
      threads.add(new ParallelCompactor("10MB", "10", "Video"));
      threads.add(new ParallelCompactor("10MB", "10", "Audio"));
      threads.add(new ParallelCompactor("10MB", "10", "Image"));
      threads.add(new ParallelCompactor("10MB", "10", "Exe"));
      threads.add(new ParallelCompactor("10MB", "50", "Text"));
      threads.add(new ParallelCompactor("10MB", "50", "Video"));
      threads.add(new ParallelCompactor("10MB", "50", "Audio"));
      threads.add(new ParallelCompactor("10MB", "50", "Image"));
      threads.add(new ParallelCompactor("10MB", "50", "Exe"));
      threads.add(new ParallelCompactor("10MB", "100", "Text"));
      threads.add(new ParallelCompactor("10MB", "100", "Video"));
      threads.add(new ParallelCompactor("10MB", "100", "Audio"));
      threads.add(new ParallelCompactor("10MB", "100", "Image"));
      threads.add(new ParallelCompactor("10MB", "100", "Exe"));

      threads.add(new ParallelCompactor("100MB", "10", "Text"));
      threads.add(new ParallelCompactor("100MB", "10", "Video"));
      threads.add(new ParallelCompactor("100MB", "10", "Audio"));
      threads.add(new ParallelCompactor("100MB", "10", "Image"));
      threads.add(new ParallelCompactor("100MB", "10", "Exe"));
      threads.add(new ParallelCompactor("100MB", "50", "Text"));
      threads.add(new ParallelCompactor("100MB", "50", "Video"));
      threads.add(new ParallelCompactor("100MB", "50", "Audio"));
      threads.add(new ParallelCompactor("100MB", "50", "Image"));
      threads.add(new ParallelCompactor("100MB", "50", "Exe"));
      threads.add(new ParallelCompactor("100MB", "100", "Text"));
      threads.add(new ParallelCompactor("100MB", "100", "Video"));
      threads.add(new ParallelCompactor("100MB", "100", "Audio"));
      threads.add(new ParallelCompactor("100MB", "100", "Image"));
      threads.add(new ParallelCompactor("100MB", "100", "Exe"));

      for (int index = 0; index < threads.size(); index++) {
         try {
            threads.get(index).start();
            threads.get(index).join();
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
         basicInfo.addStrings("Compress", "Time", 4);
         
      } catch (WriteException writeException) {
         Logger.getLogger(ParallelCompactor.class.getName()).log(Level.SEVERE, null, writeException);
      } catch (IOException ex) {
         Logger.getLogger(ZipComp.class.getName()).log(Level.SEVERE, null, ex);
      }

      for (int index = 0; index < threads.size(); index++) {
         try {
            basicInfo.addTime(threads.get(index).getWay() + ", Size:" + threads.get(index).getSize() + ", Quantity:" + threads.get(index).getQuantity() + ", Type:" + threads.get(index).getType(), threads.get(index).getTime(), index + 5);
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
