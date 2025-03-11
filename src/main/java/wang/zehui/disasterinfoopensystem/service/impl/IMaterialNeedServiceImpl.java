package wang.zehui.disasterinfoopensystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.zehui.disasterinfoopensystem.dao.IMaterialNeedDao;
import wang.zehui.disasterinfoopensystem.pojo.Material_Need;
import wang.zehui.disasterinfoopensystem.service.IMaterialNeedService;

import java.util.List;

@Service
public class IMaterialNeedServiceImpl implements IMaterialNeedService {

    @Autowired
    IMaterialNeedDao iMaterialNeedDao;

    @Override
    public List<Material_Need> selectIndexMaterial() {
        return iMaterialNeedDao.selectIndexMaterial();
    }

    @Override
    public boolean Check(Material_Need materialNeed) {
        return iMaterialNeedDao.Check(materialNeed).isEmpty();
    }

    @Override
    public boolean commitMaterial(Material_Need materialNeed) {
        return iMaterialNeedDao.commitMaterial(materialNeed) > 0;
    }

    @Override
    public boolean commitAgain(Material_Need materialNeed) {
        return iMaterialNeedDao.commitAgain(materialNeed) > 0;
    }
}
