package sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class UDPClient {

	/*
	 * Implemente uma aplicação cliente para enviar uma dada quantidade de
	 * pacotes ao servidor de eco. A aplicação deve verificar e informar se todo
	 * os pacotes enviados ao servidor estão sendo recebidos de volta, e se a
	 * ordem de entrega é a mesma em que eles foram enviados.
	 */

	public static int port = 9999;
	private static boolean testSequence = true;
	private static String sausage = "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH";
	private String host;
	private int packagesQtt;

	private List<String> answers;

	private DatagramSocket socket;

	public UDPClient(String host, int packagesQtt) throws IOException {
		super();
		this.host = host;
		this.packagesQtt = packagesQtt;

		socket = new DatagramSocket();
		answers = new ArrayList<String>();
		
		start();
	}

	public void start() throws IOException {
		socket.connect(new InetSocketAddress(host, port));

		byte[] buffer = new byte[1];

		for (int i = 0; i < packagesQtt; i++) {
			String send = String.valueOf(i)+"@"+sausage; //Calendar.getInstance().getTimeInMillis()+".mensagem:"+i+sausage;
			buffer = send.getBytes();
			System.out.println(send);
			socket.send(new DatagramPacket(buffer, buffer.length));
		}

		buffer = new byte[512];
		DatagramPacket response = new DatagramPacket(buffer, buffer.length);
		byte[] responseBuffer = null;
		int timer = 0;
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Recebendo!");
		int previous = -1;
		while (true) {
			socket.receive(response);
			responseBuffer = response.getData();
			String ans = new String(responseBuffer);
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
			int l = response.getLength();
			ans = ans.substring(0, l);
			System.out.println(ans);
			answers.add(ans);
			timer++;
			
			if(timer == packagesQtt)
			{
				break;
			}
		}

		for(String msg : answers)
		{
			System.out.println(answers.size() + " " + msg);
		}
		System.out.println("Quantidade Recebida: " + answers.size());
	}

	public static void main(String[] args) {
		try {
			UDPClient client = new UDPClient("all-systems.mcast.net", 800);
//			UDPClient client = new UDPClient("127.0.0.1", 800);
			System.out.println(client);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
