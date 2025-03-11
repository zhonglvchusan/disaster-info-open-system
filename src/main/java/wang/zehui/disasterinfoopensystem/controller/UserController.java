package wang.zehui.disasterinfoopensystem.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.zehui.disasterinfoopensystem.pojo.Rescue_Need;
import wang.zehui.disasterinfoopensystem.pojo.User;
import wang.zehui.disasterinfoopensystem.service.IUserService;
import wang.zehui.disasterinfoopensystem.utils.Md5Util;
import wang.zehui.disasterinfoopensystem.utils.SendMessageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangxiaobai
 * @date: 2022/3/17 10:19
 * @Description: 实现用户操作的控制器
 */
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    IUserService iUserService;

    /**
     * @author: wangxiaobai
     * @date: 2022/3/18 10:00
     * @Description: 登录实现，获取用户名密码，成功时返回
     * @return
     */
    @PostMapping("/login")
    public String UserLogin(String username, String password, HttpServletRequest request) throws ServletException, IOException {

        List<String> listMsg = new ArrayList<>();
        List<String> listCheck = SelectUsername();

        User user = null;

        //去除用户名密码前后的空格
        username = username.trim();
        password = password.trim();

        //对密码进行md5加密
        password = Md5Util.md5Encrypt32Lower(password);

        //判断用户名是否存在
        if (!listCheck.contains(username)) {
            return null;
        }

        HttpSession session = request.getSession();

        Object userInfo = session.getAttribute("user");

        User sessionUser = new User();

        //判断之前是否登录
        if (userInfo != null) {
            sessionUser = (User) userInfo;
        }
        if (sessionUser != null && username.equalsIgnoreCase(sessionUser.getUsername())) {
            //list添加成功消息返回前台
            session.setAttribute("user", sessionUser);
            listMsg.add(sessionUser.getRight());
            listMsg.add(sessionUser.getDisplayName());
        } else {
            user = iUserService.UserLogin(username);

            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                listMsg.add(String.valueOf(user.getId()));
                listMsg.add(user.getRight());
                listMsg.add(user.getDisplayName());
            } else {
                listMsg.add("密码错误");
            }
        }
        Gson gson = new Gson();

        return gson.toJson(listMsg);
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/3/17 14:08
     * @Description: 请求验证码
     */
    @RequestMapping("/check")
    public String UserCheck(String tel) {
//        System.out.println(tel);
        String check = SendMessageUtil.getRandomCode(6);
//        Integer num = 1;
        Integer num = SendMessageUtil.send("3364462922@qq.com", "d41d8cd98f00b204e980", tel, "您的验证码是:" + check);
        if (num > 0) {
            return check;
        }
        return SendMessageUtil.getMessage(num);
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/3/17 17:29
     * @Description: 请求用户名是否重复
     */
    @GetMapping("/username")
    public List<String> SelectUsername() {
        return iUserService.SelectUsername();
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/3/17 16:10
     * @Description: 验证码正确时，将用户注册信息存入数据库
     */
    @PostMapping("/register")
    public int UserRegister(String tel, String password, String right) {
        //对密码使用md5加密方式存入数据库
        password = Md5Util.md5Encrypt32Lower(password);

        String displayname = "用户" + System.currentTimeMillis();
        User user = new User(tel, password, right, displayname);

        int k = iUserService.UserRegister(user);
        if (k > 0) {
            return k;
        } else {
            return 0;
        }
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/3/18 14:08
     * @Description: 注销登录
     */
    @GetMapping("/logout")
    public String UserLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        session.removeAttribute("user");
        response.sendRedirect("/index");
        return "ok";

    }

    /**
     * @author: wangxiaobai
     * @date: 2022/3/18 19:43
     * @Description: 返回权限对应页面请求路径
     */
    @PostMapping("/model")
    public String getRightModel(String right, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return null;
        }

        if(!user.getRight().equals(right)){
            return "非法请求";
        }

        Gson gson = new Gson();
        return gson.toJson(iUserService.getRightModel(right));
    }

    @GetMapping("/getInfo")
    public User getLoginUserInfo(String Id){
        return iUserService.getLoginUserInfo(Id);
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/7 19:10
     * @Description: 修改用户信息
     */
    @PostMapping("/update")
    public String updateUserInfo(User user){
        String Msg = "";

        if(user.getUsername().isEmpty() || user.getDisplayName().isEmpty() || user.getArea().isEmpty() || user.getEmail().isEmpty() || user.getSex().isEmpty()){
            return Msg = "修改出错，请联系管理员！";
        }

        int k = iUserService.updateUserInfo(user);
        if(k > 0){
            Msg = "修改成功";
        }

        return Msg;
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/8 22:06
     * @Description: 检验待救援信息是否被提交过,若被提交过，返回提交此信息用户的用户名
     */
    public String CheckMsg(String name, int age){
        return iUserService.CheckMsg(name, age);
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/18 20:13
     * @Description: 提交待救援信息
     */
    @PostMapping("/commit")
    public String commitMsg(Rescue_Need rescueNeed, HttpServletRequest request){

        //调用方法查询是否被其他用户提交过，提交过后返回提交的用户名至前端
        if(!CheckMsg(rescueNeed.getName(), rescueNeed.getAge()).equals("null")){
            return CheckMsg(rescueNeed.getName(), rescueNeed.getAge());
        }

        //获取提交的用户的用户名，并封装入对象中
        User sessionUser = (User) request.getSession().getAttribute("user");
        rescueNeed.setUsername(sessionUser.getDisplayName());

        if(iUserService.commitMsg(rescueNeed)){
            return "success";
        }

        return "fail";
    }

}
