package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.qiandanpaymoneyVO;
import com.xwcloud.cloud.model.entity.Pxqiandanpaymoney;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
public interface IPxqiandanpaymoneyService extends IService<Pxqiandanpaymoney> {
    Pxqiandanpaymoney GetQiandanPayMoneyStyleByqdID(Long qiandanID,Long paymoneyStyleID);

    List<Pxqiandanpaymoney> getQiandanPayMoneyList(Long qiandanID);

    int deleteQiandanPayMoneybyqiandanID(Long qiandanID);

    List<qiandanpaymoneyVO> getqiandanPayMoneyList(Long qiandanID);

    List<HashMap<String, Object>> getPaystyletoPayweikuan(QueryWrapper queryWrapper);
}
