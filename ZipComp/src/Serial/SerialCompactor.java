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

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class SerialCompactor extends GenericCompactor {

   public SerialCompactor(String size, String quantity, String type) {
      super(size, quantity, type);
      this.way = "Serial";
      initialization();
   }

   public SerialCompactor(String size, String quantity, String type, int number) {
      super(size, quantity, type, number);
      this.way = "Serial";
      initialization();
   }
   
   @Override
   public long totalTime(int wave) {
      long tempTime;
      File directory = new File("Files/" + size + "/" + quantity + "/" + type + "/");
      File[] files = directory.listFiles();
      List<Compactor> threads = new ArrayList();
      for (File file : files) {
         try {
            if (file.isFile()) {
               threads.add(new Compactor(directory.getPath(), file.getName(), this.way));
               threads.get(threads.size() - 1).start();
               threads.get(threads.size() - 1).join();
            }
         }catch (InterruptedException ex) {
            Logger.getLogger(SerialCompactor.class.getName()).log(Level.SEVERE, null, ex);
         }
      }

      this.numberOfFiles = threads.size();
      
      addNames(threads);
      tempTime = addTotal(threads, wave);
      return tempTime;
   }
}
