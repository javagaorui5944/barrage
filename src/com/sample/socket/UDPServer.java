package com.sample.socket;

import java.io.IOException;

import com.sample.websocket.HelloWorldEndpoint;

public class UDPServer {

	public static void main(String[] args) throws IOException {

		
		UDPUtils t1 = new UDPUtils();

		t1.init();
	//	new Thread(new HelloWorldEndpoint()).start();
	}
}
