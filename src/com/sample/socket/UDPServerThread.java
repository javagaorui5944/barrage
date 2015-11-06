package com.sample.socket;



import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerThread implements Runnable {
	static String info;
	UDPUtils t1 = new UDPUtils();


	
	@Override
	public void run() {
	
		//DatagramPacket packet = new DatagramPacket(data, data.length);

		try {
			while (true) {
			
			byte[] buffer = new byte[1024 * 64]; // 缓冲区
			DatagramPacket packet = new DatagramPacket(buffer,
					buffer.length);
			t1.receive(packet);

		//	byte[] sendbuf = new byte[280];

			byte[] bt = new byte[packet.getLength()];
			bt = packet.getData();
			 info = new String(bt, 0, packet.getLength(),"UTF-8");
			System.out.println(info);
			
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
		
	}
	public String get(){
		return info;
	}
	
}
