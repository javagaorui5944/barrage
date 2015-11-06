package com.sample.senddanmu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gaorui.Dao.DanmuDao;
import com.sample.Bean.Danmu;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class get
 */
@WebServlet("/getDanmu")
public class getDanmu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		DanmuDao dd = new DanmuDao();
		ArrayList<Danmu> danmu = dd.getDanmu();
		jsonObject.put("danmu", danmu);
		pw.println(jsonObject);

		pw.flush();

		pw.close();
	}

}
