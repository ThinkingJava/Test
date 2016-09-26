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
	
	public String getAllGrade() throws Exception{
		List<Grade> list=gradeDao.findAll();
		ResultUtils.toJson(ServletActionContext.getResponse(), list);
		return null;
	}
	
	public String getOneGrade() throws IOException{
		System.out.println("----"+grade.toString());
	    Grade list=gradeDao.findByGrade(1);
	    System.out.println("--46--"+list);
	    ResultUtils.toJson(ServletActionContext.getResponse(), list);	
		return null;
	}
	
	public String addGrade() throws IOException{
		Map<String,String> map=new HashMap<String, String>();
		try{
		gradeDao.save(grade);
		map.put("status", "SUCCESS");
		map.put("message", "添加成功");
		}catch(Exception e){
		map.put("status","FALSE");
		map.put("messge", "添加失败");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	public String updateGrade() throws IOException{
		Map<String,String> map = new HashMap<String, String>();
		try{
		gradeDao.update(grade);
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
		gradeDao.delete(grade);
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
