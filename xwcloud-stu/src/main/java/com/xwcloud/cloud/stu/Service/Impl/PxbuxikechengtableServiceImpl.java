package com.xwcloud.cloud.stu.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
import com.xwcloud.cloud.stu.Dao.IPxbuxikechengtableDao;
import com.xwcloud.cloud.stu.Service.IPxbuxikechengtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-06
 */
@Service
public class PxbuxikechengtableServiceImpl extends ServiceImpl<IPxbuxikechengtableDao, Pxbuxikechengtable> implements IPxbuxikechengtableService {

    @Autowired
    IPxbuxikechengtableDao iPxbuxikechengtableDao;

    @Override
    public List<Pxbuxikechengtable> selectbxkc(QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.selectbxkc(queryWrapper);
    }

    @Override
    public List<NewhebingKcVo> getHebingTokecheng(Long buxiID, Long stuID, Long qiyeID) {
        return iPxbuxikechengtableDao.getHebingTokecheng(buxiID, stuID, qiyeID);
    }

    @Override
    public List<ChangekechengVo> getnewkcInfo(Long qiyeID) {
        return iPxbuxikechengtableDao.getnewkcInfo(qiyeID);
    }

    @Override
    public List<HashMap<String, Object>> getOtherKechengs(String stuID, Long qiyeID, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getOtherKechengs(stuID, qiyeID, queryWrapper);
    }

    //获取学员课程
    @Override
    public Page<buxiKeChengVo> getbuxiCourse(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getbuxiCourse(page, queryWrapper);
    }

    @Override
    public Page<kxqKcInfoVo> getkxqinfo(Page page, Long buxiID, Long qiyeID) {
        return iPxbuxikechengtableDao.getkxqinfo(page, buxiID, qiyeID);
    }

    @Override
    public List<addKSBkcVo> Getnewkc(Long campusID, int jifeiStyleID, Long qiyeID) {
        return iPxbuxikechengtableDao.Getnewkc(campusID, jifeiStyleID, qiyeID);
    }

    //导出学员课程
    @Override
    public List<buxiKeChengVo> ExportbuxiCourse(QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.ExportbuxiCourse(queryWrapper);
    }

    @Override
    public List<stubxStyleVo> getstubxStyle(Long kcID, Long qiyeID) {
        return iPxbuxikechengtableDao.getstubxStyle(kcID, qiyeID);
    }


    //region 停课
    @Override
    public List<Pxbuxikechengtable> getbuxikc(Long stuID, Long qiyeID) {
        return iPxbuxikechengtableDao.getbuxikc(stuID, qiyeID);
    }

    @Override
    public List<Pxkechengtable> getKc(Long kcID, Long qiyeID) {
        return iPxbuxikechengtableDao.getKc(kcID, qiyeID);
    }


    //获取转送课程
    @Override
    public Page<transferVo> getTransfer(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getTransfer(page, queryWrapper);
    }

    @Override
    public List<HashMap<String, Object>> getZSkechengs(String stuID, Long qiyeID, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getZSkechengs(stuID, qiyeID, queryWrapper);
    }

    @Override
    public List<kechengVos> getzhuangsongkecheng(QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getzhuangsongkecheng(queryWrapper);
    }

    @Override
    public List<kechengVos> getzhuangsongdaykecheng(Long stuID, Long qiyeID) {
        return iPxbuxikechengtableDao.getzhuangsongdaykecheng(stuID, qiyeID);
    }

    @Override
    public List<listVo> getcamkecheng(Long campusID, Long qiyeID) {
        return iPxbuxikechengtableDao.getcamkecheng(campusID, qiyeID);
    }

    //导出转送课程
    @Override
    public List<transferVo> ExportTransfer(QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.ExportTransfer(queryWrapper);
    }

