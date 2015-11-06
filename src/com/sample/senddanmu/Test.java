package com.sample.senddanmu;

import com.sample.socket.UDPUtils;

public class Test implements Runnable {
	public static void main(String[] args) {
		
		Test t = new Test();
		for(int i =0 ;i<=10;i++){
			Thread th= new Thread(t);
			th.start();
		}
		
	}

	@Override

	public void run() {
		while(true){
		UDPUtils utils = new UDPUtils();
		utils.sendPost("http://127.0.0.1:8080/WebSocketTest/senddanmu", "textID=danmuceshi2015/09/30&Username=xxx");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}
