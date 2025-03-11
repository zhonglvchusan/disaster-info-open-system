package wang.zehui.disasterinfoopensystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wang.zehui.disasterinfoopensystem.pojo.PiyaoPage;

import java.util.List;

@Mapper
public interface IPiyaoDao {

    @Select("select count(*) from piyaopage")
    int selectTotalNum();

    @Select("select date from piyaopage")
    List<String> selectYesTotalNum();

    @Select("select * from piyaopage")
    List<PiyaoPage> getListByPage();
}
