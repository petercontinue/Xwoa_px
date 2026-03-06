package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.qiandanwpVO;
import com.xwcloud.cloud.model.Vo.teachingSuppliesVo;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliestable;
import com.xwcloud.cloud.zsbm.Dao.IPxteachingsuppliestableDao;
import com.xwcloud.cloud.zsbm.Service.IPxteachingsuppliestableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */
@Service
public class PxteachingsuppliestableServiceImpl extends ServiceImpl<IPxteachingsuppliestableDao, Pxteachingsuppliestable> implements IPxteachingsuppliestableService {
    @Autowired
    IPxteachingsuppliestableDao iPxteachingsuppliestableDao;

    @Override
    public Pxteachingsuppliestable GetTeachingSuppliesByName(String Name) {
        return iPxteachingsuppliestableDao.GetTeachingSuppliesByName(Name);
    }

    @Override
    public int UpdateteachingsuppliesKucun(Long ID, BigDecimal kucun) {
        return iPxteachingsuppliestableDao.UpdateteachingsuppliesKucun(ID, kucun);
    }

    @Override
    public List<Pxteachingsuppliestable> getTeachingSuppliesByTiaoma(String changpinTiaoma) {
        return iPxteachingsuppliestableDao.getTeachingSuppliesByTiaoma(changpinTiaoma);
    }

    @Override
    public Page<teachingSuppliesVo> GetTeachingSuppliesPages(Page page, QueryWrapper wrapper) {
        return iPxteachingsuppliestableDao.GetTeachingSuppliesPages(page,wrapper);
    }

    @Override
    public Pxteachingsuppliestable getTeachingSupplies(QueryWrapper wrapper) {
        return iPxteachingsuppliestableDao.getTeachingSupplies(wrapper);
    }

    @Override
    public List<teachingSuppliesVo> GetTeachingSuppliesList(QueryWrapper wrapper) {
        return iPxteachingsuppliestableDao.GetTeachingSuppliesList(wrapper);
    }

    @Override
    public List<qiandanwpVO> getAllWupingList(long campusID, long qiyeID) {
        return iPxteachingsuppliestableDao.getAllWupingList(campusID,qiyeID);
    }
}
