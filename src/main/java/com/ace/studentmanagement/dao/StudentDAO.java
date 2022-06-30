
  package com.ace.studentmanagement.dao;
  
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.jdbc.core.JdbcTemplate;
	import org.springframework.stereotype.Repository;
	
	import com.ace.studentmanagement.dto.StudentDTO;
	import com.ace.studentmanagement.dto.StudentResponseDTO;
  
  	 @Repository
  	 public class StudentDAO { 
  	  @Autowired
  	  JdbcTemplate jdbcTemplate;	  
	  public int insertStudent(StudentDTO dto) { 
		  int result = 0; 
		  String sql = "Insert into student_table (id, name, dob, gender, phone, education, photo) values(?, ?, ?, ?, ?, ?, ?)"; 
		  result = jdbcTemplate.update(sql, dto.getStudentId(), dto.getStudentName(), dto.getStudentDob(), dto.getStudentGender(), dto.getStudentPhone(), dto.getStudentEducation(), dto.getStudentPhoto());
		  return result;
	  }
	  
	  public int updateStudent(StudentDTO dto) { 
		  int result = 0; 
		  String sql = "update student_table set name = ?, dob = ?, gender = ?, phone = ?, education = ?, photo = ? where id = ?"; 
		  result = jdbcTemplate.update(sql, dto.getStudentName(), dto.getStudentDob(), dto.getStudentGender(), dto.getStudentPhone(), dto.getStudentEducation(), dto.getStudentPhoto(), dto.getStudentId());
		  return result; 
	  }
	  
	  public StudentResponseDTO selectStudent(StudentDTO dto) { 
		  String sql = "select * from student_table where id=?"; 
		  return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new StudentResponseDTO(rs.getString("id") , rs.getString("name"), rs.getString("dob") , rs.getString("gender"), rs.getString("phone"), rs.getString("education") , rs.getString("photo") ), dto.getStudentId());
	  
	  }
	  
	  public List<StudentResponseDTO> selectStudentAll() {		  
		  String sql = "select * from student_table "; 
		  return jdbcTemplate.query(sql, (rs, rowNum) -> new StudentResponseDTO(rs.getString("id") , rs.getString("name"), rs.getString("dob") , rs.getString("gender"), rs.getString("phone"), rs.getString("education") , rs.getString("photo")) );
	  
	  }
	  
	  public int deleteStudent(StudentDTO dto) {
		  int result = 0;
		  String sql = "delete from student_table where id=?";
		  result = jdbcTemplate.update(sql, dto.getStudentId());
		  return result;
	  }
	  
  
  
  }
 