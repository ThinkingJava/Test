package com.ych.action.stuent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.ych.action.BaseAction;
import com.ych.dao.student.StudentDao;
import com.ych.dao.student.StudentDaoImpl;
import com.ych.entity.Student;
import com.ych.model.PageModel;
import com.ych.util.ConstUtil;
import com.ych.util.FileUpload;
import com.ych.util.ResultUtils;
import com.ych.util.StringUitl;

@Scope("prototype")
@Controller("studentAction")
public class StudentAction extends BaseAction implements ModelDriven<Student>{

	private static final long serialVersionUID = 1L;
	private Integer id;

    public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	private Student student=new Student();
	
	@Override
	public Student getModel() {
		// TODO Auto-generated method stub
		return student;
	}
	

	public String login() throws Exception{
//		String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.LOCALPATH).replace("\\", "/");
		 System.out.println("------"+pageNo+"----"+maxResult);
		 PageModel<Student> list=studentDao.find(pageNo,maxResult);
		 ResultUtils.toJson(ServletActionContext.getResponse(), list);  
		
		return null;
	}
	/**
	 * 
	 * @Title: getStudent 
	 * @Description: TODO(查找学生) 
	 * @param @return
	 * @param @throws IOException 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String getStudent() throws IOException{
		Map<String ,Object> map=new HashedMap();
		List<Student> list=studentDao.findByStudent(studentId);
		ResultUtils.toJson(ServletActionContext.getResponse(), list);
		return null;
	}
	
	
	
/**
 * 
 * @Title: addStudent 
 * @Description: TODO(添加学生) 
 * @param @return
 * @param @throws IOException 设定文件 
 * @return String 返回类型 
 * @throws
 */
    public String addStudent() throws IOException {
        Map<String ,Object> map=new HashedMap();
        
		if(student!=null){
		  	   String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.LOCALPATH).replaceAll("\\","/");
		  	   String imgStr=student.getStudentImage();
		  	   String fileName = StringUitl.getStringTime() + ".jpg";
			   String temp="/student_"+student.getName();
			   String imgpath = AbsolutePath+temp+"/"+fileName;
			   
				File dir = new File( AbsolutePath+temp);
				if(!dir.exists()){//文件夹不存在
					dir.mkdir();//创建文件夹
				}
				
				String relative = ConstUtil.LOCALPATH+temp+"/"+fileName;
				   student.setStudentImage(relative);
				   attendDao.save(student);
				   map.put("status", "SUCCESS");
				   try {
					FileUpload.generateImage(imgStr, imgpath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("status", "FALSE");
				}	   
		}
		 ResultUtils.toJson(ServletActionContext.getResponse(),map);
    	//studentDao.save(student);
    	return null;
    }
    
    public String getCourseStudent() throws IOException{
    	 PageModel<Student> list=studentDao.find(pageNo,maxResult);
		 ResultUtils.toJson(ServletActionContext.getResponse(), list); 
		return null;
    	
    }
    
    public String getAllStudent() throws IOException{
    	List<Student> list=studentDao.findAll();
    	ResultUtils.toJson(ServletActionContext.getResponse(), list); 
    	return null;
    }
    
    public String deleteStudent() throws IOException{
        Map<String,Object> map=new HashMap<String, Object>();
    	studentDao.delete(student);
    	map.put("status", "SUCCESS");
    	return null;
    }

	
	/*@Override
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
		*/
	private Integer studentId;


	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	

}
