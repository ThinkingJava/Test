package com.ych.entity;

/**
 * Score entity. @author MyEclipse Persistence Tools
 */

public class Score implements java.io.Serializable {

	// Fields

	private ScoreId id;
	private Course course;
	private Student student;
	private Float score;
	private Integer credit;

	// Constructors

	/** default constructor */
	public Score() {
	}

	/** minimal constructor */
	public Score(ScoreId id, Course course, Student student) {
		this.id = id;
		this.course = course;
		this.student = student;
	}

	/** full constructor */
	public Score(ScoreId id, Course course, Student student, Float score,
			Integer credit) {
		this.id = id;
		this.course = course;
		this.student = student;
		this.score = score;
		this.credit = credit;
	}

	// Property accessors

	public ScoreId getId() {
		return this.id;
	}

	public void setId(ScoreId id) {
		this.id = id;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Float getScore() {
		return this.score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

}