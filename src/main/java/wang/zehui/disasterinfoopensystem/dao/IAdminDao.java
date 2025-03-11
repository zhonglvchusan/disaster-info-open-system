package wang.zehui.disasterinfoopensystem.dao;

import org.apache.ibatis.annotations.*;
import wang.zehui.disasterinfoopensystem.pojo.*;

import java.util.List;

@Mapper
public interface IAdminDao {

    @Insert("insert into a_release (title, msg, date, name) values (#{Title}, #{Msg}, #{date}, #{Name})")
    int insertArticle(AuthoritativeRelease authoritativeRelease);

    @Delete("delete from a_release where id = #{id}")
    int deleteArticleById(int id);

    @Select("select * from a_release order by date desc")
    List<AuthoritativeRelease> selectAuthByPage();

    @Select("select * from material_need where status = 0")
    List<Material_Need> selectMaterialNeedByPage();

    @Update("update material_need set status = 1 where id = #{id}")
    int updateMaterialStatusById(int id);

    @Select("select * from rescue_need where status = 0")
    List<Rescue_Need> selectRescueNeedByPage();

    @Update("update rescue_need set status = 1 where id = #{id}")
    int updateRescueStatusById(int id);

    @Select("select * from piyaopage order by date desc")
    List<PiyaoPage> selectPiyaoPageByPage();

    @Insert("insert into piyaoPage (title, date, msg) values (#{Title}, #{date}, #{Msg})")
    int insertPiyaoPage(PiyaoPage piyaoPage);

    @Delete(("delete from piyaoPage where id = #{id}"))
    int deletePiyaoPageById(int id);

    @Select("select id, username, `right`, email, displayname from user where `right` != 'admin'")
    List<User> selectUserByPage();

    @Update("update user set `right` = #{right} where id = #{id}")
    int updateUserRightById(@Param("id") int id, @Param("right") String right);

    @Insert("insert into image (title, url, date) values (#{title}, #{url}, #{date})")
    int insertImg(Image image);

    @Select("select count(*) from ${tableName}")
    int selectAllNum(String tableName);

    @Select("select count(*) from ${tableName} where status = 0")
    int selectAllNum1(String tableName);
}
