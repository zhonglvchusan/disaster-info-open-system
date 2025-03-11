package wang.zehui.disasterinfoopensystem.service;

import wang.zehui.disasterinfoopensystem.pojo.PiyaoPage;

import java.text.ParseException;
import java.util.List;

public interface IPiyaoService {

    int selectTotalNum();

    int selectYesTotalNum() throws ParseException;

    List<PiyaoPage> getListByPage(int currPage);
}
