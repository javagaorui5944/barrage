package com.sample.websocket;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import com.sample.socket.UDPUtils;

import net.sf.json.JSONObject;

@ServerEndpoint("/hello")
public class HelloWorldEndpoint {
	UDPUtils t1 = new UDPUtils();
	static Session session1 = null;
	static HashMap<String,Session> Sessions = new HashMap<String,Session>();

	@OnMessage
	public void hello(String message, Session session,Queue<String> queue) {
		
		
	
		if (session != null) {
			session1 = session;
			//return;
			for (Session openSession : session.getOpenSessions()) {
				System.out.println("openSession.getId():"+openSession.getId());
			/*	System.out
						.println(session1.getOpenSessions()
								+ "session.getOpenSessions()"
								+ openSession.getId());
				openSession.getBasicRemote()
						.sendText(jsonObject.toString());*/
			}
			
			
		}

		else if (session == null) {
			session = session1;
			System.out.println("info" + message);
			//   for (String string : queue)     {  
					String[] res = message.split(",");
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("textID", res[0]);
					jsonObject.put("Username", res[1]);
					// String message1
					// ="{\"textID\":\""+res[0]+"\",\"Username\":\""+res[1]+"\"}";
					// JSONObject jsonObject11 = JSONObject.fromObject(message1);
					Session openSession =null;
					try {
						
						// 把消息发送给所有连接的会话
						Set<String> keys=Sessions.keySet();
						 Iterator<String> temp =keys.iterator();
						 while(temp.hasNext()){
							String i= temp.next();
						 
					//	for( int i= 0 ;i<Sessions.size();i++){
							openSession = Sessions.get(i);
						
							System.out.println("size"+Sessions.size());
							System.out.println("openSession.getId():"+openSession.getId());
							/*System.out
									.println(session1.getOpenSessions()
											+ "session.getOpenSessions()"
											+ openSession.getId());*/
							openSession.getBasicRemote()
									.sendText(jsonObject.toString());
						}
					/*	for (Session openSession : session.getOpenSessions()) {
							System.out.println("openSession.getId():"+openSession.getId());
							System.out
									.println(session1.getOpenSessions()
											+ "session.getOpenSessions()"
											+ openSession.getId());
							openSession.getBasicRemote()
									.sendText(jsonObject.toString());
						}*/
						
					} catch (Exception e) {
					
						e.printStackTrace();
					}
		      //  }  
		

		}

	}

	@OnOpen
	public void myOnOpen(Session session ) {
		
		Sessions.put(session.getId(), session);
		System.out.println("WebSocket opened: " + session.getId());
		
		//hello("x", session, null);
		for (Session openSession : session.getOpenSessions()) {
			
			System.out.println("myOnOpen——openSession.getId():"+openSession.getId());
			
		/*	System.out
					.println(session1.getOpenSessions()
							+ "session.getOpenSessions()"
							+ openSession.getId());
			openSession.getBasicRemote()
					.sendText(jsonObject.toString());*/
		}
	}

	@OnClose
	public void myOnClose(CloseReason reason, Session session ) {
		Sessions.remove(session.getId());
		System.out.println("Closing a WebSocket due to "
				+ session.getId());
		
		
	
	}

}