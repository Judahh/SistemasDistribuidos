/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compactor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class Compactor extends Thread {

   private final File file;
   private File newFile;
   private final String filePath;
   private final String fileName;
   private final String way;
   private long time;

   public long getTime() {
      return time;
   }

   public String getFileName() {
      return fileName;
   }

   public String getFilePath() {
      return filePath;
   }

   public Compactor(String filePath, String fileName, String way) {
      this.filePath = filePath;
      this.fileName = fileName;
      this.way = way;
      this.file = new File(this.filePath + "\\" + this.fileName);
   }

   @Override
   public void run() {
      time = System.currentTimeMillis();
      FileInputStream fileInputStream;
      ZipOutputStream zipOutputStream;
      byte[] buffer = new byte[1024];

      if (this.file.exists()) {
         try {
            fileInputStream = new FileInputStream(this.file);
            this.newFile = new File(this.filePath + "\\" + "Zipped" + "\\" + this.way + "\\" + this.fileName + ".zip");
            if (this.newFile.createNewFile()) {
               this.newFile.delete();
               this.newFile.createNewFile();
            }
//            System.out.print(this.newFile.getAbsolutePath() + "...");
//            System.out.print(this.newFile.getName() + "... on " + this.newFile.getAbsolutePath());
            zipOutputStream = new ZipOutputStream(new FileOutputStream(this.newFile));
            zipOutputStream.putNextEntry(new ZipEntry(this.file.getName()));
            while (fileInputStream.read(buffer) > 0) {
               zipOutputStream.write(buffer);
               zipOutputStream.flush();
            }
            fileInputStream.close();
            zipOutputStream.close();
//            System.out.println("OK!");
         } catch (FileNotFoundException e) {
            System.err.println("Error!");
            System.err.println("Erro ao compactar ou ler arquivo " + this.fileName + ".");
            e.printStackTrace();
         } catch (IOException e) {
            System.err.println("Error!");
            System.err.println("Erro ao escrever ou ler dados do arquivo " + this.fileName + ".");
            e.printStackTrace();
         }
      } else {
         System.err.println("Arquivo " + this.filePath + this.fileName + " não existe.");
      }
      time = System.currentTimeMillis() - time;
   }
}
