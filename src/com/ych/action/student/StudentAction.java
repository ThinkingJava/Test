package com.ych.action.student;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletInputStream;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.ych.action.BaseAction;
import com.ych.entity.Attend;
import com.ych.entity.Course;
import com.ych.entity.Major;
import com.ych.entity.Score;
import com.ych.entity.Student;
import com.ych.entity.Teacher;
import com.ych.model.PageModel;
import com.ych.openCVUtils.DetectFaceUtil;
import com.ych.util.ConstUtil;
import com.ych.util.FileUpload;
import com.ych.util.ResultUtils;
import com.ych.util.StringUitl;
/**
 * 
 * ClassName: StudentAction 
 * @Description: TODO
 * @author 杨城欢
 * @date 2016年10月1日
 */
@Scope("prototype")
@Controller("studentAction")
public class StudentAction extends BaseAction implements ModelDriven<Student>{

	private static final long serialVersionUID = 1L;
	private String courseid;

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public Student student=new Student();

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Student getStudent() {
		return student;
	}
	
	private File photo;

	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	
	private List<Major> major;
  
	public List<Major> getMajor() {
		return major;
	}

	public void setMajor(List<Major> major) {
		this.major = major;
	}

//	@SuppressWarnings("unchecked")
//	public String loginAPI() throws Exception{
////		String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.LOCALSTUDENTPATH).replace("\\", "/");
//         Map<String,Object> map=new HashMap<String,Object>();
//		 PageModel<Student> list=studentDao.find(pageNo,maxResult);
//		 Map<String,Object> li=new HashMap<String,Object>();
//		 for(int i=0;i<list.getList().size();i++){
//			 li.put("studentid",list.getList().get(i).getStudentid()+"");
//			 li.put("studentname",list.getList().get(i).getStudentname());
//			 li.put("sex", list.getList().get(i).getSex());
//			 li.put("major", list.getList().get(i).getMajor().getMajorname());
//			 
//			 List<Course> courses=new ArrayList<Course>(list.getList().get(i).getCourses());
//			 List<Object> templist=new ArrayList<Object>();
//			 for(Course course:courses){
//				Map<String,Object> temp=new HashMap<String,Object>();
//				temp.put("courseid",course.getCourseid());
//				temp.put("coursename",course.getCoursename());
//				temp.put("teacherid",course.getTeacher().getTeacherid());
//				temp.put("teachername",course.getTeacher().getTeachername());
//				templist.add(temp);
//			 }
//
//		     li.put("courses", templist);	 
//		     li.put("imagepath", list.getList().get(i).getImagepath());
//         
//		 }
//		 map.put("list", li);
//		 ResultUtils.toJson(ServletActionContext.getResponse(), map);  
//		
//		return null;
//	}
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
		try{
		Student tempStudent=studentDao.findByStudent(student.getStudentid());
		 Map<String,Object> tempMap=new HashMap<String, Object>();
		    tempMap.put("studentid", tempStudent.getStudentid());
			tempMap.put("studentname", tempStudent.getStudentname());
			tempMap.put("major", tempStudent.getMajor().toJSONObject());
			tempMap.put("sex", tempStudent.getSex());
			tempMap.put("score", tempStudent.getScore());
			tempMap.put("imagepath", tempStudent.getImagepath());
			tempMap.put("datatime", tempStudent.getDatatime());
			tempMap.put("remark", tempStudent.getRemark());
		map.put("student", tempMap);
		map.put("status", "SUCCESS");
		map.put("message", "查找数据成功");
		}catch(Exception e){
		e.printStackTrace();
		map.put("status", "FALSE");
		map.put("message", "查找数据失败");
		}
		
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
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
//        System.out.println(student.toString());
   //     JSONObject json=JSONObject.fromObject(sb.toString());
        
		if(student.getStudentname()!=null){
			
			   String temp="student_"+student.getStudentid();
		  	   String fileName = StringUitl.getStringTime() + ".png";
		  	   String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.LOCALSTUDENTPATH+temp);
		  	   String imgpath = ServletActionContext.getServletContext().getRealPath(ConstUtil.LOCALSTUDENTPATH+temp+"/"+fileName);
 	   
