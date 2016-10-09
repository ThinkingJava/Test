package com.ych.dao.student;

import java.util.List;

import com.ych.dao.BaseDao;
import com.ych.entity.Student;

public interface StudentDao extends BaseDao<Student>{
	public Student findByStudent(String studentId);
}
