/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class DNSNet {

   public static void main(String[] args) throws IOException {
      InetAddress[] address = Inet4Address.getAllByName("google.com");
      for (int index = 0; index < address.length; index++) {
         System.out.println("IP:"+address[index].getHostAddress()); 
      }
   }
}
