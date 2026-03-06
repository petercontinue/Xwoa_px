package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.xinqianliushuiVo;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
public interface IPxliushuizhangtableService extends IService<Pxliushuizhangtable> {
    List<Pxliushuizhangtable> GetQiandanLiushuiList(Long qiandanID);

    Integer DeleteLiushuiByQiandanID(Long qiandanID, Long qiyeID);

    Pxliushuizhangtable GetLiushuiByQdidAndPst(Long qiandanID, Long payMoneyStyle);

    Integer DeleteliushuiBystuID(Long stuID, Long qiyeID);

    Integer deleteLiushuiByStuIdAndqiandanID(Long stuID, Long qiandanID, Long qiyeID);

    List<xinqianliushuiVo> getqdliushuiList(QueryWrapper queryWrapper);
}
