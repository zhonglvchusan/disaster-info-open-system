package wang.zehui.disasterinfoopensystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: wangxiaobai
 * @date: 2022/3/17 9:13
 * @Description: 页面返回控制器
*/
@Controller
public class PageController {

    @RequestMapping("/login")
    public String UserLogin(){
        return "login";
    }

    @RequestMapping("/")
    public String Index(){
        return "index";
    }

    @RequestMapping("/index")
    public String Index1(){
        return "index";
    }

    @RequestMapping("/piyao")
    public String Piyao(){
        return "piyao";
    }

    @RequestMapping("/authoritative")
    public String authoritative(){
        return "authoritative";
    }

    @RequestMapping("/rescue")
    public String rescue(){
        return "rescue";
    }

    @RequestMapping("/update")
    public String update(){
        return "userInfo";
    }

    @RequestMapping("/Error")
    public String ErrorPage(){
        return "error";
    }

    @RequestMapping("/relation")
    public String relationPage(){
        return "relation";
    }

    @RequestMapping("/rescueCommit")
    public String rescueCommitPage(){
        return "rescueCommit";
    }

    @RequestMapping("/rescueNeed")
    public String rescueNeedPage(){
        return "rescue_need";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}
