package wang.zehui.disasterinfoopensystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.zehui.disasterinfoopensystem.dao.IUserDao;
import wang.zehui.disasterinfoopensystem.pojo.Rescue_Need;
import wang.zehui.disasterinfoopensystem.pojo.User;
import wang.zehui.disasterinfoopensystem.pojo.roleModel;
import wang.zehui.disasterinfoopensystem.service.IUserService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDao iUserDao;

    @Override
    @Transactional
    public int UserRegister(User user) {
        return iUserDao.UserRegister(user);
    }

    @Override
    public List<String> SelectUsername() {
        return iUserDao.SelectUsername();
    }

    @Override
    public User UserLogin(String username) {
        return iUserDao.UserLogin(username);
    }

    @Override
    public Map<String, String> getRightModel(String right) {

        Map<String, String> mapModel = new HashMap<>();
        List<roleModel> list = iUserDao.getRightModel(right);
        for (roleModel roleModel : list) {
            mapModel.put(roleModel.getModelName(), roleModel.getModelPath());
        }

        return mapModel;
    }

    @Override
    public User getLoginUserInfo(String id) {
        return iUserDao.getLoginUserInfo(id);
    }

    @Override
    public int updateUserInfo(User user) {
        return iUserDao.updateUserInfo(user);
    }

    @Override
    public String CheckMsg(String name, int age) {
        String Msg = "null";
        if (iUserDao.CheckMsg(name, age) != null){
            Msg = iUserDao.CheckMsg(name, age).getUsername();
        }

        return Msg;
    }

    @Override
    public boolean commitMsg(Rescue_Need rescueNeed) {
        return iUserDao.commitMsg(rescueNeed) > 0;
    }

}
