package com.xwcloud.cloud.wsc.Service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.jizanfaqiVO;
import com.xwcloud.cloud.model.entity.WhdJizanFaqimyjizan;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-24
 */
public interface IWhdJizanFaqimyjizanService extends IService<WhdJizanFaqimyjizan> {
    List<jizanfaqiVO> GetjizanFaqiList(long huodongID);
}
