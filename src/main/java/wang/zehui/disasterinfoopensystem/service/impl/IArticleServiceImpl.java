package wang.zehui.disasterinfoopensystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.zehui.disasterinfoopensystem.dao.IArticleDao;
import wang.zehui.disasterinfoopensystem.pojo.AuthoritativeRelease;
import wang.zehui.disasterinfoopensystem.pojo.PiyaoPage;
import wang.zehui.disasterinfoopensystem.pojo.RescuePage;
import wang.zehui.disasterinfoopensystem.service.IArticleService;

import java.util.List;

@Service
public class IArticleServiceImpl implements IArticleService {

    @Autowired
    IArticleDao iArticleDao;

    @Override
    public List<RescuePage> SelectRescue() {
        return iArticleDao.SelectRescue();
    }

    @Override
    public List<AuthoritativeRelease> SelectRelease() {
        return iArticleDao.SelectRelease();
    }

    /**
     * @author: wangxiaobai
     * @date: 2022/4/14 13:35
     * @Description: 数组实现分页
     */
    @Override
    public List<AuthoritativeRelease> selectReleaseByPage(int currPage, int pageSize) {

        //获取全部结果
        List<AuthoritativeRelease> authoritativeReleaseList = iArticleDao.selectReleaseByPage();

        int firstIndex = (currPage - 1) * pageSize;
        int lastIndex = currPage * pageSize;
        if(lastIndex > authoritativeReleaseList.size()){
            lastIndex = authoritativeReleaseList.size();
        }

        return authoritativeReleaseList.subList(firstIndex, lastIndex);
    }

    @Override
    public List<RescuePage> selectRescueByPage(int currPage, int pageSize) {

        //获取全部结果
        List<RescuePage> rescuePageList = iArticleDao.selectRescueByPage();

        int firstIndex = (currPage - 1) * pageSize;
        int lastIndex = currPage * pageSize;
        if(lastIndex > rescuePageList.size()){
            lastIndex = rescuePageList.size();
        }

        return rescuePageList.subList(firstIndex, lastIndex);

    }

    @Override
    public int selectReleaseAllNum() {
        return iArticleDao.selectReleaseAllNum();
    }

    @Override
    public int selectRescueAllNum() {
        return iArticleDao.selectRescueAllNum();
    }

    @Override
    public AuthoritativeRelease selectReleaseById(int id) {
        return iArticleDao.selectReleaseById(id);
    }

    @Override
    public RescuePage selectRescueById(int id) {
        return iArticleDao.selectRescueById(id);
    }

    @Override
    public PiyaoPage selectPiyaoPageById(int id) {
        return iArticleDao.selectPiyaoPageById(id);
    }

    @Override
    public boolean insertRescue(RescuePage rescuePage) {
        return iArticleDao.insertRescue(rescuePage) > 0;
    }
}
