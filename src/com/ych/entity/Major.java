package com.ych.entity;

import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONObject;

/**
 * Major entity. @author MyEclipse Persistence Tools
 */

public class Major implements java.io.Serializable {

	// Fields

	private Integer majorid;
	private Teacher teacher;
	private String majorname;
	private Integer number;
	private Set students = new HashSet(0);

	// Constructors

	/** default constructor */
	public Major() {
	}

	/** minimal constructor */
	public Major(String majorname) {
		this.majorname = majorname;
	}

	/** full constructor */
	public Major(Teacher teacher, String majorname, Integer number, Set students) {
		this.teacher = teacher;
		this.majorname = majorname;
		this.number = number;
		this.students = students;
	}

	// Property accessors

	public Integer getMajorid() {
		return this.majorid;
	}

	public void setMajorid(Integer majorid) {
		this.majorid = majorid;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getMajorname() {
		return this.majorname;
	}

	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Set getStudents() {
		return this.students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}
	
	public JSONObject toJSONObject() {
		JSONObject object = new JSONObject();
		object.put("studentid", majorid);
		object.put("majorname", majorname);
		object.put("teacher", teacher.toJSONObject());
		object.put("number", number);
		return object;
	}

}