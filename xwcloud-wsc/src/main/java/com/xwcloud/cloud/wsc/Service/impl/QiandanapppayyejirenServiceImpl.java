package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.Qiandanapppayyejiren;
import com.xwcloud.cloud.wsc.Dao.IQiandanapppayyejirenDao;
import com.xwcloud.cloud.wsc.Service.IQiandanapppayyejirenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-28
 */
@Service
public class QiandanapppayyejirenServiceImpl extends ServiceImpl<IQiandanapppayyejirenDao, Qiandanapppayyejiren> implements IQiandanapppayyejirenService {
	@Autowired
    IQiandanapppayyejirenDao iQiandanapppayyejirenDao;
}
