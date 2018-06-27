package com.student.controller;

import com.student.data.model.student.Student;
import com.student.service.student.IStudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Resource
    private IStudentService studentService;

    //@Resource
    //private IProfileService profileService;

    @ResponseBody   //--- /xxx/studentlist
    @RequestMapping(value = "/studentlist",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String doStudentList(@RequestBody String input, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject(input);

        logger.info(input);

        List<Student> students = studentService.getStudentList(jsonObject);

        JSONObject root = new JSONObject();
        root.put("total",studentService.getStudentCount(jsonObject)); //students.size()
        root.put("rows",new JSONArray(students));

        logger.info(root.toString());

        return root.toString();
    }

    @ResponseBody   //--- /xxx/addstudent
    @RequestMapping(value = "/addstudent",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String doAddStudent(@RequestBody String student, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject(student);

        logger.info(jsonObject.toString());
        studentService.insertStudent(jsonObject);

        JSONObject jsonStatus = new JSONObject();
        jsonStatus.put("status","success");

        return jsonStatus.toString();
    }

    @ResponseBody   //--- /xxx/editstudent
    @RequestMapping(value = "/editstudent",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String doEditStudent(@RequestBody String student, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject(student);

        logger.info(jsonObject.toString());
        studentService.editStudent(jsonObject);

        JSONObject jsonStatus = new JSONObject();
        jsonStatus.put("status","success");

        return jsonStatus.toString();
    }

    @ResponseBody   //--- /xxx/deletestudent
    @RequestMapping(value = "/deletestudent",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String doDeleteStudent(@RequestBody String student, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject(student);

        logger.info(jsonObject.toString());
        studentService.deleteStudent(jsonObject);

        JSONObject jsonStatus = new JSONObject();
        jsonStatus.put("status","success");

        return jsonStatus.toString();
    }



    @ResponseBody   //--- /xxx/login
    @RequestMapping(value = "/login",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String doLogin(@RequestBody String input, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject(input);

        logger.info("This is the login process module!");
        logger.info(jsonObject.toString());

        Student student = studentService.getStudent(jsonObject);

        if(student!=null){
            jsonObject.put("status", "success");
            jsonObject.put("message", "");
        }else{
            jsonObject.put("status", "failure");
            jsonObject.put("message", "用户名或密码错！");
        }

        logger.info(jsonObject.toString());

//        HttpSession s = request.getSession();
//        String vcode = (String) s.getAttribute("validateCode");
//        String valicode = jsonObject.getString("valicode");
//        if (vcode == null) {
//            jsonObject.put("status", "failure");
//            jsonObject.put("message", "验证码已经过期！");
//        } else if (vcode.equals(valicode)) {
//            User user = null;
//            try {
//                user = loginService.getUser(jsonObject);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (user != null) {
//                if (!s.isNew()) {
//                    s.invalidate();
//                    s = request.getSession(true);
//                }
//                jsonObject.put("sessionId", s.getId());
//                s.setAttribute("user", user);
//                jsonObject.put("status", "success");
//                jsonObject.put("message", "");
//            } else {
//                s.invalidate();
//                jsonObject.put("status", "failure");
//                jsonObject.put("message", "用户名或密码错！");
//            }
//        } else {
//            jsonObject.put("status", "failure");
//            jsonObject.put("message", "验证码错误！");
//        }
//        jsonObject.remove("password");
        return jsonObject.toString();
    }

//    @ResponseBody   //--- /xxx/forget
//    @RequestMapping(value = "/forget",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    public String doForget(@RequestBody String input) {
//        JSONObject jsonObject = new JSONObject(input);
//        List<User> users = null;
//        try {
//            users = loginService.getUserListByEmail(jsonObject.getString("email"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(users != null){
//            int size = 0;
//            try {
//                size = loginService.doForget(users,jsonObject.getString("email"));
//                if (size>0) {
//                    jsonObject.put("status", "success");
//                    jsonObject.put("message", "账户和密码已经发到指定邮箱了！");
//                }else {
//                    jsonObject.put("status", "failure");
//                    jsonObject.put("message", "输入的电子邮箱不存在！");
//                }
//            } catch (Exception e) {
//                jsonObject.put("status", "failure");
//                jsonObject.put("message", e.getMessage()+"！");
//            }
//        }else{
//            jsonObject.put("status", "failure");
//            jsonObject.put("message", "输入的电子邮箱不存在！");
//        }
//        return jsonObject.toString();
//    }

//    @ResponseBody   //--- /xxx/register/{profile}
//    @RequestMapping(value = "/register/{profile}",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    public String doRegister(@PathVariable("profile") String profile,@RequestBody String input) {
//        JSONObject jsonObject = new JSONObject(input);
//        User user = null;
//        try {
//            user = loginService.getUserById(jsonObject.getString("username"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(user == null){
//            try {
//                loginService.insertUser(profile,jsonObject);
//                profileService.insertProfile(profile,jsonObject);
//                jsonObject.put("status", "success");
//                jsonObject.put("message", "已经注册成功，可以随时登录了！");
//            } catch (Exception e) {
//                jsonObject.put("status", "failure");
//                jsonObject.put("message", "数据库操作失败，请与管理员联系！");
//                e.printStackTrace();
//            }
//        }else{
//            jsonObject.put("status", "failure");
//            jsonObject.put("message", "该用户名已经存在了！");
//        }
//        return jsonObject.toString();
//    }
}