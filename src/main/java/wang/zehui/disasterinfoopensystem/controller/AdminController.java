package wang.zehui.disasterinfoopensystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wang.zehui.disasterinfoopensystem.pojo.*;
import wang.zehui.disasterinfoopensystem.service.IAdminService;
import wang.zehui.disasterinfoopensystem.utils.DateTransformationUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IAdminService iAdminService;

    /**
     * @author: wangxiaobai
     * @date: 2022/5/26 22:24
     * @Description: 权威发布界面发布文章接口
     */
    @PostMapping("/release")
    public String insertArticle(String title, String msg, HttpServletRequest request){
        String nowTime = String.valueOf(System.currentTimeMillis());
        User sessionUser = (User) request.getSession().getAttribute("user");

        AuthoritativeRelease authoritativeRelease = new AuthoritativeRelease();
        authoritativeRelease.setTitle(title);
        authoritativeRelease.setMsg(msg);
        authoritativeRelease.setDate(nowTime);
        authoritativeRelease.setName(sessionUser.getDisplayName());

        if(iAdminService.insertArticle(authoritativeRelease)){
            return "success";
        }

        return "fail";
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/26 22:36
     * @Description: 根据id删除文章
     */
    @DeleteMapping("/release/{id}")
    public String deleteArticleById(@PathVariable int id){

        if(iAdminService.deleteArticleById(id)){
            return "success";
        }

        return "fail";
    }

    @GetMapping("/selectAuthData")
    public List<AuthoritativeRelease> selectAuthByPage(int currPage){
        List<AuthoritativeRelease> authoritativeReleaseList = iAdminService.selectAuthByPage(currPage);
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
     * @date: 2022/5/27 11:15
     * @Description: 根据分页查询物资申请当页数据
     */
    @GetMapping("/selectMaterialNeedData")
    public List<Material_Need> selectMaterialNeedByPage(int currPage){
        return iAdminService.selectMaterialNeedByPage(currPage);
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/27 11:18
     * @Description: 根据分页查询待救援申请当页数据
     */
    @GetMapping("/selectRescueNeedData")
    public List<Rescue_Need> selectRescueNeedByPage(int currPage){
        return iAdminService.selectRescueNeedByPage(currPage);
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/27 11:19
     * @Description: 根据id改变对应状态
     */
    @GetMapping("/apply/{type}/{id}")
    public String updateStatusById(@PathVariable String type, @PathVariable int id){

        if(type.equals("material")){
            if(iAdminService.updateMaterialStatusById(id)){
                return "success";
            }
        }

        if(type.equals("rescue")){
            if(iAdminService.updateRescueStatusById(id)){
                return "success";
            }
        }

        return "fail";
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/27 17:29
     * @Description: 根据分页查询数据
     */
    @GetMapping("/selectPiyaoData")
    public List<PiyaoPage> selectPiyaoPageByPage(int currPage){
        List<PiyaoPage> piyaoPageList = iAdminService.selectPiyaoPageByPage(currPage);
        piyaoPageList.forEach(i -> {
            try {
                i.setDate(DateTransformationUtils.stamptoDate(i.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return piyaoPageList;
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/27 11:31
     * @Description: 添加辟谣文章
     */
    @PostMapping("/piyao")
    public String insertPiyaoPage(String title, String Msg){
        PiyaoPage piyaoPage = new PiyaoPage();

        System.out.println(title);
        System.out.println(Msg);

        piyaoPage.setTitle(title);
        piyaoPage.setDate(String.valueOf(System.currentTimeMillis()));
        piyaoPage.setMsg(Msg);

        if(iAdminService.insertPiyaoPage(piyaoPage)){
            return "success";
        }

        return "fail";
    }
    /**
     * @author: wangxiaobai
     * @date: 2022/5/27 11:49
     * @Description: 根据id删除辟谣文章
     */
    @DeleteMapping("/piyao/{id}")
    public String deletePiyaoPageById(@PathVariable int id){
        if(iAdminService.deletePiyaoPageById(id)){
            return "success";
        }

        return "fail";
    }

    @GetMapping("/selectUserData")
    public List<User> selectUserByPage(int currPage){

        List<User> userList = iAdminService.selectUserByPage(currPage);

        userList.forEach(i -> {
            if(i.getRight().equals("user")){
                i.setRight("普通用户");
            }
            if(i.getRight().equals("organization")){
                i.setRight("救援组织");
            }
        });

        return userList;
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/27 11:57
     * @Description: 根据id修改用户权限
     */
    @PostMapping("/adminUpdate")
    public String updateUserRightById(int id, String right){
        if(iAdminService.updateUserRightById(id, right)){
            return "success";
        }

        return "fail";
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/27 12:35
     * @Description: 上传图片
     */
    @PostMapping("/uploadImg")
    public String uploadImg(@RequestParam("file") MultipartFile file){

        Image image = new Image();
        if(file.isEmpty()){
            System.out.println("图片为空");
        }

        String filePath = "D:\\wangzehui\\DisasterInfoOpenSystem\\src\\main\\resources\\static\\img";


        File dir = new File(filePath);
        if (!dir.exists()){
            dir.mkdir();
        }

        String suffix = "";
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //获取后缀开始的索引值
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0){
            //获取后缀
            suffix = originalFilename.substring(beginIndex);
        }

        //文件名，防止文件名相同，使用时间戳命名
        String filename = System.currentTimeMillis() + suffix;

        //数据库存入文件标题
        image.setTitle(originalFilename.substring(0, beginIndex));
        image.setUrl("/image/" + System.currentTimeMillis() + suffix);
        image.setDate(String.valueOf(System.currentTimeMillis()));

        File dest = new File(dir, filename);

        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            if(iAdminService.insertImg(image)){
                file.transferTo(dest);
                return "success";
            }
            return "fail";

        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

    }

    @GetMapping("/num")
    public int selectAllNum(String name){
        return iAdminService.selectAllNum(name);
    }

    @GetMapping("/num1")
    public int selectAllNum1(String name){
        return iAdminService.selectAllNum1(name);
    }

}
