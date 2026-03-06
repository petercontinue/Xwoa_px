package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.jizanfaqiVO;
import com.xwcloud.cloud.model.entity.WhdJizanHuodong;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-03
 */
public interface IWhdJizanHuodongService extends IService<WhdJizanHuodong> {
    List<WhdJizanHuodong> GetAlljizanList(QueryWrapper wrapper);
    List<jizanfaqiVO> GetjizanfaqiInfo(long huodongID, long faqirenID);
}
