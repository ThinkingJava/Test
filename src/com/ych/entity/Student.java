package com.ych.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONObject;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private String studentid;
	private Major major;
	private String studentname;
	private Integer sex;
	private Timestamp datatime;
	private Integer score;
	private String remark;
	private String imagepath;
	private Set courses = new HashSet(0);
	private Set scores = new HashSet(0);
	private Set attends = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(Major major, String studentname) {
		this.major = major;
		this.studentname = studentname;
	}

	/** full constructor */
	public Student(Major major, String studentname, Integer sex,
			Timestamp datatime, Integer score, String remark, String imagepath,
			Set courses, Set scores, Set attends) {
		this.major = major;
		this.studentname = studentname;
		this.sex = sex;
		this.datatime = datatime;
		this.score = score;
		this.remark = remark;
		this.imagepath = imagepath;
		this.courses = courses;
		this.scores = scores;
		this.attends = attends;
	}

	// Property accessors

	public String getStudentid() {
		return this.studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public Major getMajor() {
		return this.major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public String getStudentname() {
		return this.studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Timestamp getDatatime() {
		return this.datatime;
	}

	public void setDatatime(Timestamp datatime) {
		this.datatime = datatime;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImagepath() {
		return this.imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}



	public Set getCourses() {
		return courses;
	}

	public void setCourses(Set courses) {
		this.courses = courses;
	}

	public Set getScores() {
		return this.scores;
	}

	public void setScores(Set scores) {
		this.scores = scores;
	}

	public Set getAttends() {
		return this.attends;
	}

	public void setAttends(Set attends) {
		this.attends = attends;
	}
    
	
	@Override
	public String toString() {
		return "Student [studentid=" + studentid + ", major=" + major
				+ ", studentname=" + studentname + ", sex=" + sex
				+ ", datatime=" + datatime + ", score=" + score + ", remark="
				+ remark + ", imagepath=" + imagepath + ", courses=" + courses
				+ ", scores=" + scores + ", attends=" + attends + "]";
	}

	public JSONObject toJSONObject() {
		JSONObject object = new JSONObject();
		object.put("studentid", studentid);
		object.put("major", major.toJSONObject());
		object.put("studentname", studentname);
		object.put("sex", sex);
		object.put("datatime", datatime);
		object.put("imagepath", imagepath);
		return object;
	}
}