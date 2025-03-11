package wang.zehui.disasterinfoopensystem.service;

import wang.zehui.disasterinfoopensystem.pojo.Material_Need;

import java.util.List;

public interface IMaterialNeedService {

    List<Material_Need> selectIndexMaterial();

    boolean Check(Material_Need materialNeed);

    boolean commitMaterial(Material_Need materialNeed);

    boolean commitAgain(Material_Need materialNeed);
}
