/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class CipherWriter extends Writer {

   private final OutputStreamWriter outputStreamWriter;

   public CipherWriter(OutputStreamWriter outputStreamWriter) {
      this.outputStreamWriter = outputStreamWriter;
   }

   @Override
   public void close() throws IOException {
      outputStreamWriter.close();
   }

   @Override
   public void flush() throws IOException {
      outputStreamWriter.flush();
   }

   @Override
   public void write(char[] data) throws IOException {
      write(data, 0, data.length);
   }

   @Override
   public void write(String data) throws IOException {
      write(data, 0, data.length());
   }

   @Override
   public void write(String data, int offset, int length) throws IOException {
      write(data.toCharArray(), offset, length);
   }

   @Override
   public void write(char[] buffer, int offset, int length) throws IOException {
      for (int index = offset; index < length + offset; index++) {
         outputStreamWriter.write(Cipher.getCipheredCharacter(buffer[index]));
      }
   }
}
