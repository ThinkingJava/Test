package com.ych.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import net.sf.json.JSONObject;
import Decoder.BASE64Encoder;

public class Test {

	/** 
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param args
	 * @param @throws IOException 设定文件 
	 * @return void 返回类型 
	 * @throws 
	 */ 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
         String url="http://localhost:8080/Test/addStudentAPI.html";
         Map<String,Object> map=new HashMap<String, Object>();
//         InputStream is=new FileInputStream(new File("F://image/avatar-08.png"));
         InputStream is=new FileInputStream(new File("D://image/1169774.gif"));
         ByteArrayOutputStream baos = new ByteArrayOutputStream();  
         int b = 0;  
         while((b = is.read())!=-1){  
             baos.write(b);  
         }  
         baos.close();
      // 对字节数组Base64编码
         BASE64Encoder encoder = new BASE64Encoder();
         String imgdata = encoder.encode(baos.toByteArray());// 返回Base64编码过的字节数组字符串
//         Student student=new Student();
//         student.setName("good");
//         student.setAge(22);
//         student.setSex(1);
//         student.setDepartment("计算机");
//         student.setStudentImage(imgdata);
//         map.put("student", student);
     //    JsonObject jsonobject=new JsonObject();
   //      jsonobject.addProperty("name", "");
//         JSONObject json=new JSONObject();
//         json.put("studentId", "3");
//         json.put("name", "马延浩");
//         json.put("sex", 0);
//         json.put("age", 23);
//         json.put("department", "软件系");
//         json.put("studentImage", imgdata);
         map.put("student.studentId", "100000001");
         map.put("student.name", "hello");
         map.put("student.sex", "0");
         map.put("student.age", "30");
         map.put("student.department", "计算机");
         map.put("student.studentImage", imgdata);
//        System.out.println(imgdata);
  //       String resp=Dopost(url,map);  
   //     String resp = Dopost(url,map);
     //    String resp = testpost(url,json);
         String resp =  http(url,map);
         System.out.println("----ddd-----"+resp);    
	}
	
	public static String testpost(String urlstring,JSONObject object) throws IOException{
		

		URL url = new URL(urlstring);  
        HttpURLConnection httpURLConnection = (HttpURLConnection) url  
                .openConnection();  
        httpURLConnection.setDoOutput(true);  
        httpURLConnection.setDoInput(true);  
   //     httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Type","text/html");
        httpURLConnection.setRequestMethod("POST");    
        httpURLConnection.setConnectTimeout(5000);  
        httpURLConnection.setReadTimeout(5000);  
        httpURLConnection.connect();  
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(  
                httpURLConnection.getOutputStream(), "UTF-8")); 

	//	System.out.println("--==>>"+object.toString());
        out.write(object.toString());      
        out.flush();  

        //允许获得输出流  通过输出流向服务器输出数据  
        StringBuilder resp=new StringBuilder();  
        try{  
            Scanner in=new Scanner(httpURLConnection.getInputStream());  
            while(in.hasNext()){  
                resp.append(in.nextLine());  
                resp.append("\n");  
                }  
            }catch(IOException e){  
            	
        }  

        return resp.toString();  
	}

	private static String Dopost(String urlstring, Map<String,Object> namevalueParis) throws IOException{  
        // TODO Auto-generated method stub  
        String data="";
		URL url = new URL(urlstring);  
        HttpURLConnection httpURLConnection = (HttpURLConnection) url  
                .openConnection();  
        httpURLConnection.setDoOutput(true);  
        httpURLConnection.setDoInput(true);  
   //     httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Type","text/html");
        httpURLConnection.setRequestMethod("POST");    
        httpURLConnection.setConnectTimeout(5000);  
        httpURLConnection.setReadTimeout(5000);  
        httpURLConnection.connect();  
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(  
                httpURLConnection.getOutputStream(), "UTF-8")); 
        StringBuffer sb = new StringBuffer();
		if(namevalueParis!=null){
		for (Entry<String, Object> e : namevalueParis.entrySet()) {
		sb.append(e.getKey());
		sb.append("=");
		sb.append(e.getValue());
		sb.append("&");
		}
		data=sb.substring(0, sb.length() - 1);
		}
//		System.out.println("--==>>"+data);
        out.write(data);      
        out.flush();  

        //允许获得输出流  通过输出流向服务器输出数据  
  
    //    System.out.println("------www----");
        StringBuilder resp=new StringBuilder();  
        try{  
            Scanner in=new Scanner(httpURLConnection.getInputStream());  
            while(in.hasNext()){  
                resp.append(in.nextLine());  
                resp.append("\n");  
                }  
            }catch(IOException e){  
//            if(!(conn instanceof HttpURLConnection)) throw e;  
//            HttpURLConnection err=(HttpURLConnection) conn.getErrorStream();  
//            if(err==null)  
//                throw e;  
//            Scanner in=new Scanner(err);  
//            resp.append(in.nextLine());  
//            resp.append("\n");  
        }  
    //    System.out.println("------www----");
        return resp.toString();  
          
                  
          
//        return null;  
    }  
	
	
	
	private static String http(String url, Map<String, Object> params) {
		String data="";
		URL u = null;
		HttpURLConnection con = null;
		//构建请求参数
		StringBuffer sb = new StringBuffer();
		if(params!=null){
		for (Entry<String, Object> e : params.entrySet()) {
		sb.append(e.getKey());
		sb.append("=");
		sb.append(e.getValue());
		sb.append("&");
		}
		data=sb.substring(0, sb.length() - 1);
		}
//		System.out.println("send_url:"+url);
//		System.out.println("send_data:"+data);
		//尝试发送请求
		try {
		u = new URL(url);
		con = (HttpURLConnection) u.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setUseCaches(false);
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(data);
		osw.flush();
		osw.close();
		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		if (con != null) {
		con.disconnect();
		}
		}
		 
		//读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(con
		.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null) {
		buffer.append(temp);
		buffer.append("\n");
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		 
		return buffer.toString();
		}
	
}
