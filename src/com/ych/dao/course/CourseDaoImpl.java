package com.ych.dao.course;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Course;
@Repository("courseDao")
@Transactional
public class CourseDaoImpl extends DaoSupport<Course> implements CourseDao{

	@Override
	public Course findByCourse(String courseId) {
		// TODO Auto-generated method stub
		String where ="where courseid = ?";
	    Object []queryParams ={courseId};
	 List<Course> tempList = find(-1,-1,where,queryParams).getList();
	 if(tempList!=null&&tempList.size()>0)
		 return tempList.get(0);
	 
		return null;
	}



}
