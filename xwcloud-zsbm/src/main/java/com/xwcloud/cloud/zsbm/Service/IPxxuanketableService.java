package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxxuanketable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */
public interface IPxxuanketableService extends IService<Pxxuanketable> {
    List<Pxxuanketable> GetXuankeByBuxiIDAndPaikeId(Long stuID, Long paikeID);

    int deleteXuanKeTable(Long paikeID);

    int deleteXuankeByBxIDAndStuID(Long bxID,Long stuID);

    List<Pxxuanketable> getAllXuankeByPaikeID(Long paikeID);

    List<Pxxuanketable> GetAllXuankeBypkIDAndStuIDList(Long paikeID,Long stuID);

    int DeleteXuankeReords(Long paikeID,Long stuID);

    List<Pxxuanketable> GetXuankeByKehcnegID(Long kechengID);

    List<Pxxuanketable> GetXuankeByPaikeID(Long PaikeID);
}
