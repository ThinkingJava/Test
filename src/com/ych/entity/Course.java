package com.ych.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private String courseid;
	private Teacher teacher;
	private String coursename;
	private Short semester;
	private Integer hours;
	private Integer number;
	private Integer credit;
	private Set attends = new HashSet(0);
	private Set students = new HashSet(0);
	private Set scores = new HashSet(0);

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** full constructor */
	public Course(Teacher teacher, String coursename, Short semester,
			Integer hours, Integer number, Integer credit, Set attends,
			Set students, Set scores) {
		this.teacher = teacher;
		this.coursename = coursename;
		this.semester = semester;
		this.hours = hours;
		this.number = number;
		this.credit = credit;
		this.attends = attends;
		this.students = students;
		this.scores = scores;
	}

	// Property accessors

	public String getCourseid() {
		return this.courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getCoursename() {
		return this.coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public Short getSemester() {
		return this.semester;
	}

	public void setSemester(Short semester) {
		this.semester = semester;
	}

	public Integer getHours() {
		return this.hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Set getAttends() {
		return this.attends;
	}

	public void setAttends(Set attends) {
		this.attends = attends;
	}

	public Set getStudents() {
		return this.students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}

	public Set getScores() {
		return this.scores;
	}

	public void setScores(Set scores) {
		this.scores = scores;
	}

}