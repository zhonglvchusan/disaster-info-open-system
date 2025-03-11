package wang.zehui.disasterinfoopensystem.pojo;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;

import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class Image {

    @Id
    @Column(isNull = false, isAutoIncrement = true, isKey = true)
    private int id;

    @Column(comment = "图片名称")
    private String title;

    @Column(comment = "图片存储的url地址")
    private String url;

    @Column(comment = "图片的存储时间戳")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
