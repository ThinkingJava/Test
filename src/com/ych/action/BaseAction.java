package com.ych.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.ych.dao.attend.AttendDao;
import com.ych.dao.course.CourseDao;
import com.ych.dao.login.LoginDao;
import com.ych.dao.major.MajorDao;
import com.ych.dao.score.ScoreDao;
import com.ych.dao.student.StudentDao;
import com.ych.dao.student.StudentDaoImpl;
import com.ych.dao.teacher.TeacherDao;
import com.ych.entity.Teacher;



public class BaseAction extends ActionSupport implements RequestAware,
SessionAware, ApplicationAware {
	
	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	protected Integer[] ids;
	protected int pageNo = 1;
	protected int maxResult = 5;  //返回调数
	protected int pageSize = 3;
	
	public static final String LIST = "list";
	public static final String EDIT = "edit";
	public static final String ADD = "add";
	public static final String SELECT = "select";
	public static final String QUERY = "query";
	public static final String LOGINERROR="loginerror";
	
	public static final String TEACHER_LOGIN = "teacherLogin";
//	public static final String STUDENT_LOGIN = "studentLogin";
	
/*	// 获取用户id
	// 获取用户对象
	public Student getLoginStudent(){
		if(session.get("student") != null){
			return (Student) session.get("student");
		}
		return null;
	}*/
	// 获取管理员id
	// 获取管理员对象
	public Teacher getLoginTeacher(){
		if(session.get("teacher") != null){
			return (Teacher) session.get("teacher");
		}
		return null;
	}

	
	@Autowired
	protected StudentDao studentDao;
	@Autowired
	protected AttendDao attendDao;
	@Autowired
	protected TeacherDao teacherDao;
	@Autowired
	protected CourseDao courseDao;
	@Autowired
	protected ScoreDao scoreDao;
	@Autowired
	protected MajorDao majorDao;
	@Autowired
	protected LoginDao loginDao;
	
	
	// Map类型的request
	protected Map<String, Object> request;
	// Map类型的session
	protected Map<String, Object> session;
	// Map类型的application
	protected Map<String, Object> application;
	
	@Override
	public void setRequest(Map<String, Object> request) {
		// 获取Map类型的request赋值
		this.request = request;
	}
	@Override
	public void setApplication(Map<String, Object> application) {
		// 获取Map类型的application赋值
		this.application = application;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// 获取Map类型的session赋值
		this.session = session;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String list() throws Exception {
		return LIST;
	}
	
	public String edit() throws Exception {
		return EDIT;
	}
	
	public String add() throws Exception {
		return ADD;
	}
	public String select() throws Exception {
		return SELECT;
	}
	public String query() throws Exception {
		return QUERY;
	}
	// getter和settter方法
		public Integer[] getIds() {
			return ids;
		}
		public void setIds(Integer[] ids) {
			this.ids = ids;
		}
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public int getMaxResult() {
			return maxResult;
		}
		public void setMaxResult(int maxResult) {
			this.maxResult = maxResult;
		}
		
		
}