    @Override
    public List<Pxbuxikechengtable> getStubxkc(Long stuID, Long kechengID, Long qiyeID) {
        return iPxbuxikechengtableDao.getStubxkc(stuID, kechengID, qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> getStubxkcNoShow(Long stuID, Long kechengID, Long qiyeID) {
        return iPxbuxikechengtableDao.getStubxkcNoShow(stuID, kechengID, qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> getShoubxkc(Long ID, BigDecimal kechengprice, Long qiyeID) {
        return iPxbuxikechengtableDao.getShoubxkc(ID, kechengprice, qiyeID);
    }

    //region 赠送课时
    @Override
    public Page<zengsongVo> getZengSong(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getZengSong(page, queryWrapper);
    }

    //导出赠送课时
    @Override
    public List<zengsongVo> ExportZengSong(QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.ExportZengSong(queryWrapper);
    }

    @Override
    public List<Pxbuxikechengtable> getBxToStuAKc(Long stuID, Long kcID, Long qiyeID) {
        return iPxbuxikechengtableDao.getBxToStuAKc(stuID, kcID, qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> getBxToType(Long stuID, Long kcID, BigDecimal kechengprice, Long qiyeID) {
        return iPxbuxikechengtableDao.getBxToType(stuID, kcID, kechengprice, qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> getByIDAStuID(Long id, Long stuID, Long qiyeID) {
        return iPxbuxikechengtableDao.getByIDAStuID(id, stuID, qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> getByKechengID(Long kcID, Long qiyeID) {
        return iPxbuxikechengtableDao.getByKechengID(kcID, qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> getJF3(Long ID, Long stuID, Long qiyeID) {
        return iPxbuxikechengtableDao.getJF3(ID, stuID, qiyeID);
    }

    //endregion

    //region 课程变动流水
    @Override
    public Page<bxChangeVo> getkcLiuShui(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getkcLiuShui(page, queryWrapper);
    }

    //导出课程变动流水
    @Override
    public List<bxChangeVo> ExportkcLiuShui(Long qiyeID) {
        return iPxbuxikechengtableDao.ExportkcLiuShui(qiyeID);
    }
    //endregion

    //region 学员成绩
    @Override
    public Page<scoreVo> getScore(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getScore(page, queryWrapper);
    }

    //导出学员成绩
    @Override
    public List<scoreVo> ExportScore(QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.ExportScore(queryWrapper);
    }
    //endregion

    //region 学员考级
    @Override
    public Page<kaojiVo> getKaoJi(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getKaoJi(page, queryWrapper);
    }

    //导出考级
    @Override
    public List<kaojiVo> ExportKaoJi(Long qiyeID) {
        return iPxbuxikechengtableDao.ExportKaoJi(qiyeID);
    }
    //endregion

    //发证
    @Override
    public Page<fazhengVo> getfazhengPage(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getfazhengPage(page, queryWrapper);
    }

    @Override
    public Page<classgetstuVo> getstumingdan(Page page, Long classID, Long qiyeID) {
        return iPxbuxikechengtableDao.getstumingdan(page, classID, qiyeID);
    }

    @Override
    public Page<ZbCbVo> getzhuangbanchaban(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getzhuangbanchaban(page, queryWrapper);
    }

    @Override
    public List<Pxbuxikechengtable> getByStuID(Long stuID, Long qiyeID) {
        return iPxbuxikechengtableDao.getByStuID(stuID, qiyeID);
    }

    @Override
    public List<listVo> getoldclass(Long buxiID, Long qiyeID) {
        return iPxbuxikechengtableDao.getoldclass(buxiID, qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> getAny(Long qiyeID, Long id) {
        return iPxbuxikechengtableDao.getAny(qiyeID, id);
    }

    @Override
    public Page<havaclassVo> getHaveClass(Page page, Long id, Long qiyeID) {
        return iPxbuxikechengtableDao.getHaveClass(page, id, qiyeID);
    }

    @Override
    public List<allrkeshiVo> getallrkeshi(Long kechengID, Long stuID, Long qiyeID) {
        return iPxbuxikechengtableDao.getallrkeshi(kechengID, stuID, qiyeID);
    }

    @Override
    public List<DeriveChabanXinxiVo> DeriveChabanXinxi(Long qiyeID) {
        return iPxbuxikechengtableDao.DeriveChabanXinxi(qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> getstuALLbuxiKC(Long stuID, Long buxiID, Long qiyeID) {
        return iPxbuxikechengtableDao.getstuALLbuxiKC(stuID, buxiID, qiyeID);
    }

    @Override
    public List<HashMap<String, Object>> getstuOtherbuxi(QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getstuOtherbuxi(queryWrapper);
    }

    @Override
    public Page<HashMap<String, Object>> getshareInfo(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getshareInfo(page, queryWrapper);
    }


}
