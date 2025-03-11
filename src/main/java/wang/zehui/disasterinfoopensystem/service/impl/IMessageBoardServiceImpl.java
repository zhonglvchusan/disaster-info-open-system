package wang.zehui.disasterinfoopensystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.zehui.disasterinfoopensystem.dao.IMessageBoardDao;
import wang.zehui.disasterinfoopensystem.pojo.Image;
import wang.zehui.disasterinfoopensystem.pojo.MessageBoard;
import wang.zehui.disasterinfoopensystem.service.IMessageBoardService;

import java.util.List;

@Service
public class IMessageBoardServiceImpl implements IMessageBoardService {

    @Autowired
    IMessageBoardDao iMessageBoardDao;


    @Override
    public int commitMsg(MessageBoard messageBoard) {
        return iMessageBoardDao.commitMsg(messageBoard);
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/4/12 17:51
     * @Description: 数组实现分页方法
     */
    @Override
    public List<MessageBoard> selectByPage(int currPage, int pageSize) {

        //获取所有数据库中的数据
        List<MessageBoard> messageBoardList = iMessageBoardDao.selectByPage();

        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //到第几条数据结束
        int lastIndex = currPage * pageSize;
        if(lastIndex > messageBoardList.size()){
            lastIndex = messageBoardList.size();
        }

        //通过subList方法返回区间内数据
        return messageBoardList.subList(firstIndex, lastIndex);
    }

    @Override
    public int selectAllNumber() {
        return iMessageBoardDao.selectAllNumber();
    }

    @Override
    public List<Image> selectIndex() {
        return iMessageBoardDao.selectIndex().subList(0,3);
    }


}
