package com.ych.entity;

/**
 * Login entity. @author MyEclipse Persistence Tools
 */

public class Login implements java.io.Serializable {

	// Fields

	private String teacherid;
	private Teacher teacher;
	private String name;
	private String password;

	// Constructors

	/** default constructor */
	public Login() {
	}

	/** full constructor */
	public Login(Teacher teacher, String name, String password) {
		this.teacher = teacher;
		this.name = name;
		this.password = password;
	}

	// Property accessors

	public String getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}