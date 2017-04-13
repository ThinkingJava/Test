package com.ych.dao.course;

import com.ych.dao.BaseDao;
import com.ych.entity.Course;

public interface CourseDao  extends BaseDao<Course>{
	 public Course findByCourse(String courseId);

}
