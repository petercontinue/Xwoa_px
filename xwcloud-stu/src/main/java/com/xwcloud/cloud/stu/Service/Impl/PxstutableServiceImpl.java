package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstutable;

import com.xwcloud.cloud.model.Vo.zafeiInfoVo;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.stu.Dao.IPxstutableDADao;
import com.xwcloud.cloud.stu.Service.IPxstutableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-05
 */
@Service
public class PxstutableServiceImpl extends ServiceImpl<IPxstutableDADao, Pxstutable> implements IPxstutableService {

    @Autowired
    IPxstutableDADao iPxstutableDADao;


    @Override
    public List<Pxstutable> selectstu(QueryWrapper queryWrapper) {
        return iPxstutableDADao.selectstu(queryWrapper);
    }

    @Override
    public List<allstuVo> GetAllSelectedStuNames(QueryWrapper queryWrapper) {
        return iPxstutableDADao.GetAllSelectedStuNames(queryWrapper);
    }

    @Override
    public List<listVo> Getnianji(Long qiyeID) {
        return iPxstutableDADao.Getnianji(qiyeID);
    }


    @Override
    public List<listVo> getallstu(QueryWrapper queryWrapper) {
        return iPxstutableDADao.getallstu(queryWrapper);
    }

    @Override
    public List<stuInfogetqiandanVo> getstuInfoqiandan(QueryWrapper queryWrapper) {
        return iPxstutableDADao.getstuInfoqiandan(queryWrapper);
    }

    //学员档案表关联
    @Override
    public Page<stuCampusVo> getStuCampusList(Page page, QueryWrapper wrapper) {
        return iPxstutableDADao.getStuCampusList(page, wrapper);
    }

    @Override
    public List<stufilesVo> ExportstuFiles(int type, String classID, QueryWrapper queryWrapper) {
        return iPxstutableDADao.ExportstuFiles(type, classID, queryWrapper);
    }

    @Override
    public List<exportstuQdVo> ExportstuQD(QueryWrapper queryWrapper) {
        return iPxstutableDADao.ExportstuQD(queryWrapper);
    }

    @Override
    public List<ExportKcVo> Exportstukc(QueryWrapper queryWrapper) {
        return iPxstutableDADao.Exportstukc(queryWrapper);
    }

    @Override
    public List<ExportstuClassVo> ExportstuClass(QueryWrapper queryWrapper) {
        return iPxstutableDADao.ExportstuClass(queryWrapper);
    }

    @Override
    public List<ClassMesVo> ExportClassMes(QueryWrapper queryWrapper) {
        return iPxstutableDADao.ExportClassMes(queryWrapper);
    }

    @Override
    public List<remainkeshiVo> ExportRekeshi(QueryWrapper queryWrapper) {
        return iPxstutableDADao.ExportRekeshi(queryWrapper);
    }

    @Override
    public Page<stuIntegerVo> getstuIntegraInfoPage(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getstuIntegraInfoPage(page, queryWrapper);
    }

    @Override
    public Page<daijinquanVo> getdaijinquanInfoPage(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getdaijinquanInfoPage(page, queryWrapper);
    }

    @Override
    public Page<kehaoInfoVo> getkehaoInfoPage(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getkehaoInfoPage(page, queryWrapper);
    }

    @Override
    public Page<stuskInfoVo> getstuskInfoPage(Page page, Long stuID, Long qiyeID, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getstuskInfoPage(page, stuID, qiyeID, queryWrapper);
    }

    @Override
    public Page<zafeiInfoVo> getzafeiInfoPage(Page page, Long qdID, Long qiyeID) {
        return iPxstutableDADao.getzafeiInfoPage(page, qdID, qiyeID);
    }

    @Override
    public List<stuInfoVo> getstuInfoPage(Long stuID, Long qiyeID) {
        return iPxstutableDADao.getstuInfoPage(stuID, qiyeID);
    }

    @Override
    public Page<stuqiandanInfoVo> getstuQiandanInfoPage(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getstuQiandanInfoPage(page, queryWrapper);
    }

    @Override
    public Page<kcInfoLookVo> getkechengInfoPage(Page page, Long stuID, Long qdID, Long qiyeID) {
        return iPxstutableDADao.getkechengInfoPage(page, stuID, qdID, qiyeID);
    }

    @Override
    public List<listVo> getzxqcampusList(Long stuID, Long qiyeID) {
        return iPxstutableDADao.getzxqcampusList(stuID, qiyeID);
    }

    @Override
    public List<zxqstubuxiVo> getzxqbuxiList(Long stuID, Long qiyeID) {
        return iPxstutableDADao.getzxqbuxiList(stuID, qiyeID);
    }

    @Override
    public List<listVo> getallJFkcshuList(String buxiStyleName, int JifeiStyle, Long campusID, Long qiyeID) {
        return iPxstutableDADao.getallJFkcshuList(buxiStyleName, JifeiStyle, campusID, qiyeID);
    }


    //region 分配班主任
    @Override
    public Page<stuTearchVo> getStuTearch(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getStuTearch(page, queryWrapper);
    }

    //导出学员班主任
    @Override
    public List<stuTearchVo> ExportstuTeacher(QueryWrapper queryWrapper) {
        return iPxstutableDADao.ExportstuTeacher(queryWrapper);
    }
    //endregion

    //region 学员卡
    @Override
    public Page<stuCardVo> getStuCard(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getStuCard(page, queryWrapper);
    }

    //导出学员卡
    @Override
    public List<stuCardVo> ExportStuCard(QueryWrapper queryWrapper) {
        return iPxstutableDADao.ExportStuCard(queryWrapper);
    }

    //endregion

    //region 学员积分
    @Override
    public Page<stuIntegralVo> getStuIntegral(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getStuIntegral(page, queryWrapper);
    }

    //积分排名
    @Override
    public Page<JFRankVo> getJFpaiming(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getJFpaiming(page, queryWrapper);
    }
    //endregion

    //region 学员年级|年龄段
    @Override
    public Page<stuGradeVo> getStuGradeList(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getStuGradeList(page, queryWrapper);
    }

    //调级记录
    @Override
    public Page<gradeNotesVo> getGradeJiLu(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getGradeJiLu(page, queryWrapper);
    }
    //endregion

    //region 学员生日
    @Override
    public Page<stuBirthVo> getStuBirth(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getStuBirth(page, queryWrapper);
    }

    //导出
    @Override
    public List<stuBirthVo> ExportStuBirth(QueryWrapper queryWrapper) {
        return iPxstutableDADao.ExportStuBirth(queryWrapper);
    }
    //endregion

    //region 学员住宿
    @Override
    public Page<stuStayVo> getStuStay(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDADao.getStuStay(page, queryWrapper);
    }

    //获取先入住人数
    @Override
    public List<Pxstutable> getnewNum(Long roomid, Long qiyeID) {
        return iPxstutableDADao.getnewNum(roomid, qiyeID);
    }

    @Override
    public stuInRoomVo getStuInRoomVo(Long roomid, Long qiyeID) {
        return iPxstutableDADao.getStuInRoomVo(roomid, qiyeID);
    }
    //endregion

}
