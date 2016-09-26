package com.ych.entity;

/**
 * StudentGrade entity. @author MyEclipse Persistence Tools
 */

public class StudentGrade implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer studentGradeId;
	private Student student;
	private Grade grade;
	private Integer signnumber;

	// Constructors

	/** default constructor */
	public StudentGrade() {
	}

	/** minimal constructor */
	public StudentGrade(Integer studentGradeId, Student student,
			Grade grade) {
		this.studentGradeId = studentGradeId;
		this.student = student;
		this.grade = grade;
	}

	/** full constructor */
	public StudentGrade(Integer studentGradeId, Student student,
			Grade grade, Integer signnumber) {
		this.studentGradeId = studentGradeId;
		this.student = student;
		this.grade = grade;
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

	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "StudentGrade [studentGradeId=" + studentGradeId + ", student="
				+ student + ", grade=" + grade + ", signnumber=" + signnumber
				+ "]";
	}

	
	
}