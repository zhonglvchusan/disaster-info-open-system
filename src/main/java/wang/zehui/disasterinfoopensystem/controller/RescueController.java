package wang.zehui.disasterinfoopensystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.zehui.disasterinfoopensystem.pojo.Rescue_Need;
import wang.zehui.disasterinfoopensystem.pojo.User;
import wang.zehui.disasterinfoopensystem.service.IRescueService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: wangxiaobai
 * @date: 2022/5/24 16:10
 * @Description: 救援队待救援组织模块实现
 */
@RestController
@RequestMapping("/Rescue")
public class RescueController {

    @Autowired
    IRescueService iRescueService;

    /**
     * @author: wangxiaobai
     * @date: 2022/5/24 16:11
     * @Description: 查询总页数
     */
    @GetMapping("/selectTotalNum")
    public int SelectTotalNum(int status){
        return iRescueService.SelectTotalNum(status);
    }


    @GetMapping("/selectByPage")
    public List<Rescue_Need> selectByPage(int currPage, int status, HttpServletRequest request){

        User sessionUser = (User) request.getSession().getAttribute("user");

        if(status == 1){
            return iRescueService.selectByPage(currPage);
        }

        if(status == 2){
            return iRescueService.selectMyByPage(currPage, sessionUser.getDisplayName());
        }

        return null;
    }

    @GetMapping("/go")
    public String updateRescueName(int id, HttpServletRequest request){

        User sessionUser = (User) request.getSession().getAttribute("user");

        if (iRescueService.updateRescueName(id, sessionUser.getDisplayName())){
            return  "success";
        }

        return "fail";
    }

    @GetMapping("/end")
    public String updateRescueStatus(int id){
        if(iRescueService.updateRescueStatus(id)){
            return "success";
        }

        return "fail";

    }

}
