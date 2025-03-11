package wang.zehui.disasterinfoopensystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.zehui.disasterinfoopensystem.pojo.Material_Need;
import wang.zehui.disasterinfoopensystem.service.IMaterialNeedService;

import java.util.List;

@RestController
@RequestMapping("/Material")
public class MaterialNeedController {

    @Autowired
    IMaterialNeedService iMaterialNeedService;

    /**
     * @author: wangxiaobai
     * @date: 2022/4/13 13:11
     * @Description: 返回首页展示的急需物资列表
     */
    @GetMapping("/IndexMaterial")
    public List<Material_Need> selectIndexMaterial(){
        return iMaterialNeedService.selectIndexMaterial();
    }




    /**
     * @author: wangxiaobai
     * @date: 2022/5/20 20:50
     * @Description: 检查物资申请是否提交过,提交过返回false
     */
    public boolean Check(Material_Need materialNeed){
        return iMaterialNeedService.Check(materialNeed);
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/21 12:22
     * @Description: 提交物资
     */
    @PostMapping("/commit")
    public String commitMaterial(Material_Need materialNeed){
        //调用方法检测是否提交过
        if(!Check(materialNeed)){
            return "exist";
        }

        if(iMaterialNeedService.commitMaterial(materialNeed)){
            return "success";
        }

        return "fail";
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/21 14:03
     * @Description: 当用户选择继续提交时，请求该方法
     */
    @PostMapping("/again")
    public String commitAgain(Material_Need materialNeed){
        if(iMaterialNeedService.commitAgain(materialNeed)){
            return "success";
        }
        return "fail";
    }
}
