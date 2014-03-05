/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class CipherReader extends Reader {

   private final InputStreamReader inputStreamReader;

   public CipherReader(InputStreamReader inputStreamReader) {
      this.inputStreamReader = inputStreamReader;
   }

   @Override
   public void close() throws IOException {
      inputStreamReader.close();
   }

   @Override
   public int read(char[] cbuf) throws IOException {
      return read(cbuf, 0, cbuf.length);
   }

   @Override
   public int read(char[] buffer, int offset, int length) throws IOException {
      for (int index = offset; index < length + offset; index++) {
         buffer[index] = Cipher.getDecipheredCharacter(buffer[index]);
      }
      return inputStreamReader.read(buffer);//buffer.length - length + offset;
   }

}