		  	   String imgStr=student.getImagepath();
				File dir = new File(AbsolutePath);
				if(!dir.exists()){//文件夹不存在
					if(dir.mkdirs()){
						System.out.println("创建文件夹成功");
					};//创建文件夹
				}
			   imgpath= imgpath.replaceAll("\\\\", "/");		
				String relative = ConstUtil.LOCALSTUDENTPATH+temp+"/"+fileName;
				   student.setImagepath(relative);
				   try {
					FileUpload.generateImage(imgStr, imgpath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("status", "FALSE");
					map.put("message", "图片存储失败");
				}	
				   try{
				  if(org.apache.commons.lang.StringUtils.isNotEmpty(courseid)){
					Course course= courseDao.findByCourse(courseid);
					if(course!=null){
				   Set<Course> tempset=	new HashSet<Course>();
				   tempset.add(course);
				   student.setCourses(tempset);
				   student.setDatatime(new Timestamp(System.currentTimeMillis()));
				   student.setRemark("移动创建");
				   student.setScore(50);
				   attendDao.save(student);
				   course.setNumber(course.getNumber()+1);
				   courseDao.saveOrUpdate(course);  //班级人数添加
				   map.put("message", "添加成功");
				   map.put("status", "SUCCESS");
					}else{
						  map.put("message", "不存在该班级");
						  map.put("status", "FALSE"); 	
					}
				  }else{
					  map.put("message", "添加错误");
					  map.put("status", "FALSE");  
				  }
				   }catch(Exception e){
					   e.printStackTrace(); 
					   map.put("message", "班级错误");
					   map.put("status", "FALSE");  
				   }
		}else{
			map.put("message", "参数不正确");
			 map.put("status", "FALSE");
		}
		 ResultUtils.toJson(ServletActionContext.getResponse(),map);
    	//studentDao.save(student);
    	return null;
    }
    
    public String findByStudentIdExistAPI() throws IOException{
    	Map<String ,Object> map=new HashedMap();
    	if(org.apache.commons.lang.StringUtils.isNotEmpty(student.getStudentid())){
    		
    	Student stu=studentDao.findByStudent(student.getStudentid());
    		if(stu!=null){
    		 map.put("message", "该学生已存在");
   			 map.put("status", "FALSE");	
    		}else{
       		 map.put("message", "该学生不存在");
      		 map.put("status", "SUCCESS");		
    		}
    	}else{
   		 map.put("message", "学号不能为空");
   		 map.put("status", "FALSE");
    	}
	
    	 ResultUtils.toJson(ServletActionContext.getResponse(),map);
    	return null;
    }
    
