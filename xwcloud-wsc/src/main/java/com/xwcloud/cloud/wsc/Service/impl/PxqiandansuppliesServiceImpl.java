package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxqiandansupplies;
import com.xwcloud.cloud.wsc.Dao.IPxqiandansuppliesDao;
import com.xwcloud.cloud.wsc.Service.IPxqiandansuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
@Service
public class PxqiandansuppliesServiceImpl extends ServiceImpl<IPxqiandansuppliesDao, Pxqiandansupplies> implements IPxqiandansuppliesService {
	@Autowired
    IPxqiandansuppliesDao iPxqiandansuppliesDao;
}
