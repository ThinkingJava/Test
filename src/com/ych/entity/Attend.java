package com.ych.entity;

/**
 * Attend entity. @author MyEclipse Persistence Tools
 */

public class Attend implements java.io.Serializable {

	// Fields

	private Integer attendId;
	private Integer studentId;
	private Integer gradeId;
	private Integer status;
	private String studentImage;

	// Constructors

	/** default constructor */
	public Attend() {
	}

	/** minimal constructor */
	public Attend(Integer attendId, Integer studentId) {
		this.attendId = attendId;
		this.studentId = studentId;
	}

	/** full constructor */
	public Attend(Integer attendId, Integer studentId, Integer gradeId,
			Integer status, String studentImage) {
		this.attendId = attendId;
		this.studentId = studentId;
		this.gradeId = gradeId;
		this.status = status;
		this.studentImage = studentImage;
	}

	// Property accessors

	public Integer getAttendId() {
		return this.attendId;
	}

	public void setAttendId(Integer attendId) {
		this.attendId = attendId;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStudentImage() {
		return this.studentImage;
	}

	public void setStudentImage(String studentImage) {
		this.studentImage = studentImage;
	}

}