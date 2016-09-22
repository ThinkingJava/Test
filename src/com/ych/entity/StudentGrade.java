package com.ych.entity;

/**
 * StudentGrade entity. @author MyEclipse Persistence Tools
 */

public class StudentGrade implements java.io.Serializable {

	// Fields

	private Integer studentGradeId;
	private Integer studentId;
	private Integer gradeId;
	private Integer signnumber;

	// Constructors

	/** default constructor */
	public StudentGrade() {
	}

	/** minimal constructor */
	public StudentGrade(Integer studentGradeId, Integer studentId,
			Integer gradeId) {
		this.studentGradeId = studentGradeId;
		this.studentId = studentId;
		this.gradeId = gradeId;
	}

	/** full constructor */
	public StudentGrade(Integer studentGradeId, Integer studentId,
			Integer gradeId, Integer signnumber) {
		this.studentGradeId = studentGradeId;
		this.studentId = studentId;
		this.gradeId = gradeId;
		this.signnumber = signnumber;
	}

	// Property accessors

	public Integer getStudentGradeId() {
		return this.studentGradeId;
	}

	public void setStudentGradeId(Integer studentGradeId) {
		this.studentGradeId = studentGradeId;
	}

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getSignnumber() {
		return this.signnumber;
	}

	public void setSignnumber(Integer signnumber) {
		this.signnumber = signnumber;
	}

}