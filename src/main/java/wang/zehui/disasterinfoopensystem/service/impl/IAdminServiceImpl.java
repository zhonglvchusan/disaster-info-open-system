package wang.zehui.disasterinfoopensystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.zehui.disasterinfoopensystem.dao.IAdminDao;
import wang.zehui.disasterinfoopensystem.pojo.*;
import wang.zehui.disasterinfoopensystem.service.IAdminService;

import java.util.List;

@Service
public class IAdminServiceImpl implements IAdminService {

    @Autowired
    IAdminDao iAdminDao;

    @Override
    public boolean insertArticle(AuthoritativeRelease authoritativeRelease) {
        return iAdminDao.insertArticle(authoritativeRelease) > 0;
    }

    @Override
    public boolean deleteArticleById(int id) {
        return iAdminDao.deleteArticleById(id) > 0;
    }

    @Override
    public List<AuthoritativeRelease> selectAuthByPage(int currPage) {
        List<AuthoritativeRelease> authoritativeReleaseList = iAdminDao.selectAuthByPage();

        int firstIndex = (currPage - 1) * 10;
        int lastIndex = currPage * 10;
        if(lastIndex > authoritativeReleaseList.size()){
            lastIndex = authoritativeReleaseList.size();
        }

        return authoritativeReleaseList.subList(firstIndex, lastIndex);
    }

    @Override
    public List<Material_Need> selectMaterialNeedByPage(int currPage) {

        List<Material_Need> materialNeedList = iAdminDao.selectMaterialNeedByPage();

        int firstIndex = (currPage - 1) * 10;
        int lastIndex = currPage * 10;
        if(lastIndex > materialNeedList.size()){
            lastIndex = materialNeedList.size();
        }

        return materialNeedList.subList(firstIndex, lastIndex);
    }

    @Override
    public boolean updateMaterialStatusById(int id) {
        return iAdminDao.updateMaterialStatusById(id) > 0;
    }

    @Override
    public List<Rescue_Need> selectRescueNeedByPage(int currPage) {

        List<Rescue_Need> rescueNeedList = iAdminDao.selectRescueNeedByPage();

        int firstIndex = (currPage - 1) * 10;
        int lastIndex = currPage * 10;
        if(lastIndex > rescueNeedList.size()){
            lastIndex = rescueNeedList.size();
        }

        return rescueNeedList.subList(firstIndex, lastIndex);

    }

    @Override
    public boolean updateRescueStatusById(int id) {
        return iAdminDao.updateRescueStatusById(id) > 0;
    }

    @Override
    public List<PiyaoPage> selectPiyaoPageByPage(int currPage) {
        List<PiyaoPage> piyaoPageList = iAdminDao.selectPiyaoPageByPage();

        int firstIndex = (currPage - 1) * 10;
        int lastIndex = currPage * 10;
        if(lastIndex > piyaoPageList.size()){
            lastIndex = piyaoPageList.size();
        }

        return piyaoPageList.subList(firstIndex, lastIndex);
    }

    @Override
    public boolean insertPiyaoPage(PiyaoPage piyaoPage) {
        return iAdminDao.insertPiyaoPage(piyaoPage) > 0;
    }

    @Override
    public boolean deletePiyaoPageById(int id) {
        return iAdminDao.deletePiyaoPageById(id) > 0;
    }

    @Override
    public List<User> selectUserByPage(int currPage) {

        List<User> userList = iAdminDao.selectUserByPage();

        int firstIndex = (currPage - 1) * 10;
        int lastIndex = currPage * 10;
        if(lastIndex > userList.size()){
            lastIndex = userList.size();
        }

        return userList.subList(firstIndex, lastIndex);
    }

    @Override
    public boolean updateUserRightById(int id, String right) {
        return iAdminDao.updateUserRightById(id, right) > 0;
    }

    @Override
    public boolean insertImg(Image image) {
        return iAdminDao.insertImg(image) > 0;
    }

    @Override
    public int selectAllNum(String tableName) {
        return iAdminDao.selectAllNum(tableName);
    }

    @Override
    public int selectAllNum1(String tableName) {
        return iAdminDao.selectAllNum1(tableName);
    }
}
