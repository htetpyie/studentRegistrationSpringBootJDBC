
  package com.ace.studentmanagement.dao;
  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ace.studentmanagement.dto.CourseDTO;
import com.ace.studentmanagement.dto.CourseResponseDTO;
  
  
  
  @Repository
  public class CourseDAO {  
		  @Autowired
		  JdbcTemplate jdbcTemplate;
		  
		  public int insertCourse(CourseDTO dto) { 
			  int result = 0; 
			  String sql = "Insert into course_table (id, name) " + "values(?, ?)"; 
			  result = jdbcTemplate.update(sql, dto.getCourseId(), dto.getCourseName());
			  return result; 
			  }
		  
		  public String selectCourseIdByCourseName(String name) { 
			  String sql = "SELECT id FROM course_table where name=? "; 
			  return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getString("id"), name );
		  }
		  
		  public CourseResponseDTO selectCourse(CourseDTO dto) { 
			  String sql = "select * from course_table where id=?"; 
			  return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new CourseResponseDTO(rs.getString("id"), rs.getString("name")),dto.getCourseId());
		  
		  }
		  
		  public List<CourseResponseDTO> selectCourseAll() {			  
			  String sql ="select * from course_table "; 
			  return jdbcTemplate.query(sql, (rs, rowNum) -> new CourseResponseDTO(rs.getString("id"), rs.getString("name")) );
		  }
		  
  }
  
  
  
 