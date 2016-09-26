package com.ych.entity;

import java.util.Set;

/**
 * Grade entity. @author MyEclipse Persistence Tools
 */

public class Grade implements java.io.Serializable {

	// Fields

	private Integer gradeId;
	private String name;
//	private Integer teacherId;
	private String number;
	private Integer hour; 
	
	private Teacher teacher;
	private Set<StudentGrade> studetGrade;

	// Constructors

	/** default constructor */
	public Grade() {
	}

	/** minimal constructor */
	public Grade(Integer gradeId, String name, Teacher teacher) {
		this.gradeId = gradeId;
		this.name = name;
		this.teacher = teacher;
	}

	/** full constructor */
	public Grade(Integer gradeId, String name, Teacher teacher,
			String number, Integer hour) {
		this.gradeId = gradeId;
		this.name = name;
		this.teacher = teacher;
		this.number = number;
		this.hour = hour;
	}
	
	

	// Property accessors

	public Integer getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getHour() {
		return this.hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}


	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Set<StudentGrade> getStudetGrade() {
		return studetGrade;
	}

	public void setStudetGrade(Set<StudentGrade> studetGrade) {
		this.studetGrade = studetGrade;
	}

	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", name=" + name + ", number="
				+ number + ", hour=" + hour + ", teacher=" + teacher
				+ ", studetGrade=" + studetGrade + "]";
	}
	
	

}