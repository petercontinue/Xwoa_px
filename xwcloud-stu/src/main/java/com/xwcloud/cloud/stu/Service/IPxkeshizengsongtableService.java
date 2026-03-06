package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxkeshizengsongtable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxkeshizengsongtableService extends IService<Pxkeshizengsongtable> {
    List<Pxkeshizengsongtable> getksZs(Long stuID,Long qiyeID);
}
