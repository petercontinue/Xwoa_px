package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstucardtable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-19
 */
public interface IPxstucardtableService extends IService<Pxstucardtable> {
    List<Pxstucardtable> getCard(Long stuID, String cardNumber,Long qiyeID);
    List<Pxstucardtable> addUpdateCard(Long stuID,Long qiyeID);
    List<Pxstucardtable> getcfCard(@Param("ew") QueryWrapper queryWrapper);
}
