/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class SistemasDistribuidos {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) throws IOException {

      // TODO code application logic here
//      InvertOutputStream invertOutputStream = new InvertOutputStream(System.out);
//      try {
//         invertOutputStream.write("Teste".getBytes());
//         invertOutputStream.flush();
//         invertOutputStream.close();
//      } catch (IOException ex) {
//         Logger.getLogger(SistemasDistribuidos.class.getName()).log(Level.SEVERE, null, ex);
//      }
//      InvertInputStream invertInputStream = new InvertInputStream(System.in);
//      int inChar;
//      byte array[]=new byte[10];
//      
//      System.out.println("Enter a Character:");
//      try {
//         inChar = invertInputStream.read(array);
//         System.out.print("You entered ");
//         System.out.println(inChar);
//         System.out.println(new String(array));
//      } catch (IOException e) {
//         System.err.println("Error reading from user");
//      }
//      try {
//         InputStream inputStream;
//         inputStream = new java.io.BufferedInputStream(null);
//         System.out.println(inputStream.markSupported());
//         inputStream = new java.io.ByteArrayInputStream(null);
//         System.out.println(inputStream.markSupported());
//         inputStream = new java.io.DataInputStream(null);
//         System.out.println(inputStream.markSupported());
//         inputStream = new java.io.FileInputStream("");
//         System.out.println(inputStream.markSupported());
//         inputStream = new java.io.ObjectInputStream(null);
//         System.out.println(inputStream.markSupported());
//         inputStream = new java.io.PipedInputStream(null);
//         System.out.println(inputStream.markSupported());
//      } catch (Exception ex) {
//         Logger.getLogger(SistemasDistribuidos.class.getName()).log(Level.SEVERE, null, ex);
//      }
      
      Scanner scanner = new Scanner(System.in);
      int opcao;

      do {
         System.out.println("Escolha uma operação a ser realizada\n");
         System.out.println("1 - CipherWriter");
         System.out.println("2 - CipherReader");
         System.out.println();
         System.out.print("Opção: ");
         opcao = scanner.nextInt();

         switch (opcao) {

            case 1:
               try {
                  cipherWriter();
               } catch (IOException e) {
                  e.printStackTrace();
               }
               break;
            case 2:
               try {
                  cipherReader();
               } catch (IOException e) {
                  e.printStackTrace();
               }
               break;
            default:
         }
      } while (opcao > 0 && opcao < 3);

   }

   private static void cipherWriter() throws IOException {
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Digite o texto para a ser cifrado pelo CipherWriter: ");

      String text=scanner.next();

      final InputStreamReader inputStreamReader = new InputStreamReader(System.in);

      inputStreamReader.read(text.toCharArray());

      System.out.print("Texto cifrado: ");

      // Escrevendo o texto de forma cifrada
      final OutputStreamWriter osw = new OutputStreamWriter(System.out);
      final CipherWriter cipherWriter = new CipherWriter(osw);
      cipherWriter.write(text);
      cipherWriter.flush();
      System.out.println();

   }

   private static void cipherReader() throws IOException {
      Scanner scanner = new Scanner(System.in);

      System.out.print("Digite o texto a ser decifrado pelo CipherReader: ");

      String text=scanner.next();
      
      char textA[]=text.toCharArray();

      // Lendo o texto de forma cifrada
      final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
      final CipherReader cipherReader = new CipherReader(inputStreamReader);
      cipherReader.read(textA);

      System.out.print("Texto cifrado: ");

      System.out.println(textA);
   }

}
