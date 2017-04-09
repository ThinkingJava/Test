package com.ych.dao.student;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Student;

//@Repository("studentDao")
@Repository("studentDao")
@Transactional
public class StudentDaoImpl extends DaoSupport<Student> implements StudentDao {

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Student> findByStudent(int studentId) {
		// TODO Auto-generated method stub
		String where = "where student.studentId = ?";
		Object[] queryParams = {studentId};
		List<Student> list = find(-1, -1, where, queryParams).getList();
		System.out.println("StudentDaoImpl--->>findByCustomer()");
		return list;
	}

	
}
