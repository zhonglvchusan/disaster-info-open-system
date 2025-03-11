package wang.zehui.disasterinfoopensystem.service;

import wang.zehui.disasterinfoopensystem.pojo.Image;
import wang.zehui.disasterinfoopensystem.pojo.MessageBoard;

import java.util.List;

public interface IMessageBoardService {

    int commitMsg(MessageBoard messageBoard);

    List<MessageBoard> selectByPage(int firstPage, int lastPage);

    int selectAllNumber();

    List<Image> selectIndex();
}
