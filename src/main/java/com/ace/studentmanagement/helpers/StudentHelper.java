/*
 * package com.ace.studentmanagement.helpers;
 * 
 * import java.util.ArrayList;
 * 
 * import studentmanagement.persistant.dao.StudentDAO; import
 * studentmanagement.persistant.dto.StudentResponseDTO;
 * 
 * public class StudentHelper { public static String idGenerator() { String id =
 * ""; ArrayList<StudentResponseDTO> list =new StudentDAO().selectStudentAll();
 * if(list == null || list.size() <= 0) { id = "STU001"; }else {
 * StudentResponseDTO lastDTO = list.get(list.size()-1); int lastId =
 * Integer.parseInt(lastDTO.getStudentId().substring(3)); id =
 * String.format("STU"+"%03d", lastId+1); } return id; } }
 */