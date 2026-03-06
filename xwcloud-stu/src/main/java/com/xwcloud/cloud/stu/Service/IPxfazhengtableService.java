package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxfazhengtable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-26
 */
public interface IPxfazhengtableService extends IService<Pxfazhengtable> {
    List<Pxfazhengtable> getstufzList(Long stuID, Long Zsid, Long qiyeID);
}
