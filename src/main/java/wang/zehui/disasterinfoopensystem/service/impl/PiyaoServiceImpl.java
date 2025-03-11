package wang.zehui.disasterinfoopensystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.zehui.disasterinfoopensystem.dao.IPiyaoDao;
import wang.zehui.disasterinfoopensystem.pojo.PiyaoPage;
import wang.zehui.disasterinfoopensystem.service.IPiyaoService;
import wang.zehui.disasterinfoopensystem.utils.DateTransformationUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PiyaoServiceImpl implements IPiyaoService {

    @Autowired
    IPiyaoDao iPiyaoDao;

    @Override
    public int selectTotalNum() {
        return iPiyaoDao.selectTotalNum();
    }


    /**
     * @author: wangxiaobai
     * @date: 2022/5/26 8:21
     * @Description: 获取当天0点时间戳，再获取前一天时间戳，选取两者中间的
     */
    @Override
    public int selectYesTotalNum() throws ParseException {

        int num;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        long endTime = now.getTime();
        long startTime = endTime - 86400000;

        List<String> dateLists = iPiyaoDao.selectYesTotalNum();

        num = (int) dateLists.stream().filter(i -> Long.parseLong(i) > startTime && Long.parseLong(i) < endTime).count();

        return num;
    }


    @Override
    public List<PiyaoPage> getListByPage(int currPage) {
        List<PiyaoPage> pageList = iPiyaoDao.getListByPage();

        //按照时间戳对列表排序
        Collections.sort(pageList);

        pageList.forEach(i -> {
            try {
                i.setDate(DateTransformationUtils.stamptoDate(i.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        int firstIndex = (currPage - 1) * 10;
        int lastIndex = currPage * 10;
        if(lastIndex > pageList.size()){
            lastIndex = pageList.size();
        }

        return pageList.subList(firstIndex, lastIndex);
    }
}
