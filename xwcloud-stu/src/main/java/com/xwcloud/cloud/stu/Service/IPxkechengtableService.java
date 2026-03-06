package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxkechengtable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxkechengtableService extends IService<Pxkechengtable> {
    Page<Pxkechengtable> getZSxq(Long ZSid, Long qiyeID, Page page);

    List<listVo> getKcBySubject(Long subjectID, Long qiyeID);

    Pxkechengtable getBysubject(Long qiyeID);

    List<Pxkechengtable> selectkc(QueryWrapper queryWrapper);
}
