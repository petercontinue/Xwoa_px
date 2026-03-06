package com.xwcloud.cloud.zsbm.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxyxtelleveltable;
import com.xwcloud.cloud.zsbm.Dao.IPxyxtelleveltableDao;
import com.xwcloud.cloud.zsbm.Service.IPxyxtelleveltableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-25
 */
@Service
public class PxyxtelleveltableServiceImpl extends ServiceImpl<IPxyxtelleveltableDao, Pxyxtelleveltable> implements IPxyxtelleveltableService {

    @Autowired
    private IPxyxtelleveltableDao pxyxtelleveltableDao;

    @Override
    public List<searchVO> getYxSearchTelLevelList(Long qiyeID) {
        return pxyxtelleveltableDao.getYxSearchTelLevelList(qiyeID);
    }

}
