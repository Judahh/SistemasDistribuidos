/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class InvertOutputStream extends OutputStream {

   OutputStream outputStream;

   public InvertOutputStream(OutputStream outputStream) {
      super();
      this.outputStream = outputStream;
   }

   @Override
   public void write(byte[] data) throws IOException {
      write(data, 0, data.length);
   }

   @Override
   public void write(byte[] data, int offset, int length) throws IOException {
      for (int index = offset; index < length + offset; index++) {
         write(data[data.length - 1 - index]);
      }
   }

   @Override
   public void write(int b) throws IOException {
      outputStream.write(b);
   }

   @Override
   public void flush() throws IOException {
      outputStream.flush();
   }

   @Override
   public void close() throws IOException {
      outputStream.close();
   }
}
