package com.sample.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UDPUtils {
	    private static InetSocketAddress socketAddress = null; // 服务监听个地址  
	    private static DatagramSocket datagramSocket = null; // 连接对象  
	 /** 
     * 接收数据包，该方法会造成线程阻塞 
     * @return 
     * @throws Exception  
     * @throws IOException 
     */  
    public  DatagramPacket receive(DatagramPacket packet) throws Exception {  
        try {  
            datagramSocket.receive(packet);  
            return packet;  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
    /** 
     * 将响应包发送给请求端 
     * @param bt 
     * @throws IOException 
     */  
    public  void response(DatagramPacket packet) {  
        try {  
            datagramSocket.send(packet);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    /** 
     * 初始化连接 
     * @throws SocketException 
     */  
    public  void init(){  
        try {  
            socketAddress = new InetSocketAddress(39169);  
            datagramSocket = new DatagramSocket(socketAddress);  
           //setSotimeout(10000)是表示如果对方连接状态10秒没有收到数据的话强制断开客户端。
           // datagramSocket.setSoTimeout(5 * 1000);  
            System.out.println("服务端已经启动"+222222);  
        } catch (Exception e) {  
            datagramSocket = null;  
            System.err.println("服务端启动失败");  
            e.printStackTrace();  
        }  
    }  
    /**
	 * 已int数组类型解析收到的数据，卡类型，读卡器ip地址，机号，包号
	 * @param bytes
	 * @return 卡类型，读卡器ip地址，机号，包号
	 */
    
	public static int[] getInt(byte[] bytes) {
		int[] result = new int[9];
		for (int i = 0; i < 9; i++) {
			result[i] = 0xff & bytes[i];
		}
		return result;
	}
	
	/**
	 * 获得卡号
	 * @param bytes 接收到的字节数组
	 * @return 卡号String类型
	 */
	public static String getCardId(byte[] bytes) {
		byte[] cardid = new byte[4];
		cardid[0] = bytes[12];
		cardid[1] = bytes[11];
		cardid[2] = bytes[10];
		cardid[3] = bytes[9];
		
		String cardId = new BigInteger(1, cardid).toString(10);
		return cardId;
	}
	
	
	 /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String result1 = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            InputStream instream = conn.getInputStream();
            if(instream!=null){
            	 in = new BufferedReader( new InputStreamReader(instream));	
            	  String line;
                  while ((line = in.readLine()) != null) {
                      result += line;
                  }
                   result1 = new String(result.getBytes("iso-8859-1"), "utf-8");
            }
           
          
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(new JFrame(), e.toString(),"错误",1);
        //	System.exit(0);
//        	UDPService x=new UDPService("发送 POST 请求出现异常！"+e);	
            System.out.println("发送 POST 请求出现异常！"+e);
            System.out.println("e.toString()"+e.toString()); 
            e.printStackTrace();
            
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
     
        return result1;
    }    

}
