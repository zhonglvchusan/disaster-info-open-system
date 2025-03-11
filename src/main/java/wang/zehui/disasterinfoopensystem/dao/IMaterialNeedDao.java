package wang.zehui.disasterinfoopensystem.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import wang.zehui.disasterinfoopensystem.pojo.Material_Need;

import java.util.List;

@Mapper
public interface IMaterialNeedDao {

    @Select("select * from material_need where status = 1 limit 5")
    List<Material_Need> selectIndexMaterial();

    @Select("select id from material_need where material_name = #{material_name} and area = #{Area} and status != 2")
    List<Integer> Check(Material_Need materialNeed);

    @Insert("insert into material_need (material_name, number, area, `status`) values (#{material_name}, #{number}, #{Area}, 0)")
    int commitMaterial(Material_Need materialNeed);

    @Update("update material_need set number = number + #{number} where material_name = #{material_name} and area = #{Area} and status = 0")
    int commitAgain(Material_Need materialNeed);
}
