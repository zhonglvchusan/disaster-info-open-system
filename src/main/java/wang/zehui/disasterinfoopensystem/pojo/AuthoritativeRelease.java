package wang.zehui.disasterinfoopensystem.pojo;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;

import javax.persistence.Table;

@Table(name = "a_release")
public class AuthoritativeRelease {

    @Column(isNull = false,isAutoIncrement = true,isKey = true)
    private int id;

    @Column(comment = "发布标题")
    private String Title;

    @Column(comment = "网页主要内容", length = 1000)
    private String Msg;

    @Column(comment = "发布时间的时间戳", length = 100)
    private String date;

    @Column(comment = "发布消息的管理员名称")
    private String Name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
