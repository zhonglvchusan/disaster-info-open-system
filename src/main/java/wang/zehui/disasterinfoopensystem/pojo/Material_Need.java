package wang.zehui.disasterinfoopensystem.pojo;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "material_need")
public class Material_Need {

    @Id
    @Column(isKey = true, isAutoIncrement = true, isNull = false)
    private int id;

    @Column(comment = "急需物资名称")
    private String material_name;

    @Column(comment = "急需物资数量")
    private int number;

    @Column(comment = "所需物资地区", isNull = false)
    private String Area;

    @Column(comment = "物资状态，已被捐赠为2，管理员审核完毕后为1，用户提交为0", isNull = false)
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
