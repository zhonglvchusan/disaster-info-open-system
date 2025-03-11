package wang.zehui.disasterinfoopensystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import wang.zehui.disasterinfoopensystem.pojo.Rescue_Need;

import java.util.List;

@Mapper
public interface IRescueDao {

    @Select("select count(*) from rescue_need where status = #{status}")
    int SelectTotalNum(int status);

    @Select("select * from rescue_need where status = 1 order by age desc")
    List<Rescue_Need> selectByPage();

    @Select("select * from rescue_need where status = 2 and rescuename = #{rescueName}")
    List<Rescue_Need> selectMyByPage(String rescueName);

    @Update("update rescue_need set rescuename = #{RescueName}, status = 2  where id = #{id}")
    int updateRescueName(@Param("id")int id, @Param("RescueName") String RescueName);

    @Update("update rescue_need set status = 3 where id = #{id}")
    int updateRescueStatus(int id);
}
