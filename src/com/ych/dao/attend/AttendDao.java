package com.ych.dao.attend;

import java.util.List;

import com.ych.dao.BaseDao;
import com.ych.entity.Attend;

public interface AttendDao extends BaseDao<Attend>{
	public List<Attend> findByAttend(int attendId);
}
