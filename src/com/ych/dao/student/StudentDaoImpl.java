package com.ych.dao.student;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Student;

@Repository("studentDao")
@Transactional
public class StudentDaoImpl extends DaoSupport<Student> implements StudentDao {

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Student findByStudent(String studentId) {
		// TODO Auto-generated method stub
		String where = "where studentid = ?";
		Object[] queryParams = {studentId};
		List<Student> list = find(-1, -1, where, queryParams).getList();
		Student student=null;
		if(list.size()>0){
			student=list.get(0);
		}
			
//		System.out.println("StudentDaoImpl--->>findByCustomer()");
		return student;
	}

	
}
