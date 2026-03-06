package com.xwcloud.cloud.stu.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.entity.Pxstutable;

import com.xwcloud.cloud.model.Vo.zafeiInfoVo;
import com.xwcloud.cloud.model.Vo.*;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-05
 */
public interface IPxstutableService extends IService<Pxstutable> {

    List<Pxstutable> selectstu(QueryWrapper queryWrapper);

    List<allstuVo> GetAllSelectedStuNames(QueryWrapper queryWrapper);

    List<listVo> Getnianji(Long qiyeID);

    List<listVo> getallstu(QueryWrapper queryWrapper);

    List<stuInfogetqiandanVo> getstuInfoqiandan(QueryWrapper queryWrapper);

    //学员档案
    Page<stuCampusVo> getStuCampusList(Page page, QueryWrapper queryWrapper);

    List<stufilesVo> ExportstuFiles(int type, String classID, QueryWrapper queryWrapper);

    List<exportstuQdVo> ExportstuQD(QueryWrapper queryWrapper);

    List<ExportKcVo> Exportstukc(QueryWrapper queryWrapper);

    List<ExportstuClassVo> ExportstuClass(QueryWrapper queryWrapper);

    List<ClassMesVo> ExportClassMes(QueryWrapper queryWrapper);

    List<remainkeshiVo> ExportRekeshi(QueryWrapper queryWrapper);

    Page<stuIntegerVo> getstuIntegraInfoPage(Page page, QueryWrapper queryWrapper);

    Page<daijinquanVo> getdaijinquanInfoPage(Page page, QueryWrapper queryWrapper);

    Page<kehaoInfoVo> getkehaoInfoPage(Page page, QueryWrapper queryWrapper);

    Page<stuskInfoVo> getstuskInfoPage(Page page, Long stuID, Long qiyeID, QueryWrapper queryWrapper);

    Page<zafeiInfoVo> getzafeiInfoPage(Page page, Long qdID, Long qiyeID);

    List<stuInfoVo> getstuInfoPage(Long stuID, Long qiyeID);

    Page<stuqiandanInfoVo> getstuQiandanInfoPage(Page page, QueryWrapper queryWrapper);

    Page<kcInfoLookVo> getkechengInfoPage(Page page, Long stuID, Long qdID, Long qiyeID);

    List<listVo> getzxqcampusList(Long stuID, Long qiyeID);

    List<zxqstubuxiVo> getzxqbuxiList(Long stuID, Long qiyeID);

    List<listVo> getallJFkcshuList(String buxiStyleName, int JifeiStyle, Long campusID, Long qiyeID);

    //region 分配班主任
    Page<stuTearchVo> getStuTearch(Page page, QueryWrapper queryWrapper);

    List<stuTearchVo> ExportstuTeacher(QueryWrapper queryWrapper);
    //endregion

    //学员卡
    Page<stuCardVo> getStuCard(Page page, QueryWrapper queryWrapper);

    //region 学员年级|年龄段
    Page<stuGradeVo> getStuGradeList(Page page, QueryWrapper queryWrapper);

    Page<gradeNotesVo> getGradeJiLu(Page page, QueryWrapper queryWrapper);
    //endregion

    //region 学员积分
    Page<stuIntegralVo> getStuIntegral(Page page, QueryWrapper queryWrapper);

    Page<JFRankVo> getJFpaiming(Page page, QueryWrapper queryWrapper);

    List<stuCardVo> ExportStuCard(QueryWrapper queryWrapper);
    //endregion

    //region 学员生日
    Page<stuBirthVo> getStuBirth(Page page, QueryWrapper queryWrapper);

    List<stuBirthVo> ExportStuBirth(QueryWrapper queryWrapper);
    //endregion

    //region 学员住宿
    Page<stuStayVo> getStuStay(Page page, QueryWrapper queryWrapper);

    List<Pxstutable> getnewNum(Long roomid, Long qiyeID);

    stuInRoomVo getStuInRoomVo(Long roomid, Long qiyeID);
    //endregion
}
