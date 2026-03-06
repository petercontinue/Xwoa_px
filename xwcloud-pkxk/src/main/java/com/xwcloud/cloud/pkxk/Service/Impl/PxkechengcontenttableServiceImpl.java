package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.pxkechengcontentVO;
import com.xwcloud.cloud.model.entity.Pxkechengcontenttable;
import com.xwcloud.cloud.pkxk.Dao.IPxkechengcontenttableDao;
import com.xwcloud.cloud.pkxk.Service.IPxkechengcontenttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-26
 */
@Service
public class PxkechengcontenttableServiceImpl extends ServiceImpl<IPxkechengcontenttableDao, Pxkechengcontenttable> implements IPxkechengcontenttableService {

    @Autowired
    IPxkechengcontenttableDao iPxkechengcontenttableDao;

    @Override
    public List<pxkechengcontentVO> GetKechengContentList(long kechengID) {
        return iPxkechengcontenttableDao.GetKechengContentList(kechengID);
    }
}
