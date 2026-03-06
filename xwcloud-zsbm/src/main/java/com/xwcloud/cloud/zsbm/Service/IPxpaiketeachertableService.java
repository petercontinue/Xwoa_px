package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxpaiketeachertable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-17
 */
public interface IPxpaiketeachertableService extends IService<Pxpaiketeachertable> {
    int DeletePaikeTeacherByPaikeID(Long paikeID);

    List<Pxpaiketeachertable> DeleteAllPaikeTeacherBypaikeID(Long paikeID);

    List<Pxpaiketeachertable> getPaiketeacherByPaikeID(Long paikeID);
}
