package wang.zehui.disasterinfoopensystem.pojo;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "message_board")
public class MessageBoard {

    @Column(isKey = true, isAutoIncrement = true, isNull = false)
    private int id;

    @Column(comment = "留言用户用户名", value = "displayName")
    private String displayName;

    @Column(length = 1000, comment = "用户留言内容")
    private String Msg;

    @Column(comment = "用户留言时间的时间戳", value = "MsgDate", length = 100)
    private String MsgDate;

    public MessageBoard(String displayName, String msg, String msgDate) {
        this.displayName = displayName;
        Msg = msg;
        MsgDate = msgDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getMsgDate() {
        return MsgDate;
    }

    public void setMsgDate(String msgDate) {
        MsgDate = msgDate;
    }
}
