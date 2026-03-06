package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxclasstable;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;


import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
public interface IPxclasstableService extends IService<Pxclasstable> {
    List<listVo> getAllClass(QueryWrapper queryWrapper);

    List<Pxclasstable> selectclass(QueryWrapper queryWrapper);

    List<Pxclasstable> updatestuOyOclass(String oldstuName, String newstuName,Long qiyeID);

    Page<LookstuClassVo> getstuclassPage(Page page, Long stuID, Long qiyeID);

    //学员班级
    Page<classTyleVo> getStuClass(Page page, QueryWrapper queryWrapper);

    //查询学员班级
    Page<stuClassVo> getClassToStu(Page page, QueryWrapper queryWrapper);

    //查重一对一班级名称
    List<Pxclasstable> getOtOName(Long id, String className, Long qiyeID);

    //修改查重
    List<Pxclasstable> getClassName(String className, Long qiyeID);

    List<Pxclasstable> getupdateClassName(String className, Long qiyeID, Long id);

    List<Pxclasstable> getClasszdID(String zdID, Long qiyeID);

    List<Pxclasstable> getCfzdID(String zdID, Long qiyeID);

    List<Pxclasstable> getupdateClasszdID(String zdID, Long qiyeID, Long id);

    List<listVo> getallbanke(Long qiyeID,String campusID);

    //获取班级状态
    Pxclasstable getShow(int Type, Long classID,Long qiyeID);

    //导出学员一对一班级
    List<ExportClassVo> getClassInfoOtO(QueryWrapper queryWrapper);

    List<Pxclasstable> FkClassName(String className, Long qiyeID);

    //删除组
    List<Pxkeshistutable> getkeHao(Long classID, Long qiyeID);

    paikeVo getPaiKe(Long classID, Long qiyeID);

    List<listVo> getkxqclass(Long campusID, Long qiyeID);
}
