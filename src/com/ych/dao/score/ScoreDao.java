package com.ych.dao.score;

import java.util.List;

import com.ych.dao.BaseDao;
import com.ych.entity.Score;

public interface ScoreDao extends BaseDao<Score>{
	public List<Score> findByScoretoStudent(int studentId);
	public List<Score> findByScoretoCourse(int courseId);
	public Score findByScore(int studentId,int courseId);
}
