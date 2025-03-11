package wang.zehui.disasterinfoopensystem.service;

import wang.zehui.disasterinfoopensystem.pojo.*;

import java.util.List;

public interface IAdminService {

    boolean insertArticle(AuthoritativeRelease authoritativeRelease);

    boolean deleteArticleById(int id);

    List<AuthoritativeRelease> selectAuthByPage(int currPage);

    List<Material_Need> selectMaterialNeedByPage(int currPage);

    boolean updateMaterialStatusById(int id);

    List<Rescue_Need> selectRescueNeedByPage(int currPage);

    boolean updateRescueStatusById(int id);

    List<PiyaoPage> selectPiyaoPageByPage(int currPage);

    boolean insertPiyaoPage(PiyaoPage piyaoPage);

    boolean deletePiyaoPageById(int id);

    List<User> selectUserByPage(int currPage);

    boolean updateUserRightById(int id, String right);

    boolean insertImg(Image image);

    int selectAllNum(String tableName);

    int selectAllNum1(String tableName);
}
