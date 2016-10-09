package com.ych.dao.course;

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
	    Course list=find(-1,-1,where,queryParams).getList().get(0);
		return list;
	}

}
