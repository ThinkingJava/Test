package com.ych.dao.attend;

import java.util.List;

import com.ych.dao.BaseDao;
import com.ych.entity.Attend;

public interface AttendDao extends BaseDao<Attend>{
	public List<Attend> findByAttend(int attendId);
	public List<Attend> findByAttendCourse(String courseid);
	public List<Attend> findByAttendCourseToYesterday(String courseid);
	public int getCountByWhereToDay(String courseid);
	public int getCountByWhereYesterDay(String courseid);
	public boolean findIsExitAttend(String studentId,String courseId);
}
