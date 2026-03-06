package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.buyZafeiVo;
import com.xwcloud.cloud.model.entity.Pxqiandanothermoneytable;

import com.xwcloud.cloud.model.Vo.searchVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
public interface IPxqiandanothermoneytableService extends IService<Pxqiandanothermoneytable> {
    List<searchVO> GetQiandanOtherMoneyList(Long qiyeID);

    List<buyZafeiVo> GetQiandanZafeiList(long qiandanID);
}
