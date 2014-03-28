package sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

	/*
	 * Implemente um “servidor de eco” em Java para pacotes UDP. O servidor deve
	 * aguardar pacotes numa porta específica. A cada pacote recebido, o
	 * servidor deve mostrar seu conteúdo na tela e em seguida re-enviar o
	 * pacote de volta aoseu endereço de origem.
	 */
	private static boolean testSequence = true;
	public static int port = -1;
	static
	{
		if(port == -1)
		{
			port = UDPClient.port;
		}
	}
	
	private DatagramSocket socket;

	public UDPServer() throws IOException {
		super();
		
		socket = new DatagramSocket(port);

		start();
	}
	
	public void start() throws IOException {
		byte[] buffer = new byte[512];
		byte[] responseBuffer = null;
		DatagramPacket response = new DatagramPacket(buffer, buffer.length);
		
		int previous = -1;
		while(true)
		{
			socket.receive(response);
			responseBuffer = response.getData();
			if ((responseBuffer.length > 0)) {
				int l = response.getLength();
				String ans = new String(responseBuffer);
				ans = ans.substring(0, l);
				System.out.println(ans);
				socket.connect(response.getAddress(), response.getPort());
				socket.send(response);
				System.out.printf("Enviado! %s %d\n", response.getAddress(), response.getPort());
				if(testSequence)
				{
					int value = Integer.valueOf(ans.split("@")[0]);
					if(value != previous + 1)
					{
						System.out.printf("ERRO! Previous %d e Atual: %d\n", previous, value);
						break;
					}
					previous = value;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			UDPServer server = new UDPServer();
			System.out.println(server);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
