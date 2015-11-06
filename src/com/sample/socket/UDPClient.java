package com.sample.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

	/*
	 * public UDPClient(String textID2,String Username2){ this.textID2 =
	 * textID2; this.Username2 = Username2; }
	 */
	public void send(String textID2, String Username2) {
		InetAddress ip;
		
		try {
			ip = InetAddress.getByName("localhost");
			int port = 39169;
			String xx = textID2+","+Username2;
			byte[] data = xx.getBytes();
			// byte [] data = {111,2,33,94};
			DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
			DatagramSocket socket = new DatagramSocket();
			socket.send(packet);

			/*byte[] data2 = new byte[1024];
			DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
			socket.receive(packet2);
			String reply = new String(data2, 0, 9);
			System.out.println("xxx" + reply);*/
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
	}

}
