package wang.zehui.disasterinfoopensystem.pojo;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "RescuePage")
public class RescuePage {

    @Id
    @Column(isKey = true,isAutoIncrement = true,isNull = false)
    private int id;

    @Column(comment = "发表文章的标题")
    private String Title;

    @Column(comment = "内容", length = 1000)
    private String Msg;

    @Column(comment = "发表时间的时间戳", length = 100)
    private String date;

    @Column(comment = "发布文章的救援队名称", value = "Name")
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

    public void setName(String rescueName) {
        this.Name = rescueName;
    }
}
