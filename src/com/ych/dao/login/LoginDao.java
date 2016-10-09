package com.ych.dao.login;

import com.ych.dao.BaseDao;
import com.ych.entity.Login;
import com.ych.entity.Teacher;


public interface LoginDao extends BaseDao<Login>{
	public Teacher login(String username, String password);
	public boolean isUnique(String username);
}
