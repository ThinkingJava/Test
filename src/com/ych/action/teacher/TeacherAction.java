package com.ych.action.teacher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ych.action.BaseAction;
import com.ych.entity.Attend;
import com.ych.entity.Course;
import com.ych.entity.Teacher;
import com.ych.model.PageModel;
import com.ych.openCVUtils.DetectFaceUtil;
import com.ych.util.ConstUtil;
import com.ych.util.ResultUtils;
import com.ych.util.StringUitl;
/**
 * 
 * ClassName: TeacherAction 
 * @Description: TODO
 * @author 杨城欢
 * @date 2016年10月1日
 */
@Scope("prototype")
@Controller("teacherAction")
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
	
	private File photo;
	


	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	
	
	public String getAllTeacherAPI() throws IOException{
    	PageModel<Teacher> list=teacherDao.findAll();
    	ResultUtils.toJson(ServletActionContext.getResponse(), list);
    	return null;
    }
    
	
	public String getOneTeacherAPI() throws IOException{
	    Teacher list=teacherDao.findByTeacher(teacher.getTeacherid());
	    ResultUtils.toJson(ServletActionContext.getResponse(), list);	
		return null;
	}
	
	public String addTeacherAPI() throws IOException{
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
	
	public String updateTeacherAPI() throws IOException{
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
	
	public String deleteGradeAPI() throws IOException{
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
	
	
	public String teacherMessage(){
		Teacher teacher = (Teacher) ServletActionContext.getRequest().getSession().getAttribute("teacher");
		if(teacher!=null)
		return SUCCESS;
		else
		return LOGIN;
	}
	
	public String index(){
		Teacher teacher = (Teacher) ServletActionContext.getRequest().getSession().getAttribute("teacher");
		if(teacher!=null){
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
		}
			
		else
			return LOGIN;
	}

	public String updateTeacherMessage(){
//		System.out.println("updateTeacher:"+teacher.getTeacherid()+":"+teacher.getTeachername()+":"+teacher.getDepartment()+":"+teacher.getSex());
		
		Teacher tea = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
		      if(tea!=null){
		    	  if(teacher.getTeacherid()!=null)
		    		  tea.setTeacherid(teacher.getTeacherid());
		    	  if(teacher.getTeachername()!=null)
		    		  tea.setTeachername(teacher.getTeachername());
		    	  if(teacher.getSex()!=null)
		    		  tea.setSex(teacher.getSex());
		    	  if(teacher.getDepartment()!=null)
		    		  tea.setDepartment(teacher.getDepartment());

		try{
		if(photo!=null){
	//		DetectFaceUtil detectFace = new DetectFaceUtil();
	    	boolean isExist = DetectFaceUtil.isExistFace(photo.getPath());
			if(isExist){  //判断是否存在人脸

			String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.TEACHERPATH).replace("\\", "/");
			String fileName = StringUitl.getStringTime() + ".png";
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
		    if(StringUtils.isNotEmpty(tea.getImagepath())){
		    	String outpath= ServletActionContext.getServletContext().getRealPath("").replace("\\", "/")+teacher.getImagepath();
		    	File outfile=new File(outpath);
		    	if(outfile.isFile()){
		    		outfile.delete();
		    	}
		    }
			tea.setImagepath(ConstUtil.TEACHERPATH+fileName);
			}catch (Exception e) {
				e.printStackTrace();
				map.put(ConstUtil.status, "3");
				map.put(ConstUtil.message, "发生异常");
				return SUCCESS;
			}finally{
				fos.flush();
				fos.close();
				fis.close();
			}
			}else{
				map.put(ConstUtil.status, "3");
				map.put(ConstUtil.message, "找不到人脸");
				return SUCCESS;
			}
		}
		teacherDao.saveOrUpdate(tea);
		ServletActionContext.getRequest().getSession().setAttribute("teacher", tea);
		map.put(ConstUtil.status, "1");
		map.put(ConstUtil.message, "更改成功");
		return SUCCESS;
		}catch(Exception e){
         e.printStackTrace();
      
		}
		map.put(ConstUtil.status, "3");
		map.put(ConstUtil.message, "发生异常");
        return SUCCESS;
		  }else
		  return LOGIN;
	}
	
	Map<String,String> map = new HashMap<String, String>();
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	
}
