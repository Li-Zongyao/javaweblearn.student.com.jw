package com.student.service.student;


import com.student.data.model.student.Student;
import org.json.JSONObject;

import java.util.List;

public interface IStudentService {
	public Student getStudent(JSONObject jsonObject);
	public List<Student> getStudentList(JSONObject cond);
	public void insertStudent(JSONObject jsonObject);
	public void editStudent(JSONObject jsonObject);
    public void deleteStudent(JSONObject jsonObject);
	public int getStudentCount(JSONObject jsonObject);

//	public User getUserById(String username);
//	public int doForget(List<User> users, String email)  throws Exception;
}
