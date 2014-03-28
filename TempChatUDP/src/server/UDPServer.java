package server;

import client.UDPClient;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class UDPServer extends Thread {

   /*
    * Implemente um “servidor de eco” em Java para pacotes UDP. O servidor deve
    * aguardar pacotes numa porta específica. A cada pacote recebido, o
    * servidor deve mostrar seu conteúdo na tela e em seguida re-enviar o
    * pacote de volta aoseu endereço de origem.
    */
   public final int port;
   private final JTextArea console;
   private DatagramSocket socket;
   private final UDPClient client;

   public UDPServer(JTextArea console, UDPClient client) throws IOException {
      super();
      this.client = client;
      this.console = console;
      this.port = 9999;
      this.socket = new DatagramSocket(port);
   }

   public UDPServer(int port, JTextArea console, UDPClient client) throws IOException {
      super();
      this.client = client;
      this.console = console;
      this.port = port;
      this.socket = new DatagramSocket(port);
   }

   @Override
   public void run() {
      byte[] buffer = new byte[512];
      byte[] responseBuffer = null;
      DatagramPacket response = new DatagramPacket(buffer, buffer.length);

      System.out.println("Porta:" + port);
      while (true) {
         try {
            socket.receive(response);
            responseBuffer = response.getData();
            if ((responseBuffer.length > 0)) {
               int l = response.getLength();
               String ans = new String(responseBuffer);
               ans = ans.substring(0, l);
               console.append("received:" + '\n');
               console.append('\t' + ans + '\n');
               socket.connect(response.getAddress(), response.getPort());
               this.client.connect(response.getAddress().getHostAddress(), this.client.getPort());
            }
         } catch (IOException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }
}
