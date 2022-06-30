/*
 * package com.ace.studentmanagement.helpers;
 * 
 * import java.util.ArrayList;
 * 
 * import studentmanagement.persistant.dao.CourseDAO; import
 * studentmanagement.persistant.dto.CourseResponseDTO;
 * 
 * 
 * 
 * public class CourseHelper {
 * 
 * public static String idGenerator() { String id = "";
 * ArrayList<CourseResponseDTO> list =new CourseDAO().selectCourseAll(); if(list
 * == null || list.size() <= 0) { id = "COU001"; }else { CourseResponseDTO
 * lastDTO = list.get(list.size()-1); int lastId =
 * Integer.parseInt(lastDTO.getCourseId().substring(3)); id =
 * String.format("COU"+"%03d", lastId+1); } return id; }
 * 
 * public static boolean isCourseExist(String courseName) {
 * ArrayList<CourseResponseDTO> list =new CourseDAO().selectCourseAll(); if(list
 * != null) { for(CourseResponseDTO course: list) {
 * if(course.getCourseName().equals(courseName)) return true; } } return false;
 * }
 * 
 * }
 */