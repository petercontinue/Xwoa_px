package com.xwcloud.cloud.wsc.Service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.yuekeTeacherVO;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabutable;
import com.xwcloud.cloud.wsc.Dao.IPxyueketeacherfabutableDao;
import com.xwcloud.cloud.wsc.Service.IPxyueketeacherfabutableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-15
 */
@Service
public class PxyueketeacherfabutableServiceImpl extends ServiceImpl<IPxyueketeacherfabutableDao, Pxyueketeacherfabutable> implements IPxyueketeacherfabutableService {

    @Autowired
    IPxyueketeacherfabutableDao iPxyueketeacherfabutableDao;

    @Override
    public Page<yuekeTeacherVO> GetYuekTeacherFabuPages(Page page, QueryWrapper wrapper) {
        return iPxyueketeacherfabutableDao.GetYuekTeacherFabuPages(page,wrapper);
    }

    @Override
    public List<yuekeTeacherVO> GetYuekDetailInfo(QueryWrapper wrapper) {
        return iPxyueketeacherfabutableDao.GetYuekDetailInfo(wrapper);
    }
}
