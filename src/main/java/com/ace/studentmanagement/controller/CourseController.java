package com.ace.studentmanagement.controller;
  
  
  import java.util.List;

import javax.servlet.http.HttpSession;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Controller; import
  org.springframework.ui.ModelMap; import
  org.springframework.validation.BindingResult; import
  org.springframework.validation.annotation.Validated; import
  org.springframework.web.bind.annotation.GetMapping; import
  org.springframework.web.bind.annotation.ModelAttribute; import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.servlet.ModelAndView;

import com.ace.studentmanagement.dao.CourseDAO;
import com.ace.studentmanagement.dto.CourseDTO;
import com.ace.studentmanagement.dto.CourseResponseDTO;
import com.ace.studentmanagement.model.CourseBean;
  
  @Controller public class CourseController {
  
	  @Autowired 
	  private CourseDAO courseDAO;
	  
	  @GetMapping("/showCourseRegister") 
	  public ModelAndView showCourseRegister(HttpSession session) { 
		  if(session.getAttribute("user") == null) { 
			  return new ModelAndView("redirect:/"); 
			  }else 
			  { CourseBean bean = new CourseBean(); 
			  return new ModelAndView("BUD003","courseBean",bean);
			  }
	  }
	  
	  @PostMapping("/courseRegister") 
	  public String courseRegister(@ModelAttribute("courseBean") @Validated CourseBean courseBean, BindingResult br, ModelMap model) { 
		  if(br.hasErrors()) { 
			  return "BUD003"; }
		  else if(this.isCourseExist(courseBean.getCourseName())) {
			  model.addAttribute("error",courseBean.getCourseName()+" already exists! ");
			  return "BUD003"; 
			  } else { 
				  String courseId = this.idGenerator();
				  String courseName = courseBean.getCourseName();
				  courseBean.setCourseId(courseId); 
				  CourseDTO dto = new CourseDTO();
				  dto.setCourseId(courseId); 
				  dto.setCourseName(courseName);
				  courseDAO.insertCourse(dto);
				  model.addAttribute("success", courseName+" is successfully added!"); 
				  return "BUD003"; }
	  
	  }
	  
	  public boolean isCourseExist(String courseName) {
		  List<CourseResponseDTO> list = courseDAO.selectCourseAll(); 
		  if(list!= null) { 
			  for(CourseResponseDTO course: list) {
				  if(course.getCourseName().equals(courseName)) 
					  return true; } 
			  } return false;
	  }
	  
	  public  String idGenerator() { 
		  String id = "";
		  List<CourseResponseDTO> list = courseDAO.selectCourseAll(); 
		  if(list == null || list.size() <= 0) { 
			  id = "COU001"; 
			  }else { 
				  CourseResponseDTO lastDTO = list.get(list.size()-1); 
				  int lastId = Integer.parseInt(lastDTO.getCourseId().substring(3)); 
				  id = String.format("COU"+"%03d", lastId+1); 
				  } 
		  return id; 
		  }
		  
  }
 