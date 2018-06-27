package com.student.data.dao;

import com.student.data.model.student.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {
	public Student getStudent(Map<String, String> params);
	public List<Student> getStudentList(Map<String, String> params);
	public void insertStudent(Map<String, String> params);
	public void editStudent(Map<String, String> params);
    public void deleteStudent(@Param("id") String id);
    public int getStudentCount(Map<String, String> params);
//	public Student getStudentById(@Param("username") String username);
}
