package wang.zehui.disasterinfoopensystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.zehui.disasterinfoopensystem.pojo.PiyaoPage;
import wang.zehui.disasterinfoopensystem.service.IPiyaoService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/Piyao")
public class PiyaoController {

    @Autowired
    IPiyaoService iPiyaoService;

    /**
     * @author: wangxiaobai
     * @date: 2022/5/25 22:27
     * @Description: 辟谣总数
     */
    @GetMapping("/totalNum")
    public int selectTotalNum(){
        return iPiyaoService.selectTotalNum();
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/25 22:28
     * @Description: 昨天辟谣的总数
     */
    @GetMapping("/yesTotalNum")
    public int selectYesTotalNum() throws ParseException {
        return iPiyaoService.selectYesTotalNum();
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/5/26 8:18
     * @Description: 获取分页数据
     */
    @GetMapping("/pageData")
    public List<PiyaoPage> getListByPage(int currPage){
        return iPiyaoService.getListByPage(currPage);
    }

}
