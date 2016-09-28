package com.ych.dao.studentGrade;

import com.ych.dao.BaseDao;
import com.ych.entity.StudentGrade;

public interface StudentGradeDao extends BaseDao<StudentGrade>{
    public StudentGrade findByStudentGrade(int studentGradeId);
}
