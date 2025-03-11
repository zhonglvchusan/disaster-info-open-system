package wang.zehui.disasterinfoopensystem.pojo;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "rolemodel")
public class roleModel {

    @Id
    @Column(length = 10, isNull = false, isKey = true, isAutoIncrement = true)
    private int id;

    @Column(value = "modelName")
    private String modelName;

    @Column(value = "modelPath")
    private String modelPath;

    @Column
    private String right;


    public roleModel() {
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

}
