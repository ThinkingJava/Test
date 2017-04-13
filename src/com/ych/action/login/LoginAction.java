package com.ych.action.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ych.action.BaseAction;
import com.ych.entity.Attend;
import com.ych.entity.Course;
import com.ych.entity.Login;
import com.ych.entity.Teacher;
import com.ych.util.ConstUtil;
import com.ych.util.ResultUtils;

@Scope("prototype")
@Controller("loginAction")
public class LoginAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	private Login login=new Login();
	private String validateCode;
	private String password;

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String loginOut(){
		//清空所有session
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
	}

	public String login() throws IOException{
	 Teacher tea = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
	 
	    if(tea==null){
//	Map<String, Object> map =new HashMap<String, Object>();
	if(login.getName()!=null&&login.getPassword()!=null){	
	Teacher teacher = loginDao.login(login.getName(), login.getPassword());
	String validateCodesession = (String) ServletActionContext.getRequest().getSession().getAttribute("validateCode");
   System.out.println(validateCode+":"+validateCodesession);
	if(!validateCode.equals(validateCodesession)){
		return "validateErr";
	}
	   
		if(teacher!=null){
			int count=0;
			session.put("teacher", teacher);//将管理员信息保存在Session对象中
			ServletActionContext.getRequest().getSession().setAttribute("teacher", teacher);
			int countToDay=0,countYesterDay=0;
			Iterator<Course> itAttend = teacher.getCourses().iterator();
			Map<String,Integer> tempMapToDay = new HashMap<String,Integer>();
			Map<String,Integer> tempMapYesterDay = new HashMap<String,Integer>();                 
			while(itAttend.hasNext()){
			
			Course course =	itAttend.next();
			int listAttendToDay = attendDao.getCountByWhereToDay(course.getCourseid());
			tempMapToDay.put(course.getCoursename(), listAttendToDay);
			 countToDay+=listAttendToDay;
			 
			int listAttendYesterDay = attendDao.getCountByWhereYesterDay(course.getCourseid());
			tempMapYesterDay.put(course.getCoursename(), listAttendYesterDay);
			countYesterDay+=listAttendYesterDay;
			}
			session.put("courseToDay", tempMapToDay);
			session.put("courseYesterDay", tempMapYesterDay);
			session.put("countToDay", countToDay);
			session.put("countYesterDay", countYesterDay);
			
			return SUCCESS;
		}else{
			return LOGINERROR;
		}
	}
	return LOGINERROR;
	      }else
	    	  return SUCCESS;
	}
	
	
	public String updatePassword() {
		 Map<String,Object> map=new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(login.getTeacherid())){
			
	   Login outdata= loginDao.findByLogin(login.getTeacherid());
	   login.setName(outdata.getName());
		try{
			System.out.println("login"+login.getTeacherid()+":"+login.getName()+":"+login.getPassword());
			loginDao.saveOrUpdate(login);
			map.put("status", "1");
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		}
		return SUCCESS;
	}
	
	public String loginAPI() throws IOException{
	 Map<String,Object> map=new HashMap<String,Object>();
	 Teacher teacher = null;
	 try{
		 teacher = loginDao.login(login.getName(), login.getPassword()); 
	 }catch(Exception e){
		 e.printStackTrace();
		 map.put(ConstUtil.status, ConstUtil.FALSE);
		  map.put(ConstUtil.message, "登录出错"); 
	 }
	 	 
	 if(teacher!=null){
			session.put("teacher", teacher);//将管理员信息保存在Session对象中
			ServletActionContext.getRequest().getSession().setAttribute("teacher", teacher);
			List<Map<String,Object>> courseList = new ArrayList<Map<String,Object>>();
		      Iterator<Course> it= teacher.getCourses().iterator();
		      while (it.hasNext()) {
		    	Course course=  it.next();
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("courseid", course.getCourseid());
				tempMap.put("coursename", course.getCoursename());
				tempMap.put("number", course.getNumber());
				tempMap.put("credit", course.getCredit());
				tempMap.put("semester", course.getSemester());
				tempMap.put("hours", course.getHours());
				courseList.add(tempMap);
			}
		      
		      map.put(ConstUtil.status, ConstUtil.SUCCESS);
              map.put("teacher", teacher.toJSONObject());
			  map.put("courses", courseList);
			  map.put(ConstUtil.message, "登录成功");
	 }else{
		  map.put(ConstUtil.status, ConstUtil.FALSE);
		  map.put(ConstUtil.message, "用户名或密码出错");
	 }
		
	ResultUtils.toJson(ServletActionContext.getResponse(), map);  
		return null;
	}
	
//	Map<String,String> map=new HashMap<String,String>();
//
//	public Map<String, String> getMap() {
//		return map;
//	}
//
//	public void setMap(Map<String, String> map) {
//		this.map = map;
//	}
	
	
}
