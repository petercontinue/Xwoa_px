package com.xwcloud.cloud.wsc.Service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.jizancanyuVO;
import com.xwcloud.cloud.model.entity.WhdJizanHelpjizan;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-24
 */
public interface IWhdJizanHelpjizanService extends IService<WhdJizanHelpjizan> {
    List<jizancanyuVO> GetHelpdianzanRecordsList(long huodongID);
}
