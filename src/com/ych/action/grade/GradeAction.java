package com.ych.action.grade;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ych.action.BaseAction;
import com.ych.entity.Grade;
import com.ych.model.PageModel;
import com.ych.util.ResultUtils;
/**
 * ClassName: GradeAction 
 * @Description: TODO
 * @author 杨城欢
 * @date 2016年9月24日
 */
@Scope("prototype")
@Controller("gradeAction")
public class GradeAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private Grade grade = new Grade();

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
//	private Integer gradeId;
//	private String name;
//	private Integer teacherId;
//	private String number;
//	private Integer hour; 
//	
//	public void setGradeId(Integer gradeId) {
//		this.gradeId = gradeId;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public void setTeacherId(Integer teacherId) {
//		this.teacherId = teacherId;
//	}
//	public void setNumber(String number) {
//		this.number = number;
//	}
//	public void setHour(Integer hour) {
//		this.hour = hour;
//	}

	public String getAllGradeAPI() throws Exception{
		Map<String,Object> map=new HashMap<String, Object>();
		PageModel<Grade> list= null;
		try{
		 list=gradeDao.findAll();
		map.put("status", "SUCCESS");
		map.put("message", "数据库查询失败");
		}catch(Exception e){
		map.put("status", "FALSE");
		}

		ResultUtils.toJson(ServletActionContext.getResponse(), list);
		return null;
	}
	
	public String getOneGradeAPI() throws IOException{
		
		Map<String,Object> map=new HashMap<String, Object>();
		Grade list=null;
	if(grade.getGradeId()!=null){
	   try{	   
	    list=gradeDao.findByGrade(grade.getGradeId());
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
	
	public String addGradeAPI() throws IOException{
		Map<String,String> map=new HashMap<String, String>();
		
//		grade.setGradeId(gradeId);
//		grade.setHour(hour);
//		grade.setName(name);
//		grade.setNumber(number);
//		grade.setTeacherId(teacherId);
		if(grade.getGradeId()!=null&&grade.getHour()!=null&&grade.getName()!=null&&grade.getTeacherId()!=null&&grade.getNumber()!=null){
		try{
		gradeDao.save(grade);
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
	
	public String updateGradeAPI() throws IOException{
		Map<String,String> map = new HashMap<String, String>();
//		grade.setGradeId(gradeId);
//		grade.setHour(hour);
//		grade.setName(name);
//		grade.setNumber(number);
//		grade.setTeacherId(teacherId);
		if(grade.getGradeId()!=null&&grade.getHour()!=null&&grade.getName()!=null&&grade.getTeacherId()!=null&&grade.getNumber()!=null){
		try{
		gradeDao.update(grade);
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
	
	public String deleteGradeAPI() throws IOException{
		Map<String,String> map = new HashMap<String, String>();
		if(grade.getGradeId()!=null){
		try{
		gradeDao.deleteById(grade.getGradeId());
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

