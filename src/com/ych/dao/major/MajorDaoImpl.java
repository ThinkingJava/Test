package com.ych.dao.major;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Major;
@Repository("majorDao")
@Transactional
public class MajorDaoImpl extends DaoSupport<Major> implements MajorDao{

	@Override
	public Major findByMajor(int majorId) {
		// TODO Auto-generated method stub
		String where ="where majorid = ?";
	    Object []queryParams ={majorId};
	    Major list=find(-1,-1,where,queryParams).getList().get(0);
		return list;
	}

}
