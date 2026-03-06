package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxqiandaninfo2table;
import com.xwcloud.cloud.stu.Dao.IPxqiandaninfo2tableDao;
import com.xwcloud.cloud.stu.Service.IPxqiandaninfo2tableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Service
public class Pxqiandaninfo2tableServiceImpl extends ServiceImpl<IPxqiandaninfo2tableDao, Pxqiandaninfo2table> implements IPxqiandaninfo2tableService {
	@Autowired
    IPxqiandaninfo2tableDao iPxqiandaninfo2tableDao;

    @Override
    public List<Pxqiandaninfo2table> getqd2(Long qdID,Long qiyeID) {
        return iPxqiandaninfo2tableDao.getqd2(qdID,qiyeID);
    }
}
