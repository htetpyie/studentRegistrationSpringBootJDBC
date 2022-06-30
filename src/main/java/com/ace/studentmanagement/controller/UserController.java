package com.ace.studentmanagement.controller;
  

  
import java.util.List;

import javax.servlet.http.HttpSession;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Controller; import
  org.springframework.ui.ModelMap; import
  org.springframework.validation.BindingResult; import
  org.springframework.validation.annotation.Validated; import
  org.springframework.web.bind.annotation.GetMapping; import
  org.springframework.web.bind.annotation.ModelAttribute;
 import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestMapping; import
  org.springframework.web.bind.annotation.RequestMethod; import
  org.springframework.web.bind.annotation.RequestParam; import
  org.springframework.web.servlet.ModelAndView;

import com.ace.studentmanagement.dao.UserDAO;
import com.ace.studentmanagement.dto.UserDTO;
import com.ace.studentmanagement.dto.UserResponseDTO;
import com.ace.studentmanagement.model.UserBean;
  
  @Controller 
  public class UserController {
  
	  @Autowired 
	  private UserDAO dao;
	  
	  @RequestMapping(value="/displayUserRegister", method=RequestMethod.GET)
	  public ModelAndView displayUserRegister(ModelMap map) { 
		  UserBean userBean = new UserBean(); 
		  return new ModelAndView("USR001","userBean",userBean); }
	  
	  @PostMapping("/userRegister") 
	  public String userRegister(@ModelAttribute("userBean") @Validated UserBean userBean,  BindingResult br, ModelMap model ) { 
		  if(br.hasErrors()) { 
			  return "USR001";
		  	}
		  else if(!userBean.getUserPassword().equals(userBean.getUserCfPassword())) {			  
			  model.addAttribute("passwordError","Password doesn't match!"); 
			  return "USR001"; 
		  } else if(this.isEmailExist(userBean.getUserEmail())) {			  
			  model.addAttribute("error","Email already exists."); return "USR001"; 
		  }else {
			  
			  	  String id = "";
				  List<UserResponseDTO> list =  dao.selectUserAll();
				  if(list == null || list.size() <= 0) { 
					  id = "USR001"; 
				  }else { 
					  UserResponseDTO lastDTO = list.get(list.size()-1); 
					  int lastId = Integer.parseInt(lastDTO.getUserId().substring(3)); 
					  id = String.format("USR"+"%03d", lastId+1); 
				  } 		  
				  //userBean.setUserId(new UserHelper().idGenerator()); 
				  userBean.setUserId(id);
				  System.out.println("id is "+ userBean.getUserId());
				  UserDTO dto = new UserDTO(userBean.getUserId(), userBean.getUserName(), userBean.getUserEmail(), userBean.getUserPassword(), userBean.getUserRole());
				  dao.insertUser(dto);
				  model.addAttribute("success","Successfully registered."); 
				  return "USR001"; 
			  }
	  
	  		}
	  
		
		  @GetMapping("/showUser") public String showUser(ModelMap model, HttpSession session) { 
			  if(session.getAttribute("user") == null) { 
				  return "redirect:/";
			  }else 
			  { 
				  List<UserResponseDTO> list = dao.selectUserAll();
				  model.addAttribute("userList",list); 
				  return "USR003"; }
			  
			  }
		  
			
			  @GetMapping("/showAddUser") 
			  public ModelAndView showAddUserPage() { 
				  UserBean userBean = new UserBean(); 
				  return new ModelAndView("USR001-01","userBean",userBean); 
				  }
			  
			  @PostMapping("/userAdd") 
			  public String userAdd(@ModelAttribute("userBean") @Validated UserBean userBean, BindingResult br, ModelMap model ) { 
				  if(br.hasErrors()) { 
					  return "USR001-01";
				  } else if(!userBean.getUserPassword().equals(userBean.getUserCfPassword())) {
					  
					  model.addAttribute("passwordError","Password doesn't match!"); 
					  return "USR001-01"; 
				  }  else if(this.isEmailExist(userBean.getUserEmail())) {			  
					  model.addAttribute("error","Email already exists."); 
					  return "USR001-01"; 
				  }else {
					  
					  	  String id = "";
						  List<UserResponseDTO> list =  dao.selectUserAll();
						  if(list == null || list.size() <= 0) { 
							  id = "USR001"; 
						  }else { 
							  UserResponseDTO lastDTO = list.get(list.size()-1); 
							  int lastId = Integer.parseInt(lastDTO.getUserId().substring(3)); 
							  id = String.format("USR"+"%03d", lastId+1); 
						  } 		  
						  //userBean.setUserId(new UserHelper().idGenerator()); 
						  userBean.setUserId(id);
						  System.out.println("id is "+ userBean.getUserId());
						  UserDTO dto = new UserDTO(userBean.getUserId(), userBean.getUserName(), userBean.getUserEmail(), userBean.getUserPassword(), userBean.getUserRole());
						  dao.insertUser(dto);
						  model.addAttribute("success","Successfully registered."); 
						  return "redirect:/showUser"; 
					  }
			  
			  		}
//			  
			  @GetMapping("/userSearch") 
			  public String userSearch(ModelMap model, @RequestParam("id") String id, @RequestParam("name") String name) {
				  if(name.isBlank() && id.isBlank()) { 
					  return "redirect:/showUser"; 
					  } else {
						  List<UserResponseDTO> list = dao.selectUsersByIdAndName(id, name); 
						  model.addAttribute("userList", list); 
						  return "USR003";
					  } 
				  }
			  
			  @GetMapping("/showUserUpdate") 
			  public ModelAndView showUserUpdate(ModelMap model, @RequestParam("id") String id) { 
				  UserDTO dto = new UserDTO();
				  dto.setUserId(id); 				 
				  UserResponseDTO userRes = dao.selectUser(dto);
				  UserBean userBean = new UserBean(userRes.getUserId(), userRes.getUserName(), userRes.getUserEmail(), userRes.getUserPassword(), userRes.getUserRole());
				  //model.addAttribute("userBean", userRes); 
				  return new ModelAndView("USR002","userBean", userBean); 
				  }
			  
			  @PostMapping("/userUpdate") 
			  public String userUpdate(@ModelAttribute("userBean") @Validated UserBean userBean, BindingResult br, HttpSession session, ModelMap model) {
			  if(br.hasErrors() ) {
				  return "USR002"; 
			  } else if(!userBean.getUserPassword().equals(userBean.getUserCfPassword())) { 
				  model.addAttribute("passwordError", "Password doesn't match." ); 
				  return "USR002"; 
			  } else { 
				  UserDTO userDTO = new UserDTO(userBean.getUserId(), userBean.getUserName(), userBean.getUserEmail(), userBean.getUserPassword(), userBean.getUserRole()); 
				  dao.updateUser(userDTO) ; 
				  return "redirect:/showUser"; } }
			  
			  @RequestMapping("/userDelete") 
			  public String userDelete(@RequestParam("id") String id) { 
				  UserDTO dto = new UserDTO();
				  	dto.setUserId(id); 
				  	dao.deleteUser(dto); 
				  	return "redirect:/showUser"; }
			  
			  
			  public boolean isEmailExist(String email) { 
					  List<UserResponseDTO> list = dao.selectUserAll();
					  System.out.println("size is " + list.size());
					  if(list != null) { 
						  for(UserResponseDTO user: list ) { 
							  if(user.getUserEmail().equals(email)) return true; 
						  } 
					  } 					  
				  
				  return false; 
				  }			 
	  
  
  }
 