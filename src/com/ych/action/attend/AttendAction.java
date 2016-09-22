package com.ych.action.attend;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.ych.action.BaseAction;
import com.ych.entity.Attend;
import com.ych.util.ConstUtil;
import com.ych.util.FileUpload;
import com.ych.util.ResultUtils;
import com.ych.util.StringUitl;

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
	public String getAttend() throws IOException{
		List<Attend> attend= attendDao.findByAttend(attendId);
		ResultUtils.toJson(ServletActionContext.getResponse(), attend);
		
		return null;
	}
	/**
	 * 
	 * @Title: getAttend 
	 * @Description: TODO(根据attendId查询考勤) 
	 */
	public String getCursorAttend() throws IOException{
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
	public String addAttend() throws IOException {
		
	       Map<String ,Object> map=new HashedMap();
		
	   String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.NEWPATH).replace("\\", "/");
	   
	   String fileName = StringUitl.getStringTime() + ".jpg";
	   

	   if(studentId!=null&&attend!=null){
		   String imgStr=attend.getStudentImage();
		   String temp="/student_"+studentId;
		   String imgpath = AbsolutePath+temp+"/"+fileName;
		   
			File dir = new File( AbsolutePath+temp);
			if(!dir.exists()){//文件夹不存在
				dir.mkdir();//创建文件夹
			}
			String relative = ConstUtil.NEWPATH+temp+"/"+fileName;
			   try {
					FileUpload.generateImage(imgStr, imgpath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("status", "FALSE");
					map.put("message", "添加图片失败");
				}
		   attend.setStudentImage(relative);
		   
		   attendDao.save(attend);

		   map.put("status", "SUCCESS");

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
	
	public String updateAttend() throws IOException{
	   	Map<String,Object> map=new HashMap<String, Object>();
    	if(StringUtils.isNotEmpty(attend.getStudentImage())){
	  	   String AbsolutePath = ServletActionContext.getServletContext().getRealPath(ConstUtil.NEWPATH).replaceAll("\\","/");
	  	   String imgStr=attend.getStudentImage();
	  	   String fileName = StringUitl.getStringTime() + ".jpg";
		   String temp="/student_"+attend.getAttendId();
		   String imgpath = AbsolutePath+temp+"/"+fileName;
		   
			File dir = new File( AbsolutePath+temp);
			if(!dir.exists()){//文件夹不存在
				dir.mkdir();//创建文件夹
			}
			
			String relative = ConstUtil.NEWPATH+temp+"/"+fileName;
			   attend.setStudentImage(relative);
			   try {
				FileUpload.generateImage(imgStr, imgpath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("status", "FALSE");
				map.put("message", "图片存储失败");
			}	
    	}
		attendDao.update(attend);

		map.put("status", "SUCCESS");
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
	public String deleteAttend() throws IOException{
		attendDao.delete(attend);
		Map<String, Object> map = new HashedMap();
		map.put("status", "SUCCESS");
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		  return null;
	}
	
	
	private Attend attend=new Attend();
	
	private Integer attendId; 

    private Integer studentId;  //学生号 

	/**
	 * @return the attendId
	 */
	public Integer getAttendId() {
		return attendId;
	}

	/**
	 * @param attendId the attendId to set
	 */
	public void setAttendId(Integer attendId) {
		this.attendId = attendId;
	}



	/**
	 * @return the studentId
	 */
	public Integer getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	
	
	
}
