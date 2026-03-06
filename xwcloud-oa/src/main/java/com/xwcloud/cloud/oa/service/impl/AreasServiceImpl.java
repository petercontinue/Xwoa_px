package com.xwcloud.cloud.oa.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.Areas;
import com.xwcloud.cloud.oa.Dao.IAreasDao;
import com.xwcloud.cloud.oa.service.IAreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-23
 */
@Service
public class AreasServiceImpl extends ServiceImpl<IAreasDao, Areas> implements IAreasService {

    @Autowired
    private IAreasDao iAreasDao;

    //根据省份获取下级城市
    @Override
    public List<Areas> getAllAreasSubInfo(String id) {
        return iAreasDao.getAllAreasSubInfo(id);
    }
}
