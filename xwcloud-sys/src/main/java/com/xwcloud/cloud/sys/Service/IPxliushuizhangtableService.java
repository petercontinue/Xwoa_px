package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.yejidataVO;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-01
 */
public interface IPxliushuizhangtableService extends IService<Pxliushuizhangtable> {
    List<yejidataVO> GetCampusYejiList();

    BigDecimal GetDayYejiMoney(long qiyeID, long campusID, String date);

    BigDecimal GetKehaoMoney(long qiyeID,long campusID,String date);

}
