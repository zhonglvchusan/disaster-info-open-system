package wang.zehui.disasterinfoopensystem.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import wang.zehui.disasterinfoopensystem.pojo.Image;
import wang.zehui.disasterinfoopensystem.pojo.MessageBoard;

import java.util.List;

@Mapper
public interface IMessageBoardDao {

    @Insert("insert into message_board (displayname, msg, msgdate) values (#{displayName}, #{Msg}, #{MsgDate})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int commitMsg(MessageBoard messageBoard);

    @Select("select * from message_board order by msgdate desc")
    List<MessageBoard> selectByPage();

    @Select("select count(*) from message_board")
    int selectAllNumber();

    @Select("select * from image order by date desc")
    List<Image> selectIndex();
}
