package com.ace.studentmanagement.helpers;
  
import java.util.List;


import com.ace.studentmanagement.dao.UserDAO;
import com.ace.studentmanagement.dto.UserResponseDTO;
  public class UserHelper {
  

  
  public  String idGenerator() {
	  UserDAO dao = new UserDAO();
	  String id = "";
	  	System.out.println("in id generator...");
		  List<UserResponseDTO> list = dao.selectUserAll();
		  for(UserResponseDTO a: list) {
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
			  UserDAO dao = new UserDAO();
			  List<UserResponseDTO> list = dao.selectUserAll();
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
 