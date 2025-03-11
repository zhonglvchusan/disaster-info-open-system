package wang.zehui.disasterinfoopensystem.service;

import wang.zehui.disasterinfoopensystem.pojo.Rescue_Need;

import java.util.List;

public interface IRescueService {

    int SelectTotalNum(int status);

    List<Rescue_Need> selectByPage(int currPage);

    List<Rescue_Need> selectMyByPage(int currPage, String rescueName);

    boolean updateRescueName(int id, String RescueName);

    boolean updateRescueStatus(int id);
}
