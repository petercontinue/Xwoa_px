package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.getclassVo;
import com.xwcloud.cloud.model.Vo.kcInfoVo;
import com.xwcloud.cloud.model.entity.Pxkechengtable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxkechengtableService extends IService<Pxkechengtable> {
    List<kcInfoVo> getnewkcInfoList(Long qiyeID);
    List<getclassVo> getkcBycampus(Long campusID, Long qiyeID);
    List<getclassVo> getKcToYueXiaoKe(QueryWrapper queryWrapper);
    List<getclassVo>GetRenkeTeacherList(long teachSubjectID,long qiyeID);
}
