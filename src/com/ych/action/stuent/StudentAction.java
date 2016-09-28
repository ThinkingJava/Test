package com.ych.action.stuent;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.ych.action.BaseAction;
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
	public Integer id;

    public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Student student=new Student();

	public void setStudent(Student student) {
		this.student = student;
	}



	public Student getStudent() {
		// TODO Auto-generated method stub
		return student;
	}
	

	public String loginAPI() throws Exception{
//		String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.LOCALPATH).replace("\\", "/");
		System.out.println("----------"+student.getName());
		
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
	public String getOneStudentAPI() throws IOException{
		Map<String ,Object> map=new HashedMap();
		Student list=studentDao.findByStudent(studentId);
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
    public String addStudentAPI() throws IOException {
    	
        Map<String ,Object> map=new HashedMap();
        
//        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)ServletActionContext.getRequest().getInputStream(),"UTF-8"));  
//        String line = null;  
//        StringBuilder sb = new StringBuilder();  
//        while((line = br.readLine())!=null){  
//            sb.append(line);  
//        }  
        System.out.println(student.toString());
   //     JSONObject json=JSONObject.fromObject(sb.toString());
        
		if(student!=null){
			System.out.println("------ww---------");
//		  	   student.setName(json.getString("name"));
//		  	   student.setDepartment(json.getString("department"));
//		  	   student.setAge(json.getInt("age"));
//		  	   student.setSex(json.getInt("sex"));
		
			   String temp="student_"+student.getName();
		  	   String fileName = StringUitl.getStringTime() + ".png";
		  	   String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.LOCALPATH+temp);
		  	   String imgpath = ServletActionContext.getServletContext().getRealPath(ConstUtil.LOCALPATH+temp+"/"+fileName);
 	   
//		  	   String imgStr=json.getString("studentImage");
		  	   String imgStr=student.getStudentImage();
				File dir = new File(AbsolutePath);
				if(!dir.exists()){//文件夹不存在
					if(dir.mkdirs()){
						System.out.println("创建文件夹成功");
					};//创建文件夹
				}
			   imgpath= imgpath.replaceAll("\\\\", "/");		
				String relative = ConstUtil.LOCALPATH+temp+"/"+fileName;
				   student.setStudentImage(relative);
				   try {
					FileUpload.generateImage(imgStr, imgpath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("status", "FALSE");
					map.put("message", "图片存储失败");
				}	
				   System.out.println("数据:"+student.toString());
				   attendDao.save(student);
				   map.put("status", "SUCCESS");
   
		}else{
			map.put("message", "参数不正确");
		}
		 ResultUtils.toJson(ServletActionContext.getResponse(),map);
    	//studentDao.save(student);
    	return null;
    }
    
    public String getCourseStudentAPI() throws IOException{
    	 PageModel<Student> list=studentDao.find(pageNo,maxResult);
		 ResultUtils.toJson(ServletActionContext.getResponse(), list); 
		return null;
    	
    }
    
    public String getAllStudent() throws IOException{
    	PageModel<Student> list=studentDao.findAll();
    	ResultUtils.toJson(ServletActionContext.getResponse(), list); 
    	return null;
    }
    
    public String deleteStudentAPI() throws IOException{
    	
        Map<String ,Object> map=new HashedMap();

        String AbsolutePath = ServletActionContext.getServletContext().getRealPath("");
        AbsolutePath=AbsolutePath.replaceAll("\\\\", "/");
        if(studentId>0){
       Student student = studentDao.findByStudent(studentId);
        String path=AbsolutePath+student.getStudentImage();
        File file=new File(path);
        if(file.exists()&&file.isFile()){
        	if(file.delete()){
        		System.out.println("删除成功");
        	}
        }
    	studentDao.delete(student);
        }
    	map.put("status", "SUCCESS");
    	 ResultUtils.toJson(ServletActionContext.getResponse(),map);
    	return null;
    }

    public String updateStudentAPI() throws IOException{
    	Map<String,Object> map=new HashMap<String, Object>();
    	
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)ServletActionContext.getRequest().getInputStream(),"UTF-8"));  
        String line = null;  
        StringBuilder sb = new StringBuilder();  
        while((line = br.readLine())!=null){  
            sb.append(line);  
        }  
        JSONObject json=JSONObject.fromObject(sb.toString());
        System.out.println(json);
    	if(!json.isEmpty()){
    		student.setStudentId(json.getInt("studentId"));
    		student.setAge(json.getInt("age"));
    		student.setDepartment(json.getString("department"));
    		student.setName(json.getString("name"));
		  	Student outstudent=studentDao.findByStudent(student.getStudentId());
    		student.setStudentImage(outstudent.getStudentImage());
    	if(json.containsKey("studentImage")){
		  	   String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.LOCALPATH).replaceAll("\\\\","/");
		        String root = ServletActionContext.getServletContext().getRealPath("").replaceAll("\\\\", "/");
//		  	   Student outstudent=studentDao.findByStudent(student.getStudentId());
		  	   String outpath=root+outstudent.getStudentImage();
		  	   File file=new File(outpath);
		  	   if(file.exists()&&file.isFile()){
		  		   file.delete();
		  	   }
		  	   
		  	   
		  	   String imgStr=json.getString("studentImage");
		  	   String fileName = StringUitl.getStringTime() + ".jpg";
			   String temp="student_"+student.getName();
			   String imgpath = AbsolutePath+temp+"/"+fileName;
				File dir = new File(AbsolutePath+temp);
				if(!dir.exists()){//文件夹不存在
					if(dir.mkdirs()){
						System.out.println("创建文件夹成功");
					};//创建文件夹
				}
		
				String relative = ConstUtil.LOCALPATH+temp+"/"+fileName;
				   student.setStudentImage(relative);
				   try {
					FileUpload.generateImage(imgStr, imgpath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("status", "FALSE");
					map.put("message", "图片存储失败");
				}	
    	}
    	studentDao.update(student);
    	}
    	 
    	 ResultUtils.toJson(ServletActionContext.getResponse(),map);
    	return null;
    }
	
     
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
		pageModel=studentDao.find(pageNo, maxResult);
		return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		return ERROR;
	}

	

	/* (非 Javadoc) 
	 * <p>Title:edit</p> 
	 * <p>Description: </p> 
	 * @return
	 * @throws Exception 
	 * @see com.ych.action.BaseAction#edit() 
	 */ 
	@Override
	public String edit() throws Exception {
		// TODO Auto-generated method stub
		return super.edit();
	}


	/* (非 Javadoc) 
	 * <p>Title:add</p> 
	 * <p>Description: </p> 
	 * @return
	 * @throws Exception 
	 * @see com.ych.action.BaseAction#add() 
	 */ 
	@Override
	public String add() throws Exception {
		// TODO Auto-generated method stub
		return super.add();
	}


	private Integer studentId;
	private PageModel<Student> pageModel;



	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


	public PageModel<Student> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<Student> pageModel) {
		this.pageModel = pageModel;
	}


	@Override
	public Student getModel() {
		// TODO Auto-generated method stub
		return student;
	}

	
	

}
