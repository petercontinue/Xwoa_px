package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Qiandanapppayzafei;
import com.xwcloud.cloud.zsbm.Dao.IQiandanapppayzafeiDao;
import com.xwcloud.cloud.zsbm.Service.IQiandanapppayzafeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-28
 */
@Service
public class QiandanapppayzafeiServiceImpl extends ServiceImpl<IQiandanapppayzafeiDao, Qiandanapppayzafei> implements IQiandanapppayzafeiService {
    @Autowired
    IQiandanapppayzafeiDao iQiandanapppayzafeiDao;
}
