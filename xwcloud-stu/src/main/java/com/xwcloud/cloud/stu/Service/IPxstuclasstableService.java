package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.AsClassTOStuVo;
import com.xwcloud.cloud.model.Vo.exportclassstuVo;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxstuclasstableService extends IService<Pxstuclasstable> {
    List<Pxstuclasstable> getBybxID(Long buxiID, Long qiyeID);

    List<Pxstuclasstable> getstuclass(Long classID, Long qiyeID);

    List<Pxstuclasstable> getBybxAndclassID(Long buxiID, Long classID, Long qiyeID);

    List<Pxstuclasstable> selectstuclass(@Param("ew") QueryWrapper queryWrapper);

    //按照班级获取学员
    Page<AsClassTOStuVo> AsClassTOStuPage(Page page, QueryWrapper queryWrapper);

    List<exportclassstuVo> getClassStu(@Param("ew") QueryWrapper queryWrapper);
}
