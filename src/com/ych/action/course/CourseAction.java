package com.ych.action.course;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ych.action.BaseAction;
import com.ych.entity.Course;
import com.ych.model.PageModel;
import com.ych.util.ResultUtils;

/**
 * 
 * ClassName: CourseAction 
 * @Description: TODO
 * @author 杨城欢
 * @date 2016年10月1日
 */
@Scope("prototype")
@Controller("courseAction")
public class CourseAction extends BaseAction{

	private static final long serialVersionUID = 1L;
    private Course course;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
    
	public String getAllCourseAPI() throws Exception{
		Map<String,Object> map=new HashMap<String, Object>();
		PageModel<Course> list= null;
		try{
		 list=courseDao.findAll();
		map.put("status", "SUCCESS");
		map.put("message", "数据库查询失败");
		}catch(Exception e){
		map.put("status", "FALSE");
		}

		ResultUtils.toJson(ServletActionContext.getResponse(), list);
		return null;
	}
	
	public String getOneCourseAPI() throws IOException{
		
		Map<String,Object> map=new HashMap<String, Object>();
		Course list=null;
	if(course.getCourseid()!=null){
	   try{	   
	    list=courseDao.findByCourse(course.getCourseid());
	    map.put("status", "SUCCESS");	    
	   }catch(Exception e){
	    map.put("status", "FALSE");   
	    map.put("message", "数据库查询失败");
	   }
	  }else{
		map.put("status", "FALSE");
		map.put("messge", "参数错误");	
	  }
	    ResultUtils.toJson(ServletActionContext.getResponse(), list);	
		return null;
	}
	
	public String addCourseAPI() throws IOException{
		Map<String,String> map=new HashMap<String, String>();
		
//		course.setCourseId(courseId);
//		course.setHour(hour);
//		course.setName(name);
//		course.setNumber(number);
//		course.setTeacherId(teacherId);
		if(course.getCourseid()!=null&&course.getHours()!=null&&course.getNumber()!=null&&course.getTeacher()!=null&&course.getNumber()!=null){
		try{
		courseDao.save(course);
		map.put("status", "SUCCESS");
		map.put("message", "添加成功");
		}catch(Exception e){
		map.put("status","FALSE");
		map.put("messge", "添加失败");
		}
		}else{
			map.put("status","FALSE");
			map.put("messge", "参数错误");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	public String updateCourseAPI() throws IOException{
		Map<String,String> map = new HashMap<String, String>();
		if(course.getCourseid()!=null&&course.getHours()!=null&&course.getNumber()!=null&&course.getTeacher()!=null&&course.getNumber()!=null){
		try{
		courseDao.update(course);
		map.put("status", "SUCCESS");
		map.put("message", "更改成功");
		}catch(Exception e){
		map.put("status","FALSE");
		map.put("messge", "更改失败");
		}
		}else{
		map.put("status", "FALSE");
		map.put("messge", "参数错误");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	public String deleteCourseAPI() throws IOException{
		Map<String,String> map = new HashMap<String, String>();
		if(course.getCourseid()!=null){
		try{
		courseDao.deleteById(course.getCourseid());
		map.put("status", "SUCCESS");
		map.put("message", "更改成功");
		}catch(Exception e){
		map.put("status","FALSE");
		map.put("messge", "更改失败");
		}
		}else{
			map.put("status", "FALSE");
			map.put("messge", "参数错误");	
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}

}
