<<<<<<< HEAD
package com.ych.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ych.util.hibernate.HibernateProxyTypeAdapter;

public class ResultUtils {

	public static void toJson(HttpServletResponse response, Object data)   
	         throws IOException {  
	    	GsonBuilder b = new GsonBuilder();
	    	b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	         Gson gson = b.create();  
	         String result = gson.toJson(data);  
	         response.setContentType("text/json; charset=utf-8");  
     //        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存  
	         PrintWriter out = response.getWriter();  
	         out.print(result);  
	         out.flush();  
	         out.close();  
		     }  
	
	public static void toJson(HttpServletResponse response, Object data,JsonConfig jsonConfig)   
	         throws IOException {  
		JSONArray jsonArray = JSONArray.fromObject(data,jsonConfig);
	         response.setContentType("text/json; charset=utf-8");  
	         PrintWriter out = response.getWriter();  
	         out.print(jsonArray.toString());  
	         out.flush();  
	         out.close();  
		     }  
	
}
=======
<<<<<<< HEAD
package com.ych.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ResultUtils {

	public static void toJson(HttpServletResponse response, Object data)   
	         throws IOException {  
	         Gson gson = new Gson();  
	         String result = gson.toJson(data);  
	         response.setContentType("text/json; charset=utf-8");  
     //        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存  
	         PrintWriter out = response.getWriter();  
	         out.print(result);  
	         out.flush();  
	         out.close();  
		     }  
}
=======
package com.ych.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ResultUtils {

	public static void toJson(HttpServletResponse response, Object data)   
	         throws IOException {  
	         Gson gson = new Gson();  
	         String result = gson.toJson(data);  
	         response.setContentType("text/json; charset=utf-8");  
     //        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存  
	         PrintWriter out = response.getWriter();  
	         out.print(result);  
	         out.flush();  
	         out.close();  
		     }  
}
>>>>>>> eb63eded9899513af9538a187ddf701ead10b135
>>>>>>> 2d47266740bc805e9dd402192a7321ac5ee26525
