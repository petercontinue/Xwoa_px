package com.xwcloud.cloud.zsbm.Service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.zaffeiListVo;
import com.xwcloud.cloud.model.entity.Pxqiandaninfo2table;
import com.xwcloud.cloud.zsbm.Dao.IPxqiandaninfo2tableDao;
import com.xwcloud.cloud.zsbm.Service.IPxqiandaninfo2tableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
@Service
public class Pxqiandaninfo2tableServiceImpl extends ServiceImpl<IPxqiandaninfo2tableDao, Pxqiandaninfo2table> implements IPxqiandaninfo2tableService {

    @Autowired
    IPxqiandaninfo2tableDao iPxqiandaninfo2tableDao;

    @Override
    public Pxqiandaninfo2table GetQiandanInfoByQiandanID(Long qianInfoTabID, Long qianDanOtherMoneyID) {
        return iPxqiandaninfo2tableDao.GetQiandanInfoByQiandanID(qianInfoTabID, qianDanOtherMoneyID);
    }

    @Override
    public Integer deleteQiandanInfo2ByQiandanID(Long qianInfoTabID) {
        return iPxqiandaninfo2tableDao.deleteQiandanInfo2ByQiandanID(qianInfoTabID);
    }

    @Override
    public Page<zaffeiListVo> GetQiandanOtherMoneyPages(Page page, QueryWrapper wrapper) {
        return iPxqiandaninfo2tableDao.GetQiandanOtherMoneyPages(page, wrapper);
    }

    @Override
    public List<zaffeiListVo> GetQiandanOtherMoneyList(QueryWrapper wrapper) {
        return iPxqiandaninfo2tableDao.GetQiandanOtherMoneyList(wrapper);
    }

    @Override
    public String getzf(Wrapper wrapper) {
        return iPxqiandaninfo2tableDao.getzf(wrapper);
    }
}
