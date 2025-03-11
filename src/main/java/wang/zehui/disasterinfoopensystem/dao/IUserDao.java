package wang.zehui.disasterinfoopensystem.dao;

import org.apache.ibatis.annotations.*;
import wang.zehui.disasterinfoopensystem.pojo.Rescue_Need;
import wang.zehui.disasterinfoopensystem.pojo.User;
import wang.zehui.disasterinfoopensystem.pojo.roleModel;

import java.util.List;

@Mapper
public interface IUserDao {

    @Insert("insert into user (username, user.`password`, user.`right`, displayname) values (#{username}, #{password}, #{right}, #{displayName})")
    int UserRegister(User user);

    @Select("select username from user")
    List<String> SelectUsername();

    @Select("select * from user where username = #{username}")
    User UserLogin(String username);

    @Select("select * from rolemodel where rolemodel.`right` = #{right}")
    List<roleModel> getRightModel(String right);

    @Select("select id, username, email, displayname, sex, area from user where id = #{id}")
    User getLoginUserInfo(String id);

    @Update("update user set username = #{username}, email = #{email}, displayname = #{displayName}, sex = #{sex}, area = #{area} where id = #{id}")
    int updateUserInfo(User user);

    //当使用多个值传参时，需使用@param注解进行命名
    //底层使用map，多个参数不加注解时，使用args【0】....
    @Select("select username from rescue_need where name = #{name} and age = #{age}")
    Rescue_Need CheckMsg(@Param("name") String name, @Param("age") int age);

    @Insert("insert into rescue_need (name, age, telnum, place, msg, username, status) values (#{name}, #{age}, #{telNum}, #{place}, #{msg}, #{username}, 0)")
    int commitMsg(Rescue_Need rescueNeed);
}
