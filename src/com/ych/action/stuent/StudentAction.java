package com.ych.action.stuent;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



import com.ych.action.BaseAction;
import com.ych.entity.Student;
import com.ych.model.PageModel;
import com.ych.util.ResultUtils;

@Scope("prototype")
@Controller("studentAction")
public class StudentAction extends BaseAction {

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;


	public String login() throws Exception{
		System.out.println("======================================");
		 PageModel<Student> list=studentDao.find(1, 2);
		 ResultUtils.toJson(ServletActionContext.getResponse(), list);  
		
		return null;
	}
	
	
	@Override
	public String add() throws Exception {
		// TODO Auto-generated method stub
		return super.add();
	}


	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return super.list();
	}


	@Override
	public String edit() throws Exception {
		// TODO Auto-generated method stub
		return super.edit();
	}


	@Override
	public String select() throws Exception {
		// TODO Auto-generated method stub
		return super.select();
	}
	


	@Override
	public String query() throws Exception {
		// TODO Auto-generated method stub
		return super.query();
	}
	
	private Integer studentId;


	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	

}
