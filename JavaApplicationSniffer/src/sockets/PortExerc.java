package sockets;

import java.net.DatagramSocket;
import java.net.SocketException;

public class PortExerc {

	public static void main(String[] args) {
		//Primeiro teste para diferentes sockets
		
		DatagramSocket socket = null;
		int port = 0;
		int qtdePort = 2000;
		try {
			
			System.out.println("Teste com diferentes portas:");
			for (int i = 0; i < qtdePort; i++) {
				socket = new DatagramSocket(0);
				port = socket.getLocalPort();
				System.out.println(port);
			}
			socket.close();
			
			System.out.println("Teste com diferentes portas fechando socket:");
			for (int i = 0; i < qtdePort; i++) {
				socket = new DatagramSocket(0);
				port = socket.getLocalPort();
				System.out.println(port);
				socket.close();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
