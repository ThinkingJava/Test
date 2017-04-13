package com.ych.action.attend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.ych.action.BaseAction;
import com.ych.entity.Attend;
import com.ych.entity.Course;
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
 * ClassName: AttendAction 
 * @Description: TODO
 * @author 杨城欢
 * @date 2016年10月1日
 */
@Scope("prototype")
@Controller("attendAction")
public class AttendAction extends BaseAction implements ModelDriven<Attend>{

	private static final long serialVersionUID = 1L;
	


	@Override
	public Attend getModel() {
		// TODO Auto-generated method stub
		return attend;
	}

/**
 * 
 * @Title: getAttend 
 * @Description: TODO(根据attendId查询考勤) 
 */
	public String getAttendAPI() throws IOException{
		List<Attend> temp=null;
		try{
		temp= attendDao.findByAttend(attend.getAttendid());
		}catch(Exception e){
			
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), temp);
		
		return null;
	}
	/**
	 * 
	 * @Title: getAttend 
	 * @Description: TODO(根据attendId查询考勤) 
	 */
	public String getCourseAttendAPI() throws IOException{
		Map<String ,Object> map=new HashedMap();	
		if(attend.getCourse().getCourseid()!=null){
			try{
		Course course =	courseDao.findByCourse(attend.getCourse().getCourseid());
		if(course!=null){

		List<Attend> list=	attendDao.findByAttendCourse(attend.getCourse().getCourseid());
		if(list!=null){
			List<Map<String,Object>> tempList=new ArrayList<Map<String,Object>>();
              
               for(Attend attend:list){
            	   Map<String,Object> tempMap=new HashMap<String,Object>();
            	   tempMap.put("attendid", attend.getAttendid());
            	   tempMap.put("course", attend.getCourse().toJSONObject());
            	   tempMap.put("student", attend.getStudent().toJSONObject());
            	   tempMap.put("datatime", attend.getDatatime().toString());
            	   tempMap.put("status", attend.getStatus());
            	   tempMap.put("imagepath", attend.getImagepath());
            	   tempList.add(tempMap);
               }
			   map.put("status", "SUCCESS");
			   map.put("totalRecords", tempList.size());
			   map.put("totalCount", course.getNumber());
			   map.put("attends", tempList);
			   map.put("message", "查询结果正确");
		}else{
			map.put("status", "FALSE");
			map.put("message", "没有考勤");
		}
		}else{
			map.put("status", "FALSE");
			map.put("message", "不存在该班级");
		}
			}catch(Exception e){
				e.printStackTrace();
				map.put("status", "FALSE");
				map.put("message", "数据库查询出错");
			}
		}else{
			map.put("status", "FALSE");
			map.put("message", "参数错误");
		}

	   ResultUtils.toJson(ServletActionContext.getResponse(), map);
	   return null;
	}
	/**
	 * 
	 * @Title: addAttend 
	 * @Description: TODO(添加考勤信息) 
	 * @param @return
	 * @param @throws IOException 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String addAttendAPI() throws IOException {
		
	       Map<String ,Object> map=new HashedMap();	
	   String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.STUDENTPATH).replace("\\", "/");  
	   String fileName = StringUitl.getStringTime() + ".png";
	   

	   if(attend.getStudent().getStudentid()!=null&&attend.getCourse().getCourseid()!=null){
		   
		 boolean isExit = attendDao.findIsExitAttend(attend.getStudent().getStudentid(), attend.getCourse().getCourseid());
		   if(!isExit){
		   String imgStr=attend.getImagepath();
		   String temp="student_"+attend.getStudent().getStudentid();
		   String imgpath = AbsolutePath+"/"+temp+"/"+fileName;
		   
			File dir = new File( AbsolutePath+"/"+temp);
			if(!dir.exists()){//文件夹不存在
				dir.mkdirs();//创建文件夹
			}
			String relative = ConstUtil.STUDENTPATH+temp+"/"+fileName;
			   try {
				   boolean reslut =	FileUpload.generateImage(imgStr, imgpath);
				   map.put("uploadFile", reslut);
				   attend.setImagepath(relative);
				   attend.setDatatime(new Timestamp(System.currentTimeMillis()));
				   attend.setStatus(1);  
				   attendDao.save(attend);
		          
				   map.put("status", "SUCCESS");
				   map.put("message", "学号:"+attend.getStudent().getStudentid()+" 添加考勤成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("uploadFile", false);
					map.put("status", "FALSE");
					map.put("message", "添加考勤失败");
				}
		   }else{
				map.put("uploadFile", false);
				map.put("status", "FALSE");
				map.put("message","学号:"+ attend.getStudent().getStudentid()+"考勤已存在");   
		   }
	   }else{
			map.put("uploadFile", false);
			map.put("status", "FALSE");
			map.put("message", "参数错误");   
	   }
      
	   ResultUtils.toJson(ServletActionContext.getResponse(),map);
		return null;
	}
	/**
	 * 
	 * @Title: updateAttend 
	 * @Description: TODO(更改考勤信息) 
	 * @param @return
	 * @param @throws IOException 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	
	public String updateAttendAPI() throws IOException{
	   	Map<String,Object> map=new HashMap<String, Object>();
    	if(StringUtils.isNotEmpty(attend.getImagepath())){
	  	   String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.STUDENTPATH).replaceAll("\\","/");
	  	   String imgStr=attend.getImagepath();
	  	   String fileName = StringUitl.getStringTime() + ".jpg";
		   String temp="/student_"+attend.getStudent().getStudentid();
		   String imgpath = AbsolutePath+temp+"/"+fileName;
		   
			File dir = new File( AbsolutePath+temp);
			if(!dir.exists()){//文件夹不存在
				dir.mkdir();//创建文件夹
			}
			
			String relative = ConstUtil.STUDENTPATH+temp+"/"+fileName;
			   attend.setImagepath(relative);
			   try {
				FileUpload.generateImage(imgStr, imgpath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("status", "FALSE");
				map.put("message", "图片存储失败");
			}	
    	}
    	try{
    		attendDao.update(attend);
    		map.put("status", "SUCCESS");
    	}catch(Exception e){
    		e.printStackTrace();
    		map.put("status", "FALSE");
    		map.put("message", "数据库更改失败");
    	}
		


		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	/**
	 * 
	 * @Title: deleteAttend 
	 * @Description: TODO(删除考勤信息) 
	 * @param @return
	 * @param @throws IOException 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String deleteAttendAPI() throws IOException{
		attendDao.delete(attend);
		Map<String, Object> map = new HashedMap();
		map.put("status", "SUCCESS");
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		  return null;
	}

	
	public String addAttendCourseAPI() throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		if(attend.getCourse().getCourseid()!=null&&attend.getStudent().getStudentid()!=null){
			Student tempstudent=studentDao.findByStudent(attend.getStudent().getStudentid());
			Course tempCourse = courseDao.findByCourse(attend.getCourse().getCourseid());
			if(tempstudent==null||tempCourse==null){
				map.put("status", "FALSE"); //
				map.put("message", "学生或课程不存在");
			}else{
			if(attend.getImagepath()!=null){
	      String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.STUDENTPATH).replace("\\", "/");	   
	      String fileName = StringUitl.getStringTime() + ".png";
		   String temp="/student_"+attend.getStudent().getStudentid();
		   String imgpath = ServletActionContext.getServletContext().getRealPath(ConstUtil.STUDENTPATH+temp+"/"+fileName);
			File dir = new File( AbsolutePath+temp);
			if(!dir.exists()){//文件夹不存在
				dir.mkdirs();//创建文件夹
			}
			String imgStr=attend.getImagepath();
		     
//			FileInputStream fis = null;//输入流
//			FileOutputStream fos = null;//输出流
				
			   imgpath= imgpath.replaceAll("\\\\", "/");		
				String relative = ConstUtil.STUDENTPATH+temp+"/"+fileName;
				   attend.setImagepath(relative);
				   try {
					FileUpload.generateImage(imgStr, imgpath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("status", "FALSE");
					map.put("message", "图片存储失败");
				}

			attend.setImagepath(ConstUtil.STUDENTPATH+temp+"/"+fileName);
			}
			attend.setDatatime(new Timestamp(System.currentTimeMillis()));
			try{
			attendDao.save(attend);

			}catch(Exception e){
				e.printStackTrace();
				map.put("status", "FALSE"); //
				map.put("message", "保存失败");
			}
		}
		}
		return null;
	}

	public String attendMessage(){
		   Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
		      if(teacher!=null){
		Map<String, String> orderby = new HashMap<String, String>();//定义Map集合
		orderby.put("datatime", "desc");//设置按创建时间倒序排列
		StringBuffer whereBuffer = new StringBuffer("");//创建字符串对象
		List<Object> params = new ArrayList<Object>();
		if(attend.getCourse().getCourseid() != null && attend.getCourse().getCourseid().length() > 0){//如果订单号不为空
			whereBuffer.append("courseid = ?");//以订单号为查询条件
			params.add(attend.getCourse().getCourseid());//设置参数
		}
/*		if(attend.getStatus() != null){//如果考勤状态不为空
			if(params.size() > 0){
				whereBuffer.append(" and ");//增加查询条件
			}
			whereBuffer.append("orderState = ?");//设置考勤状态为查询条件
			params.add(attend.getStatus());//设置参数
		}*/
		//如果whereBuffer为空则查询条件为空，否则以whereBuffer为查询条件
		String where = whereBuffer.length()>0 ? "where "+whereBuffer.toString() : "";
		try{
			pageModel=attendDao.find(where, params.toArray(), orderby, pageNo, maxResult);
//			for(Attend temp:pageModel.getList()){
//				System.out.println(temp.getAttendid()+":"+temp.getStudent().getStudentname()+":"+temp.getDatatime());
//			}
			map.put("status", "0");
			map.put("courseid", attend.getCourse().getCourseid());
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}	
	  return ERROR;
    }else
	  return LOGIN;
	}
	
	public String attendPre(){
		   Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
		      if(teacher!=null){
		map.clear();
		
		if(StringUtils.isNotEmpty(attend.getAttendid()+"")){
			attend=attendDao.findByAttend(attend.getAttendid()).get(0);
			map.put("status", ConstUtil.select+"");
			map.put("message", "考勤记录查询");
		}else{
			map.put("status", ConstUtil.error+"");
			map.put("message", "不存在该考勤记录");
			return ERROR;
		}
		return SUCCESS;
		  }else
		  return LOGIN;
	}
	
	public String addAttend(){
		   Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
       if(teacher!=null){
	   map.clear();
		if(attend.getStudent().getStudentid()!=null&&attend.getCourse().getCourseid()!=null){
		Student tempstudent=studentDao.findByStudent(attend.getStudent().getStudentid());
		Course tempCourse = courseDao.findByCourse(attend.getCourse().getCourseid());
		if(tempstudent==null){
			map.put("status", "3"); //
			map.put("message", "学生不存在");
			map.put("courseid", attend.getCourse().getCourseid());
			return SUCCESS;
		}
		boolean isLive=false;
	  Iterator<Course> itCourse = tempstudent.getCourses().iterator();
	  while(itCourse.hasNext()){
		  Course course = itCourse.next();
		  if(course.getCourseid().equals(attend.getCourse().getCourseid())){
			  isLive=true;
		  }
	  }
	  if(!isLive){
			map.put("status", "3"); //
			map.put("message", "该学生不存在该课程");
			map.put("courseid", attend.getCourse().getCourseid());
			return SUCCESS;
	  }
		
		if(photo!=null){
			boolean isExist = DetectFaceUtil.isExistFace(photo.getPath());
			if(isExist){  //判断是否存在人脸
      String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.STUDENTPATH).replace("\\", "/");	   
      String fileName = StringUitl.getStringTime() + ".jpg";
	   String temp="/student_"+attend.getStudent().getStudentid();
	   
		File dir = new File( AbsolutePath+temp);
		if(!dir.exists()){//文件夹不存在
			dir.mkdirs();//创建文件夹
		}
	
	     try{
		FileInputStream fis = null;//输入流
		FileOutputStream fos = null;//输出流
		try {
			fis = new FileInputStream(photo);//根据上传文件创建InputStream实例
			fos = new FileOutputStream(new File(AbsolutePath+temp,fileName)); //创建写入服务器地址的输出流对象
			byte[] bs = new byte[1024 * 4]; //创建字节数组实例
			int len = -1;
			while((len = fis.read(bs)) != -1){//循环读取文件
				fos.write(bs, 0, len);//向指定的文件夹中写数据
			}

		}catch (Exception e) {
			map.put("status", "3"); //
			map.put("message", "圖片保存失敗");
			map.put("courseid", attend.getCourse().getCourseid());
			e.printStackTrace();
			return ERROR;
		}finally{
			fos.flush();
			fos.close();
			fis.close();
		}
	     }catch(IOException e){
	    	 e.printStackTrace();
	    	 return ERROR;
	     }
		attend.setImagepath(ConstUtil.STUDENTPATH+temp+"/"+fileName);
		}else{
			map.put("status", "3"); //
			map.put("message", "检测不到人脸");
			map.put("courseid", attend.getCourse().getCourseid());
			return SUCCESS;
		}
		}
		attend.setDatatime(new Timestamp(System.currentTimeMillis()));
		try{
			attend.setCourse(tempCourse);
			attend.setStudent(tempstudent);
		attendDao.save(attend);
		map.put("status", "1"); //
		map.put("message", "考勤添加成功");
		map.put("courseid", attend.getCourse().getCourseid());
		return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			map.put("status", "3"); //
			map.put("message", "考勤添加失敗");
			map.put("courseid", attend.getCourse().getCourseid());
		}
		return ERROR;
		}
      }else {
    	  return LOGIN; 
      }
      return LOGIN;
	}
	
	public String updateAttendStatus(){
//		map.clear();
	 Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
     if(teacher!=null){
		if(StringUtils.isNotEmpty(attend.getAttendid()+"")){
			try{
			 Attend temp=attendDao.findByAttend(attend.getAttendid()).get(0);
				if(temp.getStatus()==1)
					temp.setStatus(0);
				else
					temp.setStatus(1);
				//更改考勤状态
//				map.put("status", "2");
//				map.put("message", "数据库操作成功");
				attendDao.saveOrUpdate(temp);
			}catch(Exception e){
				e.printStackTrace();
//				map.put("status", "3");
//				map.put("message", "数据库查找错误");
//				return ERROR;
			}
	
		}	
	  return null;
      }else
	  return LOGIN;
	}
	/**
	 * 
	 * @Description: TODO
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 杨城欢
	 * @date 2016年11月6日
	 */
	
	public String deleteAttend(){
		Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
	 if(teacher!=null){
		
			try{
				if(attend.getAttendid()!=null){
			List<Attend> list= attendDao.findByAttend(attend.getAttendid());
			if(list.size()>0){
			Attend attendtemp = attendDao.findByAttend(attend.getAttendid()).get(0);
			attendDao.delete(attendtemp);
			}
			}
			Map<String, String> orderby = new HashMap<String, String>(1);//定义Map集合
			orderby.put("datatime", "desc");//设置按创建时间倒序排列
			StringBuffer whereBuffer = new StringBuffer("");//创建字符串对象
			List<Object> params = new ArrayList<Object>();
			if(attend.getCourse().getCourseid() != null && attend.getCourse().getCourseid().length() > 0){//如果课程号不为空
				whereBuffer.append("courseid = ?");//以订单号为查询条件
				params.add(attend.getCourse().getCourseid());//设置参数
			}
	/*		if(attend.getStatus() != null){//如果考勤状态不为空
				if(params.size() > 0){
					whereBuffer.append(" and ");//增加查询条件
				}
				whereBuffer.append("orderState = ?");//设置考勤状态为查询条件
				params.add(attend.getStatus());//设置参数
			}*/
			//如果whereBuffer为空则查询条件为空，否则以whereBuffer为查询条件
			String where = whereBuffer.length()>0 ? "where "+whereBuffer.toString() : "";
			try{
				pageModel=attendDao.find(where, params.toArray(), orderby, pageNo, maxResult);
				map.put("status", "0");
				map.put("courseid", attend.getCourse().getCourseid());
				attend.setAttendid(null);
				return SUCCESS;
			}catch(Exception e){
				e.printStackTrace();
				return SUCCESS;
			}
			
			}catch(Exception e){
			e.printStackTrace();
			}
		
		return null;
	 }else
		 return LOGIN;
	}
	
	public String addAttendCourse(){
	 Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
		  if(teacher!=null){
	     map.clear();
		 map.put("courseid", attend.getCourse().getCourseid());
		return SUCCESS;
		     }else
		   return LOGIN;
	}
	
	private Attend attend=new Attend();

	public Attend getAttend() {
		return attend;
	}

	public void setAttend(Attend attend) {
		this.attend = attend;
	}
	
	private PageModel<Attend> pageModel;// 分页组件

	public PageModel<Attend> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<Attend> pageModel) {
		this.pageModel = pageModel;
	}
	
	Map<String, Object> map = new HashMap<String, Object>();

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	private File  photo;

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}
	
	
	
}
