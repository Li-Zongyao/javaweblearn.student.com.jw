package com.student.service.student;

import com.student.data.dao.StudentDao;
import com.student.data.model.student.Student;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("loginService")
public class StudentService implements IStudentService {
	@Resource
	private StudentDao studentDao;


//	@Autowired
//	private RepositoryConfiguration reposConfig;

	@Override
	public Student getStudent(JSONObject jsonObject) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", jsonObject.getString("username"));
		params.put("password", jsonObject.getString("password"));
		System.out.println(params.toString());
		return studentDao.getStudent(params);
	}

	@Override
	public List<Student> getStudentList(JSONObject jsonObject) {
		Map<String, String> params = new HashMap<String, String>();

		params.put("search", jsonObject.getString("searchText"));
        params.put("order", jsonObject.getString("sortOrder"));
        params.put("sort", jsonObject.getString("sortName"));
		int pageSize = jsonObject.getInt("pageSize");
		int pageNumber = jsonObject.getInt("pageNumber");
		params.put("start", ""+((pageNumber-1)*pageSize));
		params.put("limit", ""+pageSize);

		System.out.println(params.toString());

		return studentDao.getStudentList(params);
	}

	@Override
	public void insertStudent(JSONObject jsonObject) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("username",jsonObject.getString("username"));
		params.put("password",jsonObject.getString("password"));
		params.put("name",jsonObject.getString("name"));
		params.put("email",jsonObject.getString("email"));
		params.put("sex",jsonObject.getString("sex"));
		studentDao.insertStudent(params);
	}

	@Override
	public void editStudent(JSONObject jsonObject) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("username",jsonObject.getString("username"));
		params.put("password",jsonObject.getString("password"));
		params.put("name",jsonObject.getString("name"));
		params.put("email",jsonObject.getString("email"));
		params.put("sex",jsonObject.getString("sex"));
		studentDao.editStudent(params);
	}

    @Override
    public void deleteStudent(JSONObject jsonObject) {
        studentDao.deleteStudent(jsonObject.getString("username"));
    }

	@Override
	public int getStudentCount(JSONObject jsonObject) {
		Map<String, String> params = new HashMap<String, String>();

		params.put("search", jsonObject.getString("searchText"));
		params.put("order", jsonObject.getString("sortOrder"));
		params.put("sort", jsonObject.getString("sortName"));

		return studentDao.getStudentCount(params);
	}

//	@Override
//	public User getUserById(String username) {
//		return loginDao.getUserById(username);
//	}
//
//
//	@Override
//	public int doForget(List<User> users, String email)  throws Exception{
//		if(users.size()>0){
//			SendMail sm = new SendMail(reposConfig.getMailSmtp(),reposConfig.getMailUsername(),reposConfig.getMailPassword());
//			sm.setFrom(reposConfig.getMailUsername());
//			sm.setTo(email);
//			sm.setSubject("TPSS密码找回");
//
//			String data = "您好！ \n\n";
//			data = data + "    这是来自TPSS的电子邮件，帮您找到的用户名和密码如下： \n\n";
//			for(int i=0;i<users.size();i++){
//				User user = users.get(i);
//				data = data + "        "  +(i+1) + ": 姓名("+ user.getName()+")；";
//				data = data +"账户("+ user.getUsername()+")；";
//				data = data +"密码("+ user.getPassword()+")。\n";
//			}
//			data = data + "\n    感谢您的使用！ \n";
//
//			sm.setBody(data, SendMail.TEXT);
//			sm.setNeedAuth(true);
//			try {
//				sm.send();
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new Exception(e.getMessage());
//			}
//		}
//		return users.size();
//	}


}
