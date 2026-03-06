package com.xwcloud.cloud.zsbm.Service;

;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.buyKechengVo;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;


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
public interface IPxbuxikechengtableService extends IService<Pxbuxikechengtable> {
    Pxbuxikechengtable GetZidingYiKecheng( QueryWrapper wrapper);

    List<Pxbuxikechengtable> GetBuxikchengByStuID(Long stuID,Long qiyeID);

    List<Pxbuxikechengtable> GetStuBuxikechengList(Long stuID,Long kechengID,Long qiyeID);

    Pxbuxikechengtable GetBuxikecheng(Long stuID, Long kechengID, BigDecimal kechengprice,Long qiyeID);

    Pxbuxikechengtable GetBuyBuxikecheng(Long stuID, Long kechengID, BigDecimal kechengprice,Long qiyeID);

    Integer deleteBuxikechengByStuID(Long stuID,Long qiyeID);

    List<Pxbuxikechengtable> getbuxikechenglist(Long stuID,Long buxiID,Long qiyeID);

    List<buyKechengVo> GetAllStukechengInfoList(long stuID, Long qiyeID);



    ///---------------------------------------意向学员--------------------------------

    List<Pxbuxikechengtable> getAllBuxikechengByStuID(Long stuID);

    Pxbuxikechengtable getBuxikechengByStuIDAndkechengID(Long stuID,Long kechnegID);

}
