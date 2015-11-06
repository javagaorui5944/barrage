package com.sample.senddanmu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gaorui.Dao.DanmuDao;
import com.sample.websocket.HelloWorldEndpoint;

import net.sf.json.JSONObject;

@WebServlet("/senddanmu")
public class senddanmu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
	static String text;
	DanmuDao dd = new DanmuDao();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	//	this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String ip = request.getRemoteAddr();// 记录到访ip
	
		//封禁ip
		if(dd.Searchipstatus(ip)){
			System.out.println(ip+"==="+dd.Searchipstatus(ip));
			jsonObject.put("result","no");
			pw.println(jsonObject);

			pw.flush();

			pw.close();
			return;
		}
		
		
		System.out.println("手机浏览器访问ip:" + ip);
		String textID = request.getParameter("textID");
		String Username = request.getParameter("Username");
		// 记录问题,分类问题,传给服务端后再存入数据库。
		System.out.println("textID:"+textID+"Username:"+Username);
		//String textID2 = new String(textID.getBytes("iso-8859-1"), "utf-8");
		//String Username2 = new String(Username.getBytes("iso-8859-1"), "utf-8");

		HelloWorldEndpoint hello = new HelloWorldEndpoint();
		try {
			
			queue.put(textID + "," + Username);
			System.out.println("before queue size" + queue.size());

			Thread.sleep(200);// 休息0.2秒,确保Tomcat能承受20W弹幕的压力
			text = queue.remove();
			String str = textID.replaceAll("\\s+", "");

			// System.out.println("去掉空格后的text:"+str+"MD5:"+MD5.string2MD5(str));

			System.out.println("queue size" + queue.size());

			hello.hello(text, null, queue);
			if (str.indexOf("?") != -1 | str.indexOf("？") != -1) {
				if (dd.checkSame(MD5.string2MD5(str))) {
					dd.increaseCount(MD5.string2MD5(str));
				}

				else {
					dd.saveDanmu(MD5.string2MD5(str), Username, ip, str);
				}
			}
			else{
				dd.saveDanmus(Username, ip, str);
			}
			// System.out.println(dd.saveDanmu(MD5.string2MD5(str),Username2 ,ip
			// , str));
			jsonObject.put("result","ok");
			pw.println(jsonObject);

			pw.flush();

			pw.close();

		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}

}
