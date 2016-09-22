package com.ych.entity;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer studentId;
	private String name;
	private Integer sex;
	private Integer age;
	private String department;
	private String studentImage;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String name) {
		this.name = name;
	}

	/** full constructor */
	public Student(String name, Integer sex, Integer age, String department,
			String studentImage) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.department = department;
		this.studentImage = studentImage;
	}

	// Property accessors

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
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

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStudentImage() {
		return this.studentImage;
	}

	public void setStudentImage(String studentImage) {
		this.studentImage = studentImage;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", sex="
				+ sex + ", age=" + age + ", department=" + department
				+ ", studentImage=" + studentImage + "]";
	}
	
	

}