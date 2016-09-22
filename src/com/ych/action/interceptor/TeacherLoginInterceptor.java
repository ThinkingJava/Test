package com.ych.action.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.ych.action.BaseAction;

public class TeacherLoginInterceptor extends AbstractInterceptor{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		// 获取ActionContext
		ActionContext context = invocation.getInvocationContext();
		// 获取Map类型的session
		Map<String, Object> session = context.getSession();
		// 判断用户是否登录 
		if(session.get("teacher") != null){
			// 调用执行方法
			return invocation.invoke();
		}
		// 返回登录
		return BaseAction.TEACHER_LOGIN;
	}

}
