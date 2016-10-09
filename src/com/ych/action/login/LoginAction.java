package com.ych.action.login;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.ych.action.BaseAction;
import com.ych.entity.Login;
import com.ych.entity.Teacher;

@Scope("prototype")
@Controller("loginAction")
public class LoginAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	private Login login=new Login();
	private String validateCode;

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String login() throws IOException{
		
	Teacher teacher = loginDao.login(login.getName(), login.getPassword());
	String validateCodesession = (String) ServletActionContext.getRequest().getSession().getAttribute("validateCode");
   System.out.println(validateCode+":"+validateCodesession);
	if(!validateCode.equals(validateCodesession)){
		return "validateErr";
	}
		if(teacher!=null){
			session.put("teacher", teacher);//将管理员信息保存在Session对象中
			ServletActionContext.getRequest().getSession().setAttribute("teacher", teacher);
			return SUCCESS;
		}else{
			return LOGINERROR;
		}
		
	}
}
