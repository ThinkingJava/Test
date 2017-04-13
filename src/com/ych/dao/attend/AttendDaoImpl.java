<<<<<<< HEAD
package com.ych.dao.attend;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Attend;

@Repository("attendDao")
@Transactional
public class AttendDaoImpl  extends DaoSupport<Attend> implements AttendDao{

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Attend> findByAttend(int attendId) {
		// TODO Auto-generated method stub
		String where = "where attendid = ?";
		Object[] queryParams = {attendId};
		List<Attend> list = find(-1, -1, where, queryParams).getList();
		System.out.println("AttendDaoImpl--->>findByCustomer()");
		return list;
	}

	@Override
	public List<Attend> findByAttendCourse(String courseid) {
		// TODO Auto-generated method stub
		String where = "where courseid = ? and DATE(datatime) = CURDATE()";
		Object[] queryParams = {courseid};
		List<Attend> list = find(-1, -1, where, queryParams).getList();
		return list;
	}

	@Override
	public List<Attend> findByAttendCourseToYesterday(String courseid) {
		// TODO Auto-generated method stub
		String where = "where courseid = ? and DATE(datatime) = CURDATE() -1 ";
		Object[] queryParams = {courseid};
		List<Attend> list = find(-1, -1, where, queryParams).getList();
		return list;
	}

	@Override
	public int getCountByWhereToDay(String courseid) {
		// TODO Auto-generated method stub
		String where = "where courseid = ? and DATE(datatime) = CURDATE() ";
		Object[] queryParams = {courseid};
		
		return (int)getCountByWhere(where, queryParams);
	}

	@Override
	public int getCountByWhereYesterDay(String courseid) {
		// TODO Auto-generated method stub
		String where = "where courseid = ? and DATE(datatime) = CURDATE() -1 ";
		Object[] queryParams = {courseid};
		
		return (int)getCountByWhere(where, queryParams);
	}

	@Override
	public boolean findIsExitAttend(String studentId, String courseId) {
		// TODO Auto-generated method stub
		String where = "where courseid = ? and studentid = ? and DATE(datatime) = CURDATE() ";
		Object[] queryParams = {courseId,studentId};
		List<Attend> list =	find(-1, -1, where, queryParams).getList();
		if(list!=null&&list.size()>0)
			return true;
		
		return false;
	}
	
	
	
	

}
=======
<<<<<<< HEAD
package com.ych.dao.attend;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Attend;
@Repository("attendDao")
@Transactional
public class AttendDaoImpl  extends DaoSupport<Attend> implements AttendDao{

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Attend> findByAttend(int attendId) {
		// TODO Auto-generated method stub
		String where = "where attend.attendId = ?";
		Object[] queryParams = {attendId};
		List<Attend> list = find(-1, -1, where, queryParams).getList();
		System.out.println("AttendDaoImpl--->>findByCustomer()");
		return list;
	}

}
=======
package com.ych.dao.attend;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Attend;

@Repository("attendDao")
@Transactional
public class AttendDaoImpl  extends DaoSupport<Attend> implements AttendDao{

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Attend> findByAttend(int attendId) {
		// TODO Auto-generated method stub
		String where = "where attend.attendId = ?";
		Object[] queryParams = {attendId};
		List<Attend> list = find(-1, -1, where, queryParams).getList();
		System.out.println("AttendDaoImpl--->>findByCustomer()");
		return list;
	}

}
>>>>>>> eb63eded9899513af9538a187ddf701ead10b135
>>>>>>> 2d47266740bc805e9dd402192a7321ac5ee26525
