package com.ych.action.teacher;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.ych.action.BaseAction;
import com.ych.entity.Grade;
import com.ych.entity.Teacher;
import com.ych.model.PageModel;
import com.ych.util.ResultUtils;

public class TeacherAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
    private Teacher teacher = new Teacher();
    private Integer teacherId;
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	} 
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	
	
	public String getAllTeacher() throws IOException{
    	PageModel<Teacher> list=teacherDao.findAll();
    	ResultUtils.toJson(ServletActionContext.getResponse(), list);
    	return null;
    }
    
	
	public String getOneTeacher() throws IOException{
	    Teacher list=teacherDao.findByTeacher(teacher.getTeacherId());
	    ResultUtils.toJson(ServletActionContext.getResponse(), list);	
		return null;
	}
	
	public String addTeacher() throws IOException{
		Map<String,String> map=new HashMap<String, String>();
		try{
		teacherDao.save(teacher);
		map.put("status", "SUCCESS");
		map.put("message", "添加成功");
		}catch(Exception e){
		map.put("status","FALSE");
		map.put("messge", "添加失败");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	public String updateTeacher() throws IOException{
		Map<String,String> map = new HashMap<String, String>();
		try{
		teacherDao.update(teacher);
		map.put("status", "SUCCESS");
		map.put("message", "更改成功");
		}catch(Exception e){
		map.put("status","FALSE");
		map.put("messge", "更改失败");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	public String deleteGrade() throws IOException{
		Map<String,String> map = new HashMap<String, String>();
		try{
		teacherDao.deleteById(teacherId);
		map.put("status", "SUCCESS");
		map.put("message", "更改成功");
		}catch(Exception e){
		map.put("status","FALSE");
		map.put("messge", "更改失败");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
}
