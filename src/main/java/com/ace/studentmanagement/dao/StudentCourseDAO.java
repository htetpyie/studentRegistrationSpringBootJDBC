
  package com.ace.studentmanagement.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ace.studentmanagement.dto.CourseResponseDTO;
import com.ace.studentmanagement.dto.StudentResponseDTO; 
 
  
  @Service("StudentCourseDAO") 
  public class StudentCourseDAO {  
	  @Autowired
	  private JdbcTemplate jdbcTemplate;
	  public int insertStudentCourse(String studentId, String courseId) { 
		  int result = 0; 
		  String sql = "Insert into student_course(student_id, course_id) values(?,?)"; 
		  result = jdbcTemplate.update(sql, studentId, courseId);
		  return result; 
	  }
	  
	  public List<CourseResponseDTO> selectCoursesByStudentId(String id) { 
		  String sql = "select course_table.id, course_table.name from course_table join student_course on course_table.id = student_course.course_id where student_course.student_id = ?"; 
		  return jdbcTemplate.query(sql, (rs, rowNum) -> new CourseResponseDTO(rs.getString("id"), rs.getString("name")), id );
	  }
	  
//	  public String selectCourseIdByStudentId(String id) { String courses = "";
//	  String sql =
//	  "select course_table.id from course_table join student_course on course_table.id = student_course.course_id where student_course.student_id = ?"
//	  ; try { PreparedStatement ps = con.prepareStatement(sql); ps.setString(1,id);
//	  ResultSet rs = ps.executeQuery(); while(rs.next()) { if(courses.isBlank()) {
//	  courses = rs.getString(1); }else courses = courses+", "+rs.getString(1); }
//	  
//	  } catch (SQLException e) {
//	  System.out.println("Course Id Selection with student id Fail."); } return
//	  courses; }
	  
//	  public ArrayList<String> selectCourseListByStudentId(String id) {
//	  ArrayList<String> course= new ArrayList<String>(); String sql =
//	  "select course_table.name from course_table join student_course on course_table.id = student_course.course_id  "
//	  + "where student_course.student_id = ? "; try { PreparedStatement ps =
//	  con.prepareStatement(sql); ps.setString(1, id); ResultSet rs =
//	  ps.executeQuery(); while(rs.next()) { course.add(rs.getString(1)); } } catch
//	  (SQLException e) { System.out.println("course list selection error"); }
//	  return course; }
	  
	  public int deleteCourseListByStudentId(String id) { 
		  int result = 0; 
		  String query = "delete from student_course where student_id = ?"; 
		  result = jdbcTemplate.update(query, id );
		  return result; 
	  }
	  
	  public List<StudentResponseDTO> searchByIdNameCourse(String id, String name, String course){ 
		  String sql = "select distinct student_table.id, student_table.name, student_table.dob,student_table.gender, student_table.phone, student_table.education, student_table.photo"
						  + " from student_table join student_course on student_table.id = student_course.student_id "
						  + "join course_table on student_course.course_id = course_table.id" 
						  + " where student_table.id = ? or student_table.name = ? or course_table.name = ? order by student_table.id" ; 
		  return jdbcTemplate.query(sql, (rs, rowNum) -> new StudentResponseDTO(rs.getString("id"), rs.getString("name"),rs.getString("dob"), rs.getString("gender"), rs.getString("phone"), rs.getString("education"), rs.getString("photo")), id, name, course );
	  }
	  
  }
 