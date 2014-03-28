package sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPSniffer {

	/*
	 * Utilize a aplicação cliente implementada como partedo Exercício 5.2 para
	 * testar o sniffer cujo código foi mostrado nos três slides anteriores. 
	 * Execute o sniffer em múltiplas maquinas de uma mesma rede local, passando
	 * o endereço “all-systems.mcast.net” e uma porta de sua escolha como
	 * parâmetros na linha de comando.
	 */

	private InetAddress group;
	private static boolean testSequence = true;
	public static int port = -1;
	static
	{
		if(port == -1)
		{
			port = UDPClient.port;
		}
	}
	
	
	private MulticastSocket ms;

	public UDPSniffer(InetAddress group) throws IOException {
		super();
		this.group = group;
		
		start();
	}

	public void start() throws IOException {
		ms = new MulticastSocket(port);
		ms.joinGroup(group);
		
		byte[] buffer = new byte[512];
		int previous = -1;
		while (true) {
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			ms.receive(dp);
			String ans = new String(dp.getData());
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
			System.out.println("Sniffer=" + ans);
		}
	}
	
	public static void main(String[] args) {
		try {
			UDPSniffer sniffer = new UDPSniffer(InetAddress.getByName("all-systems.mcast.net"));
			System.out.println(sniffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
