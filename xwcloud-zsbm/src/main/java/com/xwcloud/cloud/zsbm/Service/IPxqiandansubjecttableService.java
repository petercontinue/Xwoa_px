package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.buyKechengVo;
import com.xwcloud.cloud.model.entity.Pxqiandansubjecttable;


import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
public interface IPxqiandansubjecttableService extends IService<Pxqiandansubjecttable> {
    BigDecimal GetQiandanKechengzongjia(Long qianDanInfoID);

    Pxqiandansubjecttable GetQiandansubjectZidingyi(QueryWrapper wrapper);

    Integer DeleteQiandanSubjectByStuID(Long stuID,Long qiyeID);

    List<Pxqiandansubjecttable> GetQiandanSubjectByQianDanID(Long qianDanInfoID);

    Integer DeleteQiandansubjectByQdID(Long qianDanInfoID,Long qiyeID);

    List<buyKechengVo> GetQiandanKechengList(long qiandanID);
}
