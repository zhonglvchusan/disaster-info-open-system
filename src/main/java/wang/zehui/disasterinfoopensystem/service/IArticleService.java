package wang.zehui.disasterinfoopensystem.service;

import wang.zehui.disasterinfoopensystem.pojo.AuthoritativeRelease;
import wang.zehui.disasterinfoopensystem.pojo.PiyaoPage;
import wang.zehui.disasterinfoopensystem.pojo.RescuePage;

import java.util.List;

public interface IArticleService {

    List<RescuePage> SelectRescue();

    List<AuthoritativeRelease> SelectRelease();

    List<AuthoritativeRelease> selectReleaseByPage(int currPage, int pageSize);

    List<RescuePage> selectRescueByPage(int currPage, int pageSize);

    int selectReleaseAllNum();

    int selectRescueAllNum();

    AuthoritativeRelease selectReleaseById(int id);

    RescuePage selectRescueById(int id);

    PiyaoPage selectPiyaoPageById(int id);

    boolean insertRescue(RescuePage rescuePage);
}
