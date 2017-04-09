package com.ych.dao.attend;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Attend;
@Repository("attendDao")
@Transactional
public class AttendDaoImpl  extends DaoSupport<Attend> implements AttendDao{

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Attend> findByAttend(int attendId) {
		// TODO Auto-generated method stub
		String where = "where attend.attendId = ?";
		Object[] queryParams = {attendId};
		List<Attend> list = find(-1, -1, where, queryParams).getList();
		System.out.println("AttendDaoImpl--->>findByCustomer()");
		return list;
	}

}
