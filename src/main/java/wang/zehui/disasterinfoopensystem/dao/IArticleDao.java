package wang.zehui.disasterinfoopensystem.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wang.zehui.disasterinfoopensystem.pojo.AuthoritativeRelease;
import wang.zehui.disasterinfoopensystem.pojo.PiyaoPage;
import wang.zehui.disasterinfoopensystem.pojo.RescuePage;

import java.util.List;

@Mapper
public interface IArticleDao {

    @Select("select id, title, date from rescuepage order by date desc limit 7")
    List<RescuePage> SelectRescue();

    @Select("select id, title, date from a_release order by date desc limit 7")
    List<AuthoritativeRelease> SelectRelease();

    @Select("select * from a_release order by date desc")
    List<AuthoritativeRelease> selectReleaseByPage();

    @Select("select * from rescuepage order by date desc")
    List<RescuePage> selectRescueByPage();

    @Select("select count(*) from a_release")
    int selectReleaseAllNum();

    @Select("select count(*) from rescuepage")
    int selectRescueAllNum();

    @Select("select title, msg, date, name from a_release where id = #{id}")
    AuthoritativeRelease selectReleaseById(int id);

    @Select("select title, msg, date, name from rescuepage where id = #{id}")
    RescuePage selectRescueById(int id);

    @Select("select title, msg, date from piyaopage where id = #{id}")
    PiyaoPage selectPiyaoPageById(int id);

    @Insert("insert into rescuepage (title, date, msg, name) values (#{Title}, #{date}, #{Msg}, #{Name})")
    int insertRescue(RescuePage rescuePage);
}
