package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxstutable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-07
 */
public interface IPxstutableService extends IService<Pxstutable> {
    List<baomingrecordsVO> GetAllbaomingRecords(QueryWrapper wrapper);

    List<remainkeshiWscVO> GetStuAllRemainkeshi(QueryWrapper wrapper);

    List<chongzhirecordsVO> GetStuChongzhjiList(QueryWrapper wrapper);

    List<Pxstutable> GetAllStuListLoginPhone(QueryWrapper wrapper);

    Page<stuIntegerVo> getstuIntegraInfoPage(Page page, QueryWrapper queryWrapper);

    List<shengrizhushouVO> GetAllteacherAndStuBirthday(long qiyeID);
}
