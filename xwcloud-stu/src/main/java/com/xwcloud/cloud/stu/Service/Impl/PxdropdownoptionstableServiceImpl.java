package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxdropdownoptionstable;

import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.stu.Dao.IPxdropdownoptionstableDao;
import com.xwcloud.cloud.stu.Service.IPxdropdownoptionstableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-08
 */
@Service
public class PxdropdownoptionstableServiceImpl extends ServiceImpl<IPxdropdownoptionstableDao, Pxdropdownoptionstable> implements IPxdropdownoptionstableService {
    @Autowired
    IPxdropdownoptionstableDao iPxdropdownoptionstableDao;

    @Override
    public List<listVo> getparamTypeList(Long stuParamTypeId, Long qiyeID) {
        return iPxdropdownoptionstableDao.getparamTypeList(stuParamTypeId, qiyeID);
    }
}
