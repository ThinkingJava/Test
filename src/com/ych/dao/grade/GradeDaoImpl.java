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
	public Grade findByGrade(int gradeId) {
		// TODO Auto-generated method stub
		String where ="where grade_id = ?";
	    Object []queryParams ={gradeId};
	    Grade list=find(-1,-1,where,queryParams).getList().get(0);
		return list;
	}

}
