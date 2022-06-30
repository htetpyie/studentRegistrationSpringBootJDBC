package com.ace.studentmanagement.dto;

public class StudentDTO {
	private String studentId;
	private String studentName;
	private String studentDob;
	private String studentGender;
	private	String studentPhone;
	private String studentEducation;
	private String studentPhoto;
	
	public StudentDTO() {
		
	}
	
	public StudentDTO(String studentId, String studentName,String studentDob, String studentGender
			,String studentPhone, String studentEducation, String studentPhoto)
	{
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentDob = studentDob;
		this.studentGender = studentGender;
		this.studentPhone = studentPhone;
		this.studentEducation = studentEducation;
		this.studentPhoto = studentPhoto;
		
	}
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentDob() {
		return studentDob;
	}
	public void setStudentDob(String studentDob) {
		this.studentDob = studentDob;
	}
	public String getStudentGender() {
		return studentGender;
	}
	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	public String getStudentPhone() {
		return studentPhone;
	}
	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}
	public String getStudentEducation() {
		return studentEducation;
	}
	public void setStudentEducation(String studentEducation) {
		this.studentEducation = studentEducation;
	}
	public String getStudentPhoto() {
		return studentPhoto;
	}
	public void setStudentPhoto(String studentPhoto) {
		this.studentPhoto = studentPhoto;
	}
}
