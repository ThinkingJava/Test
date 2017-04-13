package com.ych.entity;

/**
 * ScoreId entity. @author MyEclipse Persistence Tools
 */

public class ScoreId implements java.io.Serializable {

	// Fields

	private String studentid;
	private String courseid;

	// Constructors

	/** default constructor */
	public ScoreId() {
	}

	/** full constructor */
	public ScoreId(String studentid, String courseid) {
		this.studentid = studentid;
		this.courseid = courseid;
	}

	// Property accessors

	public String getStudentid() {
		return this.studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getCourseid() {
		return this.courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ScoreId))
			return false;
		ScoreId castOther = (ScoreId) other;

		return ((this.getStudentid() == castOther.getStudentid()) || (this
				.getStudentid() != null && castOther.getStudentid() != null && this
				.getStudentid().equals(castOther.getStudentid())))
				&& ((this.getCourseid() == castOther.getCourseid()) || (this
						.getCourseid() != null
						&& castOther.getCourseid() != null && this
						.getCourseid().equals(castOther.getCourseid())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStudentid() == null ? 0 : this.getStudentid().hashCode());
		result = 37 * result
				+ (getCourseid() == null ? 0 : this.getCourseid().hashCode());
		return result;
	}

}