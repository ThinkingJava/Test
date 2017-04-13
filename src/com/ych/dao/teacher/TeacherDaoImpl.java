<<<<<<< HEAD
package com.ych.dao.teacher;

import org.springframework.stereotype.Repository;
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
=======
<<<<<<< HEAD
package com.ych.dao.teacher;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Teacher;
@Repository("teacherdao")
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
=======
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
>>>>>>> eb63eded9899513af9538a187ddf701ead10b135
>>>>>>> 2d47266740bc805e9dd402192a7321ac5ee26525
