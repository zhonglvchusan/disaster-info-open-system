package wang.zehui.disasterinfoopensystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.zehui.disasterinfoopensystem.pojo.Image;
import wang.zehui.disasterinfoopensystem.pojo.MessageBoard;
import wang.zehui.disasterinfoopensystem.pojo.User;
import wang.zehui.disasterinfoopensystem.service.IMessageBoardService;
import wang.zehui.disasterinfoopensystem.utils.DateTransformationUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: wangxiaobai
 * @date: 2022/4/11 21:26
 * @Description: 留言板实现
 */
@RestController
@RequestMapping("/Board")
public class MessageBoardController {

    @Autowired
    IMessageBoardService iMessageBoardService;

    /**
     * @author: wangxiaobai
     * @date: 2022/4/11 21:26
     * @Description: 发表按钮实现，添加成功返回影响的行数，失败返回0
     */
    @GetMapping("/commit")
    public int commitMsg(String message, HttpServletRequest request) throws Exception {
        User sessionUser = (User) request.getSession().getAttribute("user");

        String displayName = null;
        //从session中获取评论用户的名称
        if (sessionUser != null) {
            displayName = sessionUser.getDisplayName();
        }

        //获取当前评论时间戳
        String date = String.valueOf(System.currentTimeMillis());

        if (displayName != null) {
            MessageBoard messageBoard = new MessageBoard(displayName, message, date);
            return iMessageBoardService.commitMsg(messageBoard);
        }

        return 0;
    }


    /**
     * @author: wangxiaobai
     * @date: 2022/4/12 17:53
     * @Description: 获取前端分页传来的第几页以及每页显示数量，提交service层处理
     */
    @RequestMapping("/listByPage")
    public List<MessageBoard> selectByPage(int currPage, int pageSize){
        //获取到分页数据
        List<MessageBoard> messageBoardList = iMessageBoardService.selectByPage(currPage, pageSize);

        //将时间戳转化成yyyy-MM-dd HH:mm:ss格式返回
        messageBoardList.forEach(i -> {
            try {
                i.setMsgDate(DateTransformationUtils.stamptoDate(i.getMsgDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return messageBoardList;
    }

    @GetMapping("/listNum")
    public int selectAllNumber(){
        return iMessageBoardService.selectAllNumber();
    }

    @GetMapping("/LunBo")
    public List<Image> selectIndex(){
        return iMessageBoardService.selectIndex();
    }

}
