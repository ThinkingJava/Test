<<<<<<< HEAD
package com.ych.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ych.dao.student.StudentDao;
import com.ych.entity.Student;
import com.ych.model.PageModel;

/**
 * 测试类
 * @author CHUNBIN
 *
 */
public class DaoSupportTest {
	private DaoSupport<Student> daoSupport;//DAO

	@Before
	public void setUp() throws Exception {//初始化方法，最先执行的方法
		daoSupport = new DaoSupport<Student>();

	}
	@After
	public void tearDown() throws Exception {//清理方法，最后执行的方法
		daoSupport=null;//销毁对象
	}
	@Test
	public void testFindIntInt() {
		Map<String, String> orderby = new HashMap<String, String>(1);//定义Map集合
		orderby.put("studentId", "desc");//设置按创建时间倒序排列
		PageModel<Student> pageModel = daoSupport.find(0, 2);//执行查询方法
		assertNotNull("无法获取数据！",pageModel);//判断find()方法是否成功查询到值
	}
}
=======
package com.ych.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ych.dao.student.StudentDao;
import com.ych.entity.Student;
import com.ych.model.PageModel;

/**
 * 测试类
 * @author CHUNBIN
 *
 */
public class DaoSupportTest {
	private DaoSupport<Student> daoSupport;//DAO

	@Before
	public void setUp() throws Exception {//初始化方法，最先执行的方法
		daoSupport = new DaoSupport<Student>();

	}
	@After
	public void tearDown() throws Exception {//清理方法，最后执行的方法
		daoSupport=null;//销毁对象
	}
	@Test
	public void testFindIntInt() {
		Map<String, String> orderby = new HashMap<String, String>(1);//定义Map集合
		orderby.put("studentId", "desc");//设置按创建时间倒序排列
		PageModel<Student> pageModel = daoSupport.find(0, 2);//执行查询方法
		assertNotNull("无法获取数据！",pageModel);//判断find()方法是否成功查询到值
	}
}
>>>>>>> eb63eded9899513af9538a187ddf701ead10b135
