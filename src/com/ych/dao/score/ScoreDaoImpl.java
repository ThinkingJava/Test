package com.ych.dao.score;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Major;
import com.ych.entity.Score;
@Repository("scoreDao")
@Transactional
public class ScoreDaoImpl extends DaoSupport<Score> implements ScoreDao{

	@Override
	public List<Score> findByScoretoStudent(int studentId) {
		// TODO Auto-generated method stub
		String where ="where studentid = ?";
	    Object []queryParams ={studentId};
	    List<Score> list=find(-1,-1,where,queryParams).getList();
		return list;
	}

	@Override
	public List<Score> findByScoretoCourse(int courseId) {
		// TODO Auto-generated method stub
		String where ="where courseid = ?";
	    Object []queryParams ={courseId};
	    List<Score> list=find(-1,-1,where,queryParams).getList();
		return list;
	}

	@Override
	public Score findByScore(int studentId, int courseId) {
		// TODO Auto-generated method stub
		String where ="where studentid = ? and courseid = ? ";
	    Object []queryParams ={studentId,courseId};
	    Score list=find(-1,-1,where,queryParams).getList().get(0);
		return list;
	}


}
