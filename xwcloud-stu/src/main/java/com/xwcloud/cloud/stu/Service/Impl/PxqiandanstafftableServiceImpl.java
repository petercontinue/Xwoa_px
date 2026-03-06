package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxqiandanstafftable;
import com.xwcloud.cloud.stu.Dao.IPxqiandanstafftableDao;
import com.xwcloud.cloud.stu.Service.IPxqiandanstafftableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-05
 */
@Service
public class PxqiandanstafftableServiceImpl extends ServiceImpl<IPxqiandanstafftableDao, Pxqiandanstafftable> implements IPxqiandanstafftableService {
	@Autowired
    IPxqiandanstafftableDao iPxqiandanstafftableDao;
}
