package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UDPClient {

   /*
    * Implemente uma aplicação cliente para enviar uma dada quantidade de
    * pacotes ao servidor de eco. A aplicação deve verificar e informar se todo
    * os pacotes enviados ao servidor estão sendo recebidos de volta, e se a
    * ordem de entrega é a mesma em que eles foram enviados.
    */
   private int port;
   private final DatagramSocket socket;
   private boolean connected;

   private final JTextField jTextFieldHost;
   private final JButton jButtonConnect;
   private final JTextField jTextFieldSend;
   private final JTextArea console;
   private final JButton jButtonSend;

   private void setConnected(boolean connected) {
      this.connected = connected;
      if (connected) {
         this.jButtonConnect.setText("Disconnect");
         this.jTextFieldHost.setEnabled(false);
         this.jTextFieldSend.setEnabled(true);
         this.console.setEnabled(true);
         this.jButtonSend.setEnabled(true);
      } else {
         this.jButtonConnect.setText("Connect");
         this.jTextFieldHost.setEnabled(true);
         this.jTextFieldSend.setEnabled(false);
         this.console.setEnabled(false);
         this.jButtonSend.setEnabled(false);
      }
   }

   public UDPClient(JTextField jTextFieldHost, JButton jButtonConnect, JTextField jTextFieldSend, JTextArea console, JButton jButtonSend) throws IOException {
      super();
      this.jTextFieldHost = jTextFieldHost;
      this.jTextFieldHost.setText("127.0.0.1");
      this.jButtonConnect = jButtonConnect;
      this.jTextFieldSend = jTextFieldSend;
      this.console = console;
      this.jButtonSend = jButtonSend;
      this.port = 9999;
      this.socket = new DatagramSocket();
      setConnected(false);
   }

   public UDPClient(JTextField jTextFieldHost, JButton jButtonConnect, JTextField jTextFieldSend, JTextArea console, JButton jButtonSend, String host) throws IOException {
      super();
      this.jTextFieldHost = jTextFieldHost;
      this.jTextFieldHost.setText(host);
      this.jButtonConnect = jButtonConnect;
      this.jTextFieldSend = jTextFieldSend;
      this.console = console;
      this.jButtonSend = jButtonSend;
      this.port = 9999;
      this.socket = new DatagramSocket();
      setConnected(false);
   }

   public UDPClient(JTextField jTextFieldHost, JButton jButtonConnect, JTextField jTextFieldSend, JTextArea console, JButton jButtonSend, int port) throws IOException {
      super();
      this.jTextFieldHost = jTextFieldHost;
      this.jTextFieldHost.setText("127.0.0.1");
      this.jButtonConnect = jButtonConnect;
      this.jTextFieldSend = jTextFieldSend;
      this.console = console;
      this.jButtonSend = jButtonSend;
      this.port = port;
      this.socket = new DatagramSocket();
      setConnected(false);
   }

   public UDPClient(JTextField jTextFieldHost, JButton jButtonConnect, JTextField jTextFieldSend, JTextArea console, JButton jButtonSend, String host, int port) throws IOException {
      super();
      this.jTextFieldHost = jTextFieldHost;
      this.jTextFieldHost.setText(host);
      this.jButtonConnect = jButtonConnect;
      this.jTextFieldSend = jTextFieldSend;
      this.console = console;
      this.jButtonSend = jButtonSend;
      this.port = port;
      this.socket = new DatagramSocket();
      setConnected(false);
   }

   public void connect(String host, int port) throws SocketException {
      if (!connected) {
         this.jTextFieldHost.setText(host);
         this.port = port;
         socket.connect(new InetSocketAddress(host, port));
         setConnected(true);
      }
   }

   public void connect(String host) throws SocketException {
      if (!connected) {
         this.jTextFieldHost.setText(host);
         socket.connect(new InetSocketAddress(host, port));
         setConnected(true);
      }
   }

   public void connect(int port) throws SocketException {
      if (!connected) {
         this.port = port;
         socket.connect(new InetSocketAddress(this.jTextFieldHost.getText(), port));
         setConnected(true);
      }
   }

   public void connect() throws SocketException {
      if (!connected) {
         socket.connect(new InetSocketAddress(this.jTextFieldHost.getText(), port));
         setConnected(true);
      }
   }

   public void disconnect() throws SocketException {
      socket.close();
      setConnected(false);
   }

   public int getPort() {
      return port;
   }

   public String getHost() {
      return this.jTextFieldHost.getText();
   }

   public boolean isConnected() {
      return connected;
   }

   public void send(String text) {
      try {
         byte[] buffer = new byte[1];
         String send = text;
         buffer = send.getBytes();
         console.append("sent:" + '\n');
         console.append('\t' + send + '\n');
         System.out.println(send);
         socket.send(new DatagramPacket(buffer, buffer.length));
      } catch (IOException ex) {
         Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}
