package com.ych.dao.login;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ych.dao.DaoSupport;
import com.ych.entity.Login;
import com.ych.entity.Teacher;
@Repository("loginDao")
@Transactional
public class LoginDaoImpl extends DaoSupport<Login> implements LoginDao{

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Teacher login(String username, String password) {
		// TODO Auto-generated method stub
		if(username != null && password != null){//如果用户名和密码不为空
			String where = "where name=? and password=?";//设置查询条件
			Object[] queryParams = {username,password};//设置参数对象数组
			List<Login> list = find(-1, -1, where, queryParams).getList();//执行查询方法
			if(list != null && list.size() > 0){//如果list集合不为空
				return list.get(0).getTeacher();//返回List中的第一个存储对象
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean isUnique(String username) {
		// TODO Auto-generated method stub
		List list = getTemplate().find("from login where name = ?", username);
		if(list != null && list.size() > 0){
			return false;
		}
		return false;
	}

	@Override
	public Login findByLogin(String teacherid) {
		// TODO Auto-generated method stub
		String where ="where teacherid = ?";
	    Object []queryParams ={teacherid};
	    Login list=find(-1,-1,where,queryParams).getList().get(0);
		return list;
	}

	
}
