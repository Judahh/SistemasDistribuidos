/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class InvertInputStream extends InputStream {

   InputStream inputStream;

   public InvertInputStream(InputStream inputStream) {
      super();
      this.inputStream = inputStream;
   }

   @Override
   public int read(byte[] data) throws IOException {
      return read(data, 0, data.length);
   }

   @Override
   public int read(byte[] data, int offset, int length) throws IOException {
      for (int index = offset; index < length + offset; index++) {
         byte temp = (byte) read();
         switch (temp) {
            case -1:
               return -1;

            case '\n':
               return data.length - length + offset;
               
            default:
               data[data.length - 1 - index] = temp;
               break;
         }
      }
      return data.length - length + offset;
   }

   @Override
   public void close() throws IOException {
      inputStream.close();
   }

   @Override
   public int read() throws IOException {
      return inputStream.read();
   }
}
