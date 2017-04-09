package com.ych.entity;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher implements java.io.Serializable {

	// Fields

	private Integer teacherId;
	private String name;
	private Integer sex;
	private String department;
	private String teacherImage;

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** full constructor */
	public Teacher(String name, Integer sex, String department,
			String teacherImage) {
		this.name = name;
		this.sex = sex;
		this.department = department;
		this.teacherImage = teacherImage;
	}

	// Property accessors

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTeacherImage() {
		return this.teacherImage;
	}

	public void setTeacherImage(String teacherImage) {
		this.teacherImage = teacherImage;
	}

}