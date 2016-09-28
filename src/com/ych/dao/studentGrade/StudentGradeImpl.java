package com.ych.dao.studentGrade;

import java.util.List;

import com.ych.dao.DaoSupport;
import com.ych.entity.Student;
import com.ych.entity.StudentGrade;

public class StudentGradeImpl  extends DaoSupport<StudentGrade> implements StudentGradeDao{

	@Override
	public StudentGrade findByStudentGrade(int studentGradeId) {
		// TODO Auto-generated method stub
		String where = "where student_grade_id = ?";
		Object[] queryParams = {studentGradeId};
		System.out.println("---"+studentGradeId);
		List<StudentGrade> list = find(-1, -1, where, queryParams).getList();
		StudentGrade student=null;
		if(list.size()>0){
			student=list.get(0);
		}	
		System.out.println("StudentGradeImpl--->>findByCustomer()");
		return student;
	}

}
