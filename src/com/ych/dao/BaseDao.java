package com.ych.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ych.model.PageModel;


public interface BaseDao<T> {
	//基本数据库操作方法
	public void save(Object obj);//保存数据
	public void saveOrUpdate(Object obj);//保存或修改数据
	public void update(Object obj);//修改数据
	public void delete(Object obj);//删除数据
	public T get(Serializable entityId);//加载实体对象
	public T load(Serializable entityId);//加载实体对象
	public Object uniqueResult(String hql, Object[] queryParams);//使用hql语句操作
	//加载更多
//	public List<T> findMore(int pageNo,int end);	
//	public List<T> findMore(int pageNo,int end,Map<String, String> orderby);  //按指定条件排序加载方法
    //加载全部
	public List<T> findAll(); //加载全部
	public List<T> findAll(Map<String, String> orderby);  //根据条件排序
	public List<T> findByCondition(String where, Object[] queryParams,Map<String, String> orderby);
	//分页操作
	public long getCount();//获取总信息数
	public PageModel<T> find(int pageNo, int maxResult);//普通分页操作
	public PageModel<T> find(int pageNo, int maxResult,String where, Object[] queryParams);//搜索信息分页方法
	public PageModel<T> find(int pageNo, int maxResult,Map<String, String> orderby);//按指定条件排序分页方法
	public PageModel<T> find(String where, Object[] queryParams,
			Map<String, String> orderby, int pageNo, int maxResult);//按指定条件分页和排序的分页方法
}
