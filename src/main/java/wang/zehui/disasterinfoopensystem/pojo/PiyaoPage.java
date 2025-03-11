package wang.zehui.disasterinfoopensystem.pojo;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "PiyaoPage")
public class PiyaoPage implements Comparable<PiyaoPage> {

    @Id
    @Column(isKey = true,isAutoIncrement = true,isNull = false)
    private int id;

    @Column(comment = "发表文章的标题")
    private String Title;

    @Column(comment = "辟谣内容", length = 1000)
    private String Msg;

    @Column(comment = "发表时间的时间戳", length = 100)
    private String date;

    private String name;

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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(PiyaoPage o) {
        return (int) (Long.parseLong(this.date) - Long.parseLong(o.date));
    }
}
