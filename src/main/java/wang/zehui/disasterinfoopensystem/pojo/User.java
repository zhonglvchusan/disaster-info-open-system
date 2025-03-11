package wang.zehui.disasterinfoopensystem.pojo;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "User")
public class User {

    @Id
    @Column(length = 10, isNull = false, isKey = true, isAutoIncrement = true)
    private int id;

    @Column(isNull = false)
    @Unique
    private String username;

    @Column
    private String password;

    @Column
    private String right;

    @Column
    @Unique
    private String email;

    @Column(value = "displayname")
    private String displayName;

    @Column
    private String sex;

    @Column
    private String area;

    public User(String username, String password, String right, String displayName) {
        this.username = username;
        this.password = password;
        this.right = right;
        this.displayName = displayName;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
