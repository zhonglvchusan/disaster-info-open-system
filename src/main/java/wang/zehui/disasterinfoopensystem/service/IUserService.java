package wang.zehui.disasterinfoopensystem.service;

import wang.zehui.disasterinfoopensystem.pojo.Rescue_Need;
import wang.zehui.disasterinfoopensystem.pojo.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    //接收User对象存储
    int UserRegister(User user);

    List<String> SelectUsername();

    User UserLogin(String username);

    Map<String, String> getRightModel(String right);

    User getLoginUserInfo(String id);

    int updateUserInfo(User user);

    String CheckMsg(String name, int age);

    boolean commitMsg(Rescue_Need rescueNeed);
}
