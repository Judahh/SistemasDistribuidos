/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serial;

import Compactor.Compactor;
import Compactor.GenericCompactor;
import info.BasicInfo;
import java.io.File;
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
public class SerialCompactor extends GenericCompactor {

   public SerialCompactor(String size, String quantity, String type) {
      super(size, quantity, type);
      this.way = "Serial";
   }
   
   public SerialCompactor(String size, String quantity, String type, int number) {
      super(size, quantity, type,number);
      this.way = "Serial";
   }

   @Override
   public void run() {
      File directory = new File("Files/" + size + "/" + quantity + "/" + type + "/");
      File[] files = directory.listFiles();
      List<Compactor> threads = new ArrayList();

      for (int index = 0; index < files.length; index++) {
         try {
            if (files[index].isFile()) {
               threads.add(new Compactor(directory.getPath(), files[index].getName(), this.way));
               threads.get(threads.size() - 1).start();
               threads.get(threads.size() - 1).join();
            }

         } catch (InterruptedException ex) {
            Logger.getLogger(SerialCompactor.class.getName()).log(Level.SEVERE, null, ex);
         }
      }

      BasicInfo basicInfo = new BasicInfo();
      basicInfo.setOutputFile("Files/" + size + "/" + quantity + "/" + type + "/" + "Zipped/" + this.way + "/" + "BasicInfo.xls");

      try {
         basicInfo.createSingleFileReport();
      } catch (IOException | WriteException exception) {
         Logger.getLogger(SerialCompactor.class.getName()).log(Level.SEVERE, null, exception);
      }

      for (int index = 0; index < threads.size(); index++) {
         long newTime = threads.get(index).getTime();
         this.time += newTime;
         try {
            basicInfo.addTime(threads.get(index).getFileName(), newTime, index + 1);
         } catch (WriteException writeException) {
            Logger.getLogger(SerialCompactor.class.getName()).log(Level.SEVERE, null, writeException);
         }
      }

      try {
         basicInfo.addTime("Total", this.time, threads.size() + 1);
      } catch (WriteException writeException) {
         Logger.getLogger(SerialCompactor.class.getName()).log(Level.SEVERE, null, writeException);
      }

      try {
         basicInfo.close();
      } catch (IOException | WriteException exception) {
         Logger.getLogger(SerialCompactor.class.getName()).log(Level.SEVERE, null, exception);
      }

   }
}
