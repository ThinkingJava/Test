package com.ych.entity;

import java.util.Set;

/**
 * Grade entity. @author MyEclipse Persistence Tools
 */

public class Grade implements java.io.Serializable {

	// Fields

	private Integer gradeId;
	private String name;
	private Integer teacherId;
	private String number;
	private Integer hour; 
	
/*	private Teacher teacher;
	private Set<StudentGrade> studetGrade;*/

	// Constructors

	/** default constructor */
	public Grade() {
	}

	/** minimal constructor */
	public Grade(Integer gradeId, String name, Integer teacherId) {
		this.gradeId = gradeId;
		this.name = name;
		this.teacherId = teacherId;
	}

	/** full constructor */
	public Grade(Integer gradeId, String name, Integer teacherId,
			String number, Integer hour) {
		this.gradeId = gradeId;
		this.name = name;
		this.teacherId = teacherId;
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

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	/* (非 Javadoc) 
	 * <p>Title:toString</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see java.lang.Object#toString() 
	 */ 
	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", name=" + name + ", teacherId="
				+ teacherId + ", number=" + number + ", hour=" + hour + "]";
	}


	
	

}