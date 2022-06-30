
  package com.ace.studentmanagement.dao;
  
  import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ace.studentmanagement.dto.UserDTO;
import com.ace.studentmanagement.dto.UserResponseDTO;
  
  
@Repository
public class UserDAO { 
	  
	  @Autowired
	  JdbcTemplate jdbcTemplate;
  
	  public int insertUser(UserDTO dto) {
		  int result = 0;
		  String sql =  "Insert into user_table (id, name, email, password, role) " +
				  		"values(?, ?, ?, ?, ?)"; 
		  result = jdbcTemplate.update(sql, dto.getUserId(), dto.getUserName(), dto.getUserEmail(), dto.getUserPassword(),dto.getUserRole());		  	  
		  return  result;
	   }
	  
	  public int updateUser(UserDTO dto) {
		  int result = 0;
		  String sql = "update user_table set name=? , email = ?, password = ?, role = ?" +
				  		"where id = ?";
		  result = jdbcTemplate.update(sql, dto.getUserName(), dto.getUserEmail(), dto.getUserPassword(), dto.getUserRole(), dto.getUserId());	 
		  System.out.println("User table updated successfully.");		  
		  return result;
	  }
	  
	  public int deleteUser(UserDTO dto) { 
		  int result = 0;
		  String sql = "delete from user_table where id = ?";
		  result = jdbcTemplate.update(sql, dto.getUserId());
		  System.out.println("User table detele successfully.");	  
		  return result; 
	  }
	  
	  public UserResponseDTO selectUser(UserDTO dto) { 
		  String sql = "select * from user_table where id=?"; 
		  return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new UserResponseDTO(
				  			rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("role")
				  			), dto.getUserId());
	  
	  }
	 
	  public List<UserResponseDTO> selectUsersByIdAndName(String id, String name) {
		  String  sql = "select * from user_table where id=? or name=?";
		  return jdbcTemplate.query(sql, (rs, rowNum) -> new UserResponseDTO(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("role")), id, name);
	
	  }
	  
	  public List<UserResponseDTO> selectUserAll() {
	  	String sql = "select * from user_table ";
	  	return jdbcTemplate.query(sql, (rs, rowNum) -> new UserResponseDTO(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("role")));
	  
	  }
	  
  }
 