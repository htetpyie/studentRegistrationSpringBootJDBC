package com.ace.studentmanagement.helpers;
  
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ace.studentmanagement.dao.UserDAO;
import com.ace.studentmanagement.dto.UserResponseDTO;
	@Repository
  public class UserHelper {
  @Autowired
  private UserDAO dao;
  public  String idGenerator() {
	  String id = "";
	  	System.out.println("in id generator...");
		  List<UserResponseDTO> list =  new ArrayList<>();
		  //list = dao.selectUserAll();
		  for(UserResponseDTO a: dao.selectUserAll()) {
			  System.out.println(a.getUserId());
		  }
		  System.out.println("list size is "+ list.size());
		  if(list == null || list.size() <= 0) { 
			  id = "USR001"; 
		  }else { 
			  UserResponseDTO lastDTO = list.get(list.size()-1); 
			  int lastId = Integer.parseInt(lastDTO.getUserId().substring(3)); 
			  id = String.format("USR"+"%03d", lastId+1); 
		  } 		  
	  
	  return id; 
	  }
	  
	  public boolean isEmailExist(String email) { 
		  try {
			  List<UserResponseDTO> list = new UserDAO().selectUserAll();
			  System.out.println("size is " + list.size());
			  if(list != null) { 
				  for(UserResponseDTO user: list ) { 
					  if(user.getUserEmail().equals(email)) return true; 
				  } 
			  } 
			  
		  }catch(Exception e) {
			  System.out.println("Exception occur in isEmailExist Check ");
		  }
		  return false; 
		  }
	  
  }
 