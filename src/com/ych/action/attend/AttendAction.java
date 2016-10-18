package com.ych.action.attend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.ych.entity.Student;
import com.ych.model.PageModel;
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
	public String getCursorAttendAPI() throws IOException{
	   List<Attend> attend= attendDao.find(pageNo, maxResult).getList();
	   Map<String ,Object> map=new HashedMap();
	   map.put("status", "SUCCESS");
	   map.put("data", attend);
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
	   
	   String fileName = StringUitl.getStringTime() + ".jpg";
	   

	   if(attend.getStudent().getStudentid()!=null&&attend!=null){
		   String imgStr=attend.getImagepath();
		   String temp="/student_"+attend.getStudent().getStudentid();
		   String imgpath = AbsolutePath+temp+"/"+fileName;
		   
			File dir = new File( AbsolutePath+temp);
			if(!dir.exists()){//文件夹不存在
				dir.mkdir();//创建文件夹
			}
			String relative = ConstUtil.STUDENTPATH+temp+"/"+fileName;
			   try {
				   boolean reslut =	FileUpload.generateImage(imgStr, imgpath);
				   map.put("uploadFile", reslut);
				   attend.setImagepath(relative);
				   attendDao.save(attend);
		          
				   map.put("status", "SUCCESS");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("uploadFile", false);
					map.put("status", "FALSE");
					map.put("message", "添加图片失败");
				}


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


	public String attendMessage(){
		
		Map<String, String> orderby = new HashMap<String, String>(1);//定义Map集合
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
			for(Attend temp:pageModel.getList()){
				System.out.println(temp.getAttendid()+":"+temp.getStudent().getStudentname()+":"+temp.getDatatime());
			}
			map.put("status", "0");
			map.put("courseid", attend.getCourse().getCourseid());
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return ERROR;
	}
	
	public String addAttend(){
		map.clear();
		Student tempstudent=studentDao.findByStudent(attend.getStudent().getStudentid());
		if(tempstudent==null){
			map.put("status", "3"); //
			map.put("message", "学生不存在");
			return SUCCESS;
		}
		if(photo!=null){
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
		}
		try{
		attendDao.save(attend);
		return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		return ERROR;
	}
	
	public String updateAttendStatus(){
//		map.clear();
		
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
	}
	
	
	public String deleteAttend(){
		if(StringUtils.isNotEmpty(attend.getAttendid().toString())){
			try{
			attendDao.deleteById(attend.getAttendid());
			
			Map<String, String> orderby = new HashMap<String, String>(1);//定义Map集合
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
				for(Attend temp:pageModel.getList()){
					System.out.println(temp.getAttendid()+":"+temp.getStudent().getStudentname()+":"+temp.getDatatime());
				}
				map.put("status", "0");
				map.put("courseid", attend.getCourse().getCourseid());
				return SUCCESS;
			}catch(Exception e){
				e.printStackTrace();
			}
			
			}catch(Exception e){
			e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public String addAttendCourse(){
		
		return SUCCESS;
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
