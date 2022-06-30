package com.ace.studentmanagement.dto;

public class CourseResponseDTO {
	private String courseId;
	private String courseName;
	public CourseResponseDTO() {
		
	}
	public CourseResponseDTO(String courseId, String courseName) {
		this.courseId = courseId;
		this.courseName = courseName;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
