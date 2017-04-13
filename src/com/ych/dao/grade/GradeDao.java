package com.ych.dao.grade;

import java.util.List;

import com.ych.dao.BaseDao;
import com.ych.entity.Grade;

public interface GradeDao extends  BaseDao<Grade>{
	 public List<Grade> findByGrade(int gradeId);
}

