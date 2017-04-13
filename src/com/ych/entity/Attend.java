package com.ych.entity;

import java.sql.Timestamp;

import net.sf.json.JSONObject;

/**
 * Attend entity. @author MyEclipse Persistence Tools
 */

public class Attend implements java.io.Serializable {

	// Fields

	private Integer attendid;
	private Course course;
	private Student student;
	private Integer status;
	private String imagepath;
	private Timestamp datatime;

	// Constructors

	/** default constructor */
	public Attend() {
	}

	/** minimal constructor */
	public Attend(Course course, Student student) {
		this.course = course;
		this.student = student;
	}

	/** full constructor */
	public Attend(Course course, Student student, Integer status,
			String imagepath, Timestamp datatime) {
		this.course = course;
		this.student = student;
		this.status = status;
		this.imagepath = imagepath;
		this.datatime = datatime;
	}

	// Property accessors

	public Integer getAttendid() {
		return this.attendid;
	}

	public void setAttendid(Integer attendid) {
		this.attendid = attendid;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getImagepath() {
		return this.imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public Timestamp getDatatime() {
		return this.datatime;
	}

	public void setDatatime(Timestamp datatime) {
		this.datatime = datatime;
	}
	
	public JSONObject toJSONObject() {
		
		JSONObject object = new JSONObject();
        object.put("attendid", attendid);
        object.put("course", course.toJSONObject());
        object.put("student", student.toJSONObject());
        object.put("status", status);
        object.put("imagepath", imagepath);
        object.put("datatime", datatime.toString());
		return object;

}

}