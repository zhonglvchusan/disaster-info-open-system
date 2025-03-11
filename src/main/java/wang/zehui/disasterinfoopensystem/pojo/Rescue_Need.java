package wang.zehui.disasterinfoopensystem.pojo;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Table(name = "rescue_need")
public class Rescue_Need {

    @Id
    @Column(isNull = false, isAutoIncrement = true, isKey = true, comment = "信息id")
    private int id;

    @Column(comment = "待救援人员姓名")
    private String name;

    @Column(comment = "待救援人员年龄")
    private int age;

    @Column(comment = "联系电话", value = "telNum")
    private String telNum;

    @Column(comment = "待救援人员所在地点")
    private String place;

    @Column(comment = "对现场情况进行的一些简单描述，以及救援时需要的注意事项")
    private String msg;

    @Column(comment = "提交信息的用户名")
    private String username;

    @Column(comment = "救援状态，已被救助为3，救援组织正在施救为2，管理员审核完毕后为1，用户提交为0")
    private int status;

    @Column(value = "rescueName", comment = "施救救援队名称")
    private String rescueName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRescueName() {
        return rescueName;
    }

    public void setRescueName(String recuseName) {
        this.rescueName = recuseName;
    }

}
