package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.classstuinfoVO;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;

import com.xwcloud.cloud.model.Vo.ziyouqiandaoLookStuVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxstuclasstableService extends IService<Pxstuclasstable> {
    Page<ziyouqiandaoLookStuVo> getziyouqiandaoLookStuPage(Page page, QueryWrapper queryWrapper);
    List<Pxstuclasstable> selectstuclass(QueryWrapper queryWrapper);
    List<classstuinfoVO> getClassStuInfobyClassID(long classID, long qiyeID);
    List<classstuinfoVO> getstuInfoBypaikeID(long paikeID, long qiyeID);
}
