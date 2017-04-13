package com.ych.dao.teacher;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Teacher;
@Repository("teacherDao")
@Transactional
public class TeacherDaoImpl extends DaoSupport<Teacher> implements TeacherDao{

	@Override
	public List<Teacher> findByTeacher(int teacherId) {
		// TODO Auto-generated method stub
		String where = "where teacher.teacherId = ?";
		Object[] queryParams = {teacherId};
		List<Teacher> list = find(-1, -1, where, queryParams).getList();
		System.out.println("TeacherDaoImpl--->>findByTeacher()");
		return list;
	}

}