//    public String getCourseStudentAPI() throws IOException{
//    	 PageModel<Student> list=studentDao.find(pageNo,maxResult);
//		 ResultUtils.toJson(ServletActionContext.getResponse(), list); 
//		return null;
//    	
//    }
    
    public String getAllStudentAPI() throws IOException{
    	Map<String,Object> map=new HashMap<String,Object>();
    	List<Map<String,Object>> center=new ArrayList<Map<String,Object>>();
    	try{
    	PageModel<Student> pagemodel=studentDao.findAll();
        Iterator<Student> it= pagemodel.getList().iterator();
        while(it.hasNext()){
        	Student tempStudent=it.next();
    	Map<String,Object> tempMap=new HashMap<String, Object>();
	    tempMap.put("studentid", tempStudent.getStudentid());
		tempMap.put("studentname", tempStudent.getStudentname());
		tempMap.put("major", tempStudent.getMajor().toJSONObject());
		tempMap.put("sex", tempStudent.getSex());
		tempMap.put("score", tempStudent.getScore());
		tempMap.put("imagepath", tempStudent.getImagepath());
		tempMap.put("datatime", tempStudent.getDatatime());
		tempMap.put("remark", tempStudent.getRemark());
		center.add(tempMap);
        }
        map.put("student", center);
	    map.put("status", "SUCCESS");
	    map.put("message", "数据库查询正确");
    	}catch(Exception e){
    	e.printStackTrace();
    	map.put("status", "FALSE");
    	map.put("message", "数据库查询失败");
    	}
    	ResultUtils.toJson(ServletActionContext.getResponse(), map); 
    	return null;
    }
    
    public String deleteStudentAPI() throws IOException{
    	
        Map<String ,Object> map=new HashedMap();

        String AbsolutePath = ServletActionContext.getServletContext().getRealPath("");
        AbsolutePath=AbsolutePath.replaceAll("\\\\", "/");
        if(student.getStudentid()!=null){
       Student stu = studentDao.findByStudent(student.getStudentid());
       if(stu!=null){
        String path=AbsolutePath+stu.getImagepath();
        File file=new File(path);
        if(file.exists()&&file.isFile()){
        	if(file.delete()){
        		System.out.println("删除成功");
        	}
        }
        try{
        Iterator<Course> courseIt =  stu.getCourses().iterator();
        	while(courseIt.hasNext()){
        		Course course = courseIt.next();
        		course.setNumber(course.getNumber()-1);
        		courseDao.saveOrUpdate(course);
        	}
        	
        	stu.setCourses(null);  //设置不关联
        	studentDao.saveOrUpdate(stu);	
       	Iterator<Attend> attendit=stu.getAttends().iterator();
        	while(attendit.hasNext()){
        		Attend attend=attendit.next();
        		 attendDao.delete(attend);
        	}
        	
           	Iterator<Score> scoreit=stu.getScores().iterator();
        	while(scoreit.hasNext()){
        		Score score=scoreit.next();
        		 attendDao.delete(score);
        	}
      
        
    	studentDao.delete(stu);
        map.put("message", "删除成功");
    	map.put("status", "SUCCESS");
        }catch(Exception e){
        	e.printStackTrace();
            map.put("message", "数据删除失败");
        	map.put("status", "FALSE");
        }
        }else{
            map.put("message", "删除失败，不存在该学生");
        	map.put("status", "FALSE");
        }
        }else{
        	 map.put("message", "参数错误");
         	 map.put("status", "FALSE");
        }
    	 ResultUtils.toJson(ServletActionContext.getResponse(),map);
    	return null;
    }

    public String updateStudentAPI() throws IOException{
    	Map<String,Object> map=new HashMap<String, Object>();
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(ServletActionContext.getRequest().getInputStream(),"UTF-8"));  
        String line = null;  
        StringBuilder sb = new StringBuilder();  
        while((line = br.readLine())!=null){  
            sb.append(line);  
        }  
        JSONObject json=JSONObject.fromObject(sb.toString());
        System.out.println(json);
    	if(!json.isEmpty()){
    		student.setStudentid(json.getString("studentId"));
    		student.setDatatime(Timestamp.valueOf(json.getString("datatime")));
    		Major major=new Major();
    		major.setMajorid(json.getInt("major"));
    		student.setMajor(major);
    		student.setStudentname(json.getString("name"));
		  	Student outstudent=studentDao.findByStudent(student.getStudentid());  //获取原来数据
    		student.setImagepath(outstudent.getImagepath());
    	if(json.containsKey("studentImage")){
		  	   String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.LOCALSTUDENTPATH).replaceAll("\\\\","/");
		       String root = ServletActionContext.getServletContext().getRealPath("").replaceAll("\\\\", "/");
		  	   String outpath=root+outstudent.getImagepath();
		  	   File file=new File(outpath);
		  	   if(file.exists()&&file.isFile()){
		  		   file.delete();
		  	   }
		  	   
		  	   
		  	   String imgStr=json.getString("studentImage");
		  	   String fileName = StringUitl.getStringTime() + ".jpg";
			   String temp="student_"+student.getStudentname();
			   String imgpath = AbsolutePath+temp+"/"+fileName;
				File dir = new File(AbsolutePath+temp);
				if(!dir.exists()){//文件夹不存在
					if(dir.mkdirs()){
						System.out.println("创建文件夹成功");
					};//创建文件夹
				}
		
				String relative = ConstUtil.LOCALSTUDENTPATH+temp+"/"+fileName;
				   student.setImagepath(relative);
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

	@Override
	public String edit() throws Exception {
		// TODO Auto-generated method stub
		return super.edit();
	}

	@Override
	public String add() throws Exception {
		// TODO Auto-generated method stub
		return super.add();
	}
	
	public String studentMessage(){
		   Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
		      if(teacher!=null){
		System.out.println("--------------------");
		try{
			if(org.apache.commons.lang.StringUtils.isNotEmpty(student.getStudentid())){
				
				student=studentDao.findByStudent(student.getStudentid());
				major=majorDao.findAll().getList();
/*				for(Major m:major){
					System.out.println(m.getMajorid()+":"+m.getMajorname());
				}*/
				map.clear();
				map.put("student", student);
				map.put("major", major);
				map.put("courseid", courseid);
			}else{
				return ERROR;
			}
	
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
		  }else
		  return LOGIN;
	}



	private PageModel<Student> pageModel;

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

	public String updateStudent(){
		   Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
		      if(teacher!=null){
		map.clear();
		try{
			if(photo!=null){
				boolean isExist = DetectFaceUtil.isExistFace(photo.getPath());
				if(isExist){  //判断是否存在人脸
				 String temp="student_"+student.getStudentid();
			     String fileName = StringUitl.getStringTime() + ".png";
			  	 String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.LOCALSTUDENTPATH+temp+"/");
		
				FileInputStream fis = null;//输入流
				FileOutputStream fos = null;//输出流
				try {
					fis = new FileInputStream(photo);//根据上传文件创建InputStream实例
					File file= new File(AbsolutePath);
				    if(!file.exists()){
				    	file.mkdirs();
				    }
					fos = new FileOutputStream(new File(AbsolutePath,fileName)); //创建写入服务器地址的输出流对象
					byte[] bs = new byte[1024 * 4]; //创建字节数组实例
					int len = -1;
					while((len = fis.read(bs)) != -1){//循环读取文件
						fos.write(bs, 0, len);//向指定的文件夹中写数据
					}
			    if(StringUtils.isNotEmpty(student.getImagepath())){
			    	String outpath= ServletActionContext.getServletContext().getRealPath("").replace("\\", "/")+student.getImagepath();
			    	File outfile=new File(outpath);
			    	if(outfile.isFile()){
			    		outfile.delete();
			    	}
			    }
				student.setImagepath(ConstUtil.LOCALSTUDENTPATH+temp+"/"+fileName);
				}catch (Exception e) {
					e.printStackTrace();
					return ERROR;
				}finally{
					fos.flush();
					fos.close();
					fis.close();
				}
				}else{
					Student temp=studentDao.findByStudent(student.getStudentid()); //获取原来数据   关联表数据不能为空，如果为空会出现删除关联表数据
					student.setCourses(temp.getCourses());  //填充原来数据
					student.setAttends(temp.getAttends());
					student.setDatatime(temp.getDatatime());
					student.setScores(temp.getScores());
					studentDao.saveOrUpdate(student);
					
					major=majorDao.findAll().getList();
					map.clear();
					map.put("student", student);
					map.put("major", major);
					map.put("status", "3");
					map.put("message", "检查不到人脸");
					map.put("courseid", courseid);
					return SUCCESS;
				}
			}
			Student temp=studentDao.findByStudent(student.getStudentid()); //获取原来数据   关联表数据不能为空，如果为空会出现删除关联表数据
			student.setCourses(temp.getCourses());  //填充原来数据
			student.setAttends(temp.getAttends());
			student.setDatatime(temp.getDatatime());
			student.setScores(temp.getScores());
			studentDao.saveOrUpdate(student);
			
			major=majorDao.findAll().getList();
			map.clear();
			map.put("student", student);
			map.put("major", major);
			map.put("status", "1");
			map.put("courseid", courseid);
			return SUCCESS;
			}catch(Exception e){
               e.printStackTrace();
               
			}
	        return ERROR;
		      }else{
		    	  return LOGIN;
		      }
	}
	
	
	
	Map<String,Object> map=new HashMap<String,Object>();;

	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	

}
