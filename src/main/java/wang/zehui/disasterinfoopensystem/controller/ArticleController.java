package wang.zehui.disasterinfoopensystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wang.zehui.disasterinfoopensystem.pojo.AuthoritativeRelease;
import wang.zehui.disasterinfoopensystem.pojo.PiyaoPage;
import wang.zehui.disasterinfoopensystem.pojo.RescuePage;
import wang.zehui.disasterinfoopensystem.pojo.User;
import wang.zehui.disasterinfoopensystem.service.IArticleService;
import wang.zehui.disasterinfoopensystem.utils.DateTransformationUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: wangxiaobai
 * @date: 2022/4/11 15:27
 * @Description: 首页权威发布与救援情况板块的请求接收与处理
 */
@RestController
@RequestMapping("/Article")
public class ArticleController {

    @Autowired
    IArticleService iArticleService;

    /**
     * @author: wangxiaobai
     * @date: 2022/4/11 16:17
     * @Description: 获取首页救援情况展示的7条数据(id, title, date)
     */
    @GetMapping("/selectRescue")
    public List<RescuePage> SelectRescue() {

        List<RescuePage> rescuePageList = iArticleService.SelectRescue();
        //遍历列表，对时间戳进行处理，转化为yyyy-MM-dd HH:mm:ss格式传给前端
        rescuePageList.forEach(i -> {
            try {
                i.setDate(DateTransformationUtils.stamptoDate(i.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return rescuePageList;
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/4/11 18:20
     * @Description: 获取首页权威发布展示的7条数据(id, title, date)
     */
    @GetMapping("/selectRelease")
    public List<AuthoritativeRelease> SelectRelease() {

        List<AuthoritativeRelease> authoritativeReleaseList = iArticleService.SelectRelease();

        authoritativeReleaseList.forEach(i -> {
            try {
                i.setDate(DateTransformationUtils.stamptoDate(i.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return authoritativeReleaseList;
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/4/14 13:40
     * @Description: 根据前端传回的分页数据，提交service层处理
     */
    @GetMapping("/selectReleaseByPage")
    public List<AuthoritativeRelease> selectReleaseByPage(int currPage, int pageSize){
        //获取分页数据
        List<AuthoritativeRelease> authoritativeReleaseList = iArticleService.selectReleaseByPage(currPage, pageSize);

        authoritativeReleaseList.forEach(i -> {
            try {
                i.setDate(DateTransformationUtils.stamptoDate(i.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return authoritativeReleaseList;
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/4/14 14:23
     * @Description: 返回所有的总数
     */
    @GetMapping("/selectAllNum")
    public int selectReleaseAllNum(){
        return iArticleService.selectReleaseAllNum();
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/4/14 14:36
     * @Description: 返回所有救援情况的总数
     */
    @GetMapping("/selectRescueAllNum")
    public int selectRescueAllNum(){
        return iArticleService.selectRescueAllNum();
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/4/14 14:38
     * @Description: 根据前端传回的分页数据，提交service层处理
     */
    @GetMapping("/selectRescueByPage")
    public List<RescuePage> selectRescueByPage(int currPage, int pageSize){
        //获取分页数据
        List<RescuePage> rescuePageList = iArticleService.selectRescueByPage(currPage, pageSize);

        rescuePageList.forEach(i -> {
            try {
                i.setDate(DateTransformationUtils.stamptoDate(i.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return rescuePageList;
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/4/14 15:20
     * @Description: 根据id查询文章，返回详细数据生成页面
     */
    @GetMapping("/detail/{flag}/{id}")
    public ModelAndView selectReleaseById(@PathVariable String flag ,@PathVariable int id) throws Exception {
        //创建ModelAndView对象，返回共享数据以及页面
        ModelAndView mv = new ModelAndView();

        if(flag.equals("auth")){
            AuthoritativeRelease authoritativeRelease = iArticleService.selectReleaseById(id);
            authoritativeRelease.setDate(DateTransformationUtils.stamptoDate(authoritativeRelease.getDate()));
            //设定返回页面
            mv.setViewName("details");

            //添加共享数据
            mv.addObject("data", authoritativeRelease);
        }

        if(flag.equals("rescue")){
            RescuePage rescuePage = iArticleService.selectRescueById(id);
            rescuePage.setDate(DateTransformationUtils.stamptoDate(rescuePage.getDate()));
            //设定返回页面
            mv.setViewName("details");

            //添加共享数据
            mv.addObject("data", rescuePage);
        }

        if(flag.equals("piyao")){
            PiyaoPage piyaoPage = iArticleService.selectPiyaoPageById(id);
            piyaoPage.setName("政府办公室");
            piyaoPage.setDate(DateTransformationUtils.stamptoDate(piyaoPage.getDate()));
            //设定返回页面
            mv.setViewName("details");

            //添加共享数据
            mv.addObject("data", piyaoPage);
        }

        return mv;
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/22 17:45
     * @Description:
     */
    @PostMapping("/rescueCommit")
    public String insertRescue(RescuePage rescuePage, HttpServletRequest request){
        User sessionUser = (User) request.getSession().getAttribute("user");
        rescuePage.setName(sessionUser.getDisplayName());
        rescuePage.setDate(String.valueOf(System.currentTimeMillis()));


        if(iArticleService.insertRescue(rescuePage)){
            return "success";
        }

        return "fail";
    }

}
