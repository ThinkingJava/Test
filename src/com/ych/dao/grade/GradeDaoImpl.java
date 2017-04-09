package com.ych.dao.grade;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Grade;

@Repository("gradeDao")
@Transactional
public class GradeDaoImpl extends DaoSupport<Grade> implements GradeDao{

	@Override
	public List<Grade> findByGrade(int gradeId) {
		// TODO Auto-generated method stub
		String where ="where grade.gradeId = ?";
	    Object []queryParams ={gradeId};
	    List<Grade> list=find(-1,-1,where,queryParams).getList();
		return list;
	}

}
