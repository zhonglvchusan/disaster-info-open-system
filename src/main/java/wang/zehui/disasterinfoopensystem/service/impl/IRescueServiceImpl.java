package wang.zehui.disasterinfoopensystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.zehui.disasterinfoopensystem.dao.IRescueDao;
import wang.zehui.disasterinfoopensystem.pojo.Rescue_Need;
import wang.zehui.disasterinfoopensystem.service.IRescueService;

import java.util.List;

@Service
public class IRescueServiceImpl implements IRescueService {

    @Autowired
    IRescueDao iRescueDao;

    @Override
    public int SelectTotalNum(int status) {
        return iRescueDao.SelectTotalNum(status);
    }

    @Override
    public List<Rescue_Need> selectByPage(int currPage) {

        //获取全部数据
        List<Rescue_Need> rescueNeedList = iRescueDao.selectByPage();

        int firstIndex = (currPage - 1) * 10;
        int lastIndex = currPage * 10;
        if(lastIndex > rescueNeedList.size()){
            lastIndex = rescueNeedList.size();
        }

        return rescueNeedList.subList(firstIndex, lastIndex);
    }

    @Override
    public List<Rescue_Need> selectMyByPage(int currPage, String rescueName) {

        List<Rescue_Need> rescueNeedList = iRescueDao.selectMyByPage(rescueName);

        int firstIndex = (currPage - 1) * 10;
        int lastIndex = currPage * 10;
        if(lastIndex > rescueNeedList.size()){
            lastIndex = rescueNeedList.size();
        }

        return rescueNeedList.subList(firstIndex, lastIndex);
    }

    @Override
    public boolean updateRescueName(int id, String RescueName) {
        return iRescueDao.updateRescueName(id, RescueName) > 0;
    }

    @Override
    public boolean updateRescueStatus(int id) {
        return iRescueDao.updateRescueStatus(id) > 0;
    }
}
