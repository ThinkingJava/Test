package com.ych.dao.teacher;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Teacher;
@Repository("teacherDao")
@Transactional
public class TeacherDaoImpl extends DaoSupport<Teacher> implements TeacherDao{

	@Override
	public Teacher findByTeacher(String teacherId) {
		// TODO Auto-generated method stub
		String where = "where teacherid = ?";
		Object[] queryParams = {teacherId};
		Teacher list = find(-1, -1, where, queryParams).getList().get(0);
		System.out.println("TeacherDaoImpl--->>findByTeacher()");
		return list;
	}

	
	

}
