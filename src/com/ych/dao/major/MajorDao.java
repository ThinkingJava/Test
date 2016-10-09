package com.ych.dao.major;

import com.ych.dao.BaseDao;
import com.ych.entity.Major;

public interface MajorDao extends BaseDao<Major>{
	public Major findByMajor(int majorId);

}
