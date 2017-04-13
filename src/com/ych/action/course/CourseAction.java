package com.ych.action.course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ych.action.BaseAction;
import com.ych.entity.Course;
import com.ych.entity.Student;
import com.ych.entity.Teacher;
import com.ych.model.PageModel;
import com.ych.util.ConstUtil;
import com.ych.util.ResultUtils;

/**
 * 
 * ClassName: CourseAction
 * 
 * @Description: TODO
 * @author 杨城欢
 * @date 2016年10月1日
 */
@Scope("prototype")
@Controller("courseAction")
public class CourseAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Course course;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getAllCourseAPI() throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, String>> center = new ArrayList<Map<String, String>>();
		PageModel<Course> pagemodel = null;
		// JsonConfig jsonConfig = new JsonConfig(); //建立配置文件
		try {
			pagemodel = courseDao.findAll();
			Iterator<Course> it = pagemodel.getList().iterator();
			while (it.hasNext()) {
				Course tempCourse = it.next();
				Map<String, String> tempMap = new HashMap<String, String>();
				tempMap.put("courseid", tempCourse.getCourseid());
				tempMap.put("coursename", tempCourse.getCoursename());
				tempMap.put("semester", tempCourse.getSemester()+"");
				tempMap.put("credit", tempCourse.getCredit() + "");
				tempMap.put("hours", tempCourse.getHours() + "");
				tempMap.put("number", tempCourse.getNumber() + "");
				tempMap.put("teacher", tempCourse.getTeacher().toJSONObject()+"");
				center.add(tempMap);
			}

			// jsonConfig.setIgnoreDefaultExcludes(false); //设置默认忽略
			// jsonConfig.setExcludes(new
			// String[]{"attends","students","scores","teacher"});
			// //此处是亮点，只要将所需忽略字段加到数组中即可，在上述案例中，所要忽略的是“libs”，那么将其添到数组中即可，在实际测试中，我发现在所返回数组中，存在大量无用属性，如“multipartRequestHandler”，“servletWrapper”，那么也可以将这两个加到忽略数组中.
			map.put("course", center);
			map.put("totalRecords", pagemodel.getList().size());
			map.put("status", "SUCCESS");
			map.put("message", "数据库查询正确");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "FALSE");
			map.put("message", "数据库查询失败");
		}

		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	/**
	 * 
	 * @Description: TODO获取课程的所有学生
	 * @param @return
	 * @param @throws IOException   
	 * @return String  
	 * @throws
	 * @author 杨城欢
	 * @date 2016年11月2日
	 */

	public String getCourseStudentAPI() throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(course.getCourseid())){
			try{
		Course cou=courseDao.findByCourse(course.getCourseid());
		if(cou!=null){
//			PageModel<Student> pageModel = new PageModel<Student>();
//			pageModel.setPageNo(pageNo);
//			pageModel.setPageSize(pageSize);
			List<Map<String,String>> tempList=new ArrayList<Map<String,String>>();
			Iterator<Student> it=cou.getStudents().iterator();
			while(it.hasNext()){
			Map<String,String> tempStu=new HashMap<String, String>();	
			Student st=	it.next();
			tempStu.put("studentid", st.getStudentid());
			tempStu.put("studentname", st.getStudentname());
			tempStu.put("majorid", st.getMajor().getMajorid()+"");
			tempStu.put("imagepath", st.getImagepath());
			tempStu.put("sex", st.getSex()+"");
			tempList.add(tempStu);
			}
			
			map.put("student", tempList);
			map.put("totalRecords", tempList.size());
			map.put("status", "SUCCESS");
			map.put("message", "数据库查询正确");
		}
		}catch (Exception e) {
			e.printStackTrace();		
			map.put("status", "FALSE");
			map.put("message", "数据库查询失败");
			}
		}else{
			map.put("status", "FALSE");
			map.put("message", "参数出错");	
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	public String getOneCourseAPI() throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		Course tempCourse = null;
		if (course.getCourseid() != null) {
			try {
				tempCourse = courseDao.findByCourse(course.getCourseid());
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("courseid", tempCourse.getCourseid());
				tempMap.put("coursename", tempCourse.getCoursename());
				tempMap.put("credit", tempCourse.getCredit() + "");
				tempMap.put("hours", tempCourse.getHours() + "");
				tempMap.put("number", tempCourse.getNumber() + "");
				tempMap.put("teacher", tempCourse.getTeacher().toJSONObject());
				map.put("course", tempMap);
				map.put("status", "SUCCESS");
			} catch (Exception e) {
				map.put("status", "FALSE");
				map.put("message", "数据库查询失败");
			}
		} else {
			map.put("status", "FALSE");
			map.put("messge", "参数错误");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}

	public String getOneCourseStudentAPI() throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		Course tempCourse = null;
		if (course.getCourseid() != null) {
			try {
				List<Map<String, String>> tempList = new ArrayList<Map<String, String>>();
				tempCourse = courseDao.findByCourse(course.getCourseid());
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("courseid", tempCourse.getCourseid());
				tempMap.put("coursename", tempCourse.getCoursename());
				tempMap.put("credit", tempCourse.getCredit() + "");
				tempMap.put("hours", tempCourse.getHours() + "");
				tempMap.put("number", tempCourse.getNumber() + "");
				tempMap.put("teacher", tempCourse.getTeacher().toJSONObject());
				// tempMap.put("student", tempCourse.getStudents())
				Iterator<Student> it = tempCourse.getStudents().iterator();
				while (it.hasNext()) {
					Map<String, String> tempStudent = new HashMap<String, String>();
					Student student = it.next();
					tempStudent.put("studentid", student.getStudentid());
					tempStudent.put("studentname", student.getStudentname());
					tempStudent.put("score", student.getScore() + "");
					tempStudent.put("remark", student.getRemark());
					tempStudent.put("imagepath", student.getImagepath());
					tempStudent.put("sex", student.getSex() + "");
					tempList.add(tempStudent);
				}
				tempMap.put("student", tempList);
				tempMap.put("totalRecords", tempList.size());
				map.put("course", tempMap);
				map.put("status", "SUCCESS");
			} catch (Exception e) {
				map.put("status", "FALSE");
				map.put("message", "数据库查询失败");
			}
		} else {
			map.put("status", "FALSE");
			map.put("messge", "参数错误");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
/**
 * 
 * @Description: 添加班级
 * @param @return
 * @param @throws IOException   
 * @return String  
 * @throws
 * @author 杨城欢
 * @date 2016年11月18日
 */
	public String addCourseAPI() throws IOException {
		Map<String, String> map = new HashMap<String, String>();

		if (course.getCourseid() != null && course.getHours() != null
				&& course.getNumber() != null && course.getTeacher() != null
				&& course.getNumber() != null) {
			try {
				courseDao.save(course);
				map.put("status", "SUCCESS");
				map.put("message", "添加成功");
			} catch (Exception e) {
				map.put("status", "FALSE");
				map.put("messge", "添加失败");
			}
		} else {
			map.put("status", "FALSE");
			map.put("messge", "参数错误");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}

	public String updateCourseAPI() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		if (course.getCourseid() != null && course.getHours() != null
				&& course.getNumber() != null && course.getTeacher() != null
				&& course.getNumber() != null) {
			try {
				courseDao.update(course);
				map.put("status", "SUCCESS");
				map.put("message", "更改成功");
			} catch (Exception e) {
				map.put("status", "FALSE");
				map.put("messge", "更改失败");
			}
		} else {
			map.put("status", "FALSE");
			map.put("messge", "参数错误");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	public String addCourseStudentAPI() throws IOException{
		if(course.getCourseid()!=null&&studentid!=null){
			Student student = studentDao.findByStudent(studentid);
			if(student!=null){
			Course	tempCourse = courseDao.findByCourse(course.getCourseid());
			if(tempCourse!=null){
				boolean isInclude=true;
				// List<Student> list=new
				// ArrayList<Student>(course.getStudents());
			Iterator<Student> itStudent=	tempCourse.getStudents().iterator();
			while(itStudent.hasNext()){
				Student tempStudent=itStudent.next();
				if(tempStudent.getStudentid().equals(studentid)){
					isInclude=false;
				}
			}
			if(isInclude){
				Set<Student> set = tempCourse.getStudents();
				set.add(student);
				tempCourse.setStudents(set);
				tempCourse.setNumber(tempCourse.getNumber()+1);
				try{
				courseDao.saveOrUpdate(tempCourse);
				map.put("status", ConstUtil.SUCCESS);
				map.put("message", "添加成功");	
				}catch(Exception e){
					e.printStackTrace();
					map.put(ConstUtil.status, ConstUtil.FALSE);
					map.put("message", "保存出错");		
				}
			}else{
				map.put(ConstUtil.status, ConstUtil.FALSE);
				map.put("message", "该学生已存在");	
			}
			}else{
				map.put(ConstUtil.status, ConstUtil.FALSE);
				map.put("message", "不存在该课程");		
			}
			}else{
				map.put(ConstUtil.status, ConstUtil.FALSE);
				map.put("message", "不存在该学生");	
			}
		}else{
			map.put(ConstUtil.status, ConstUtil.FALSE);
			map.put("message", "参数错误");	
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	public String deleteCourseStudentAPI() throws Exception {
		map.clear();
		if(course.getCourseid()!=null&&studentid!=null){
			Student student = studentDao.findByStudent(studentid);
			if(student!=null){
			Course	tempCourse = courseDao.findByCourse(course.getCourseid());
			if(tempCourse!=null){
				boolean isInclude=true;
				// List<Student> list=new
				// ArrayList<Student>(course.getStudents());
			Iterator<Student> itStudent=	tempCourse.getStudents().iterator();
			while(itStudent.hasNext()){
				Student tempStudent=itStudent.next();
				if(tempStudent.getStudentid().equals(studentid)){
					isInclude=false;
				}
			}
			if(!isInclude){
				Set<Student> set = tempCourse.getStudents();
				set.remove(student);
				tempCourse.setStudents(set);
				try{
			tempCourse.setNumber(tempCourse.getNumber()-1);  //班级人数减1
				courseDao.saveOrUpdate(tempCourse);
				map.put("status", ConstUtil.SUCCESS);
				map.put("message", "删除成功");	
				}catch(Exception e){
					e.printStackTrace();
					map.put(ConstUtil.status, ConstUtil.FALSE);
					map.put("message", "删除出错");		
				}
			}else{
				map.put(ConstUtil.status, ConstUtil.FALSE);
				map.put("message", "该学生不存在该班级");	
			}
			}else{
				map.put(ConstUtil.status, ConstUtil.FALSE);
				map.put("message", "不存在该课程");		
			}
			}else{
				map.put(ConstUtil.status, ConstUtil.FALSE);
				map.put("message", "不存在该学生");	
			}
		}else{
			map.put(ConstUtil.status, ConstUtil.FALSE);
			map.put("message", "参数错误");	
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
/**
 * 删除课程
 * @Description: TODO
 * @param @return
 * @param @throws IOException   
 * @return String  
 * @throws
 * @author 杨城欢
 * @date 2016年11月8日
 */
	public String deleteCourseAPI() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		if (course.getCourseid() != null) {
			try {
				courseDao.deleteById(course.getCourseid());
				map.put("status", "SUCCESS");
				map.put("message", "更改成功");
			} catch (Exception e) {
				map.put("status", "FALSE");
				map.put("messge", "更改失败");
			}
		} else {
			map.put("status", "FALSE");
			map.put("messge", "参数错误");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return null;
	}

	public String courseMessage() {
   Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
	if(teacher!=null)
	return SUCCESS;
	else
    return LOGIN;
	}

	public String teacherCourse() {
   Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
      if(teacher!=null&&course.getCourseid()!=null){
		map.clear();
		try {

		Course	coursetemp = courseDao.findByCourse(course.getCourseid());
		if(coursetemp!=null){
			List<Student> list = new ArrayList<Student>(coursetemp.getStudents());
           if(list.size()!=0){
			pageModel = new PageModel<Student>();// 实例化分页对象;
			pageModel.setPageNo(pageNo);// 设置当前页数
			pageModel.setPageSize(4);// 设置每页显示记录数
			pageModel.setTotalRecords(list.size());// 设置总记录数
			List<Student> temp = null;
//			int count=0;
//			if (pageNo * 4 > list.size()){
//				count = list.size()/4;
//				temp = list.subList(count*4, list.size());
//				pageModel.setPageSize(4-(pageNo * 4 - list.size()));
//			}
//			else
//				temp = list.subList((pageNo - 1) * 4, pageNo * 4);
//			pageModel.setList(temp);// 将查询的list对象放入实体对象中
			
			if ((pageNo - 1) * 4 == list.size()) {
				int page = pageNo - 2 > 0 ? pageNo - 2 : 0;
				temp = list.subList((pageNo - 2) * 4, list.size());
				pageModel.setPageNo(pageNo - 1);
			} else if (pageNo * 4 > list.size()){
				temp = list.subList((pageNo - 1) * 4, list.size());
				pageModel.setPageSize(4);
			}
			else
				temp = list.subList((pageNo - 1) * 4, pageNo * 4);
			pageModel.setList(temp);// 将查询的list对象放入实体对象中
			
			map.put("status", "0");
			map.put("message", "信息获取成功");
			map.put("courseid", course.getCourseid());
           }
		}else{
			map.put("status", "3");
			map.put("message", "不存在该课程");
			map.put("courseid", course.getCourseid());
		}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "3");
			map.put("message", "信息获取失败");
			map.put("courseid", course.getCourseid());
			return ERROR;
		}
		return SUCCESS;
      }else{
    	  return LOGIN;
      }
	}

	public String addCourseStudentHead() {
     Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
	 if(teacher!=null){
	 map.clear();
	 map.put("courseid", course.getCourseid());
	 return SUCCESS;
	 }else
   	return LOGIN;
		      
	}

	public String addCourseStudent() {
		   Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
		      if(teacher!=null){
		map.clear();
		if(studentid!=null&&course.getCourseid()!=null){
		try {
			
			Student student = studentDao.findByStudent(studentid);
			if (student != null) {
				
				course = courseDao.findByCourse(course.getCourseid());
				if(course!=null){
				// List<Student> list=new
				// ArrayList<Student>(course.getStudents());
				Set<Student> set = course.getStudents();
				set.add(student);
				course.setStudents(set);
				course.setNumber(course.getNumber()+1);
				courseDao.saveOrUpdate(course);
				map.put("status", ConstUtil.add + "");
				map.put("message", "添加成功");
			    map.put("courseid", course.getCourseid());
				}else{
					map.put("status", "5");
					map.put("message", "不存在该课程");
				    map.put("courseid", course.getCourseid());
				}
			} else {
				map.put("status", "5");
				map.put("message", "不存在该学生");
				map.put("courseid", course.getCourseid());

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status",  "5");
			map.put("message", "插入数据库失败");
			map.put("courseid", course.getCourseid());
		}
		}
		return SUCCESS;
	  }else
		return LOGIN;    	 
	}

	public String deleteCourseStudent() {
		 Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
		 if(teacher!=null){
		if (org.apache.commons.lang.StringUtils.isNotEmpty(studentid+"")&& org.apache.commons.lang.StringUtils.isNotEmpty(course.getCourseid()+"")) {
			boolean isdel = false;
			course = courseDao.findByCourse(course.getCourseid());
			List<Student> list = new ArrayList<Student>(course.getStudents());
			Iterator<Student> it = list.iterator();
			while (it.hasNext()) {
				Student st = it.next();
				if (st.getStudentid().equals(studentid)) {
					it.remove();
					isdel = true;
				}
			}
			if (isdel) {
				course.setStudents(new HashSet<Student>(list));
				course.setNumber(course.getNumber()-1);
			}
			try {

				courseDao.saveOrUpdate(course);
				map.clear();		
				map.put("status", "2");
				map.put("message", "删除学生成功!!!");
				map.put("courseid", course.getCourseid());
			} catch (Exception e) {
				e.printStackTrace();
		
				map.put("status", "3");
				map.put("message", "删除学生失败!!!");
				map.put("courseid", course.getCourseid());
				return ERROR;
			}
			try {

				pageModel = new PageModel<Student>();// 实例化分页对象;
				pageModel.setPageNo(pageNo);// 设置当前页数
				pageModel.setPageSize(4);// 设置每页显示记录数
				pageModel.setTotalRecords(list.size());// 设置总记录数
				maxResult=list.size();
				List<Student> temp = null;
				if(list.size()!=0||(pageNo - 1) * 4!=0){
				if ((pageNo - 1) * 4 == list.size()) {
					int page = pageNo - 2 > 0 ? pageNo - 2 : 0;
					temp = list.subList((pageNo - 2) * 4, list.size());
					pageModel.setPageNo(pageNo - 1);
				} else if (pageNo * 4 > list.size()){
					temp = list.subList((pageNo - 1) * 4, list.size());
					pageModel.setPageSize(4);
				}
				else
					temp = list.subList((pageNo - 1) * 4, pageNo * 4);
				}else{
					temp = new ArrayList<Student>();
				}
				pageModel.setList(temp);// 将查询的list对象放入实体对象中

			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
		}
	return SUCCESS;
    }else
    return LOGIN;
	}

	public String attendCourse() {
	 Teacher teacher = (Teacher)ServletActionContext.getRequest().getSession().getAttribute("teacher");
     if(teacher!=null){
	  return SUCCESS;
      }else
      return LOGIN;
	}

	private String studentid;

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	private PageModel<Student> pageModel = new PageModel<Student>();// 实例化分页对象;//
																	// 分页组件

	public PageModel<Student> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<Student> pageModel) {
		this.pageModel = pageModel;
	}

	Map<String, Object> map = new HashMap<String, Object>();;

	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
