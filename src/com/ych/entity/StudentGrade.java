package com.ych.entity;

/**
 * StudentGrade entity. @author MyEclipse Persistence Tools
 */

public class StudentGrade implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
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
	public StudentGrade(Integer studentGradeId, Integer student,
			Integer gradeId, Integer signnumber) {
		this.studentGradeId = studentGradeId;
		this.studentId = student;
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
	public Integer getSignnumber() {
		return this.signnumber;
	}

	public void setSignnumber(Integer signnumber) {
		this.signnumber = signnumber;
	}

	
	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


	public Integer getGradeId() {
		return gradeId;
	}


	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	
	@Override
	public String toString() {
		return "StudentGrade [studentGradeId=" + studentGradeId
				+ ", studentId=" + studentId + ", gradeId=" + gradeId
				+ ", signnumber=" + signnumber + "]";
	}


	
	
}