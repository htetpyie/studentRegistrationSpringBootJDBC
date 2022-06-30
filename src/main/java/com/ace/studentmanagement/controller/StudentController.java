
  package com.ace.studentmanagement.controller;
  
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
  import java.util.Map;
  
  import javax.servlet.http.HttpSession;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Controller; import
  org.springframework.ui.ModelMap; import
  org.springframework.validation.BindingResult; import
  org.springframework.validation.annotation.Validated; import
  org.springframework.web.bind.annotation.GetMapping; import
  org.springframework.web.bind.annotation.ModelAttribute;  import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestParam; import
  org.springframework.web.servlet.ModelAndView;

import com.ace.studentmanagement.dao.CourseDAO;
import com.ace.studentmanagement.dao.StudentCourseDAO;
import com.ace.studentmanagement.dao.StudentDAO;
import com.ace.studentmanagement.dto.CourseResponseDTO;
import com.ace.studentmanagement.dto.StudentDTO;
import com.ace.studentmanagement.dto.StudentResponseDTO;
import com.ace.studentmanagement.model.StudentBean;
  
  @Controller 
  public class StudentController {
  
	  @Autowired 
	  private StudentCourseDAO studentCourseDAO;
	  @Autowired
	  private StudentDAO studentDAO;
	  @Autowired
	  private CourseDAO courseDAO;
  
	  @GetMapping("/showStudentRegister") 
	  public ModelAndView showStudentRegister(HttpSession session) { 
		  if(session.getAttribute("user") == null) { 
			  return new ModelAndView("redirect:/"); 
			  }else 
			  		{	  
					  StudentBean studentBean = new StudentBean();
					  //studentBean.setStudentCourse("Java"); 
					  return new ModelAndView("STU001","studentBean",studentBean); 
					  } 
	 }
	  
	  @PostMapping("/studentRegister") 
	  public String studentRegister(@ModelAttribute("studentBean") @Validated StudentBean studentBean, BindingResult br, ModelMap model) { 
		  if(br.hasErrors()) { 
			  return "STU001"; 
			  } 
		  String id = this.idGenerator(); 
		  StudentDTO dto = new StudentDTO(id, studentBean.getStudentName(), studentBean.getStudentDob(),studentBean.getStudentGender(), studentBean.getStudentPhone(),
				  			studentBean.getStudentEducation(),studentBean.getStudentPhoto()); 
		  studentDAO.insertStudent(dto); 
		  ArrayList<String> courses = studentBean.getStudentCourse(); 
		  for(String courseId: courses ) { 
			  studentCourseDAO.insertStudentCourse(dto.getStudentId(), courseId); 
			  } 
		  //return "redirect:/showStudentAll";
		  model.addAttribute("success","Student Registered successfully.");
		  return "STU001";
		  }
	  
	  @GetMapping("/showStudentAll") 
	  public String showStudentAll(ModelMap model, HttpSession session) { 
		  if(session.getAttribute("user") == null) { 
			  return "redirect:/"; 
			  }else 
			  {		  
		  List<StudentResponseDTO> studentList = studentDAO.selectStudentAll(); 
		  Map<String, String> map = new HashMap<>();
		  for(StudentResponseDTO student: studentList) { 
			  List<CourseResponseDTO> selectedCourses = studentCourseDAO.selectCoursesByStudentId(student.getStudentId());
			  String courseName = "";
			  for(CourseResponseDTO course: selectedCourses) {
				  if(courseName.isBlank()) {
					  courseName += course.getCourseName();
				  }else courseName += ", "+ course.getCourseName();
			  }
			  map.put(student.getStudentId(), courseName); 
		  } 
		  model.addAttribute("map", map);
		  model.addAttribute("studentList", studentList); 
		  return "STU003"; 
		  } 
		}
	  
	  @GetMapping("/searchStudent") 
	  public String searchStudent(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("course") String course, ModelMap model) {
		  if(id.isBlank() && name.isBlank() && course.isBlank()) { 
			  return "redirect:/showStudentAll"; 
		  }else { 
			  List<StudentResponseDTO> studentList = studentCourseDAO.searchByIdNameCourse(id, name, course); 
			  Map<String, String> map = new HashMap<>(); 
			  for(StudentResponseDTO student: studentList) {
				  List<CourseResponseDTO> SelectedCourses =studentCourseDAO.selectCoursesByStudentId(student.getStudentId());
				  String courseName = "";
				  for(CourseResponseDTO courseDTO: SelectedCourses) {
					  if(courseName.isBlank()) {
						  courseName += courseDTO.getCourseName();
					  }else courseName += ", "+ courseDTO.getCourseName();
				  }
				  map.put(student.getStudentId(), courseName); 
				  }		  
		  model.addAttribute("studentList", studentList);
		  model.addAttribute("map",map); 
		  //model.addAttribute("counter",this.counter(0));
		  return "STU003"; 
		  } 
		 }
		  
	  @GetMapping("/seeMore") 
	  public ModelAndView seeMore(@RequestParam("id")String id) { 
		  StudentDTO dto = new StudentDTO(); 
		  dto.setStudentId(id);
		  StudentResponseDTO studentDTO = studentDAO.selectStudent(dto);
		  StudentBean student = new StudentBean(studentDTO.getStudentId(), studentDTO.getStudentName(),studentDTO.getStudentDob(), studentDTO.getStudentGender(), studentDTO.getStudentPhone(), studentDTO.getStudentEducation(), studentDTO.getStudentPhoto());
		  //Selected course 
		  List<CourseResponseDTO> courseList = studentCourseDAO.selectCoursesByStudentId(student.getStudentId());
		  ArrayList<String> courseIds = new ArrayList<>();
		  for(CourseResponseDTO course: courseList) {
			  courseIds.add(course.getCourseId());
		  }
		  student.setStudentCourse(courseIds); 
		  return new  ModelAndView("STU002","studentBean",student); }
	  
	  @GetMapping("/deleteStudent") 
	  public String deleteStudent(@RequestParam("id") String id) {
		  studentCourseDAO.deleteCourseListByStudentId(id); 
		  StudentDTO dto = new StudentDTO(); 
		  dto.setStudentId(id); 
		  studentDAO.deleteStudent(dto);
		  return "redirect:/showStudentAll"; }
		  
	  @GetMapping("/showStudentUpdate") 
	  public ModelAndView showStudentUpdate(@RequestParam("id") String id) { 
		  StudentDTO dto = new StudentDTO(); 
		  dto.setStudentId(id); 
		  StudentResponseDTO studentDTO = studentDAO.selectStudent(dto); 
		  StudentBean student = new StudentBean(studentDTO.getStudentId(), studentDTO.getStudentName(), studentDTO.getStudentDob(), studentDTO.getStudentGender(),studentDTO.getStudentPhone(), studentDTO.getStudentEducation(), studentDTO.getStudentPhoto()); 
		  List<CourseResponseDTO> courseList = studentCourseDAO.selectCoursesByStudentId(student.getStudentId());
		  ArrayList<String> courseIds = new ArrayList<>();
		  for(CourseResponseDTO course: courseList) {
			  courseIds.add(course.getCourseId());
		  }
		  student.setStudentCourse(courseIds); 
		  return new ModelAndView("STU002-01","studentBean",student); }
	  
	  @PostMapping("/updateStudent") 
	  public String updateStudent(@ModelAttribute("studentBean") @Validated StudentBean bean, BindingResult br) { 
		  if(br.hasErrors()) { return "STU002-01"; }	  
		  StudentDTO dto = new StudentDTO(bean.getStudentId(), bean.getStudentName(), bean.getStudentDob(), bean.getStudentGender(), bean.getStudentPhone(), bean.getStudentEducation(), bean.getStudentPhoto() );
		  studentDAO.updateStudent(dto);		  
		  //Delete existing course related with the student and add again new
		  studentCourseDAO.deleteCourseListByStudentId(bean.getStudentId());		  
		  ArrayList<String> courseIds = bean.getStudentCourse(); 
		  for(String courseId: courseIds ) { 			
			  studentCourseDAO.insertStudentCourse(dto.getStudentId(), courseId);
			  }
		  return "redirect:/showStudentAll";		  
	  }
	  
	  @ModelAttribute(value="courseList") 
	  public List<CourseResponseDTO> courseList(){
		  ArrayList<String> list = new ArrayList<>(); 
		  List<CourseResponseDTO> courseLists= courseDAO.selectCourseAll(); 
		  for(CourseResponseDTO course: courseLists) { 
			  list.add(course.getCourseName()); 
			  } 
		  return courseLists;	  
	  } 
	  
	  public  String idGenerator() { 
		  String id = ""; 
		  List<StudentResponseDTO> list = studentDAO.selectStudentAll();
		  if(list == null || list.size() <= 0) { 
			  id = "STU001"; 
		  }else {
			  StudentResponseDTO lastDTO = list.get(list.size()-1); 
			  int lastId = Integer.parseInt(lastDTO.getStudentId().substring(3)); 
			  id = String.format("STU"+"%03d", lastId+1); 
			  } 
		  return id; }

 }
 