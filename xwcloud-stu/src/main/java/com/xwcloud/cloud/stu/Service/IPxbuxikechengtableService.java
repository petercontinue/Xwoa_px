package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.*;

import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-06
 */
public interface IPxbuxikechengtableService extends IService<Pxbuxikechengtable> {

    List<Pxbuxikechengtable> selectbxkc(QueryWrapper queryWrapper);

    List<NewhebingKcVo> getHebingTokecheng(Long buxiID, Long stuID, Long qiyeID);

    List<ChangekechengVo> getnewkcInfo(Long qiyeID);

    List<HashMap<String, Object>> getOtherKechengs(String stuID, Long qiyeID, QueryWrapper queryWrapper);

    //获取学员课程
    Page<buxiKeChengVo> getbuxiCourse(Page page, QueryWrapper queryWrapper);

    Page<kxqKcInfoVo> getkxqinfo(Page page, Long buxiID, Long qiyeID);

    List<addKSBkcVo> Getnewkc(Long campusID, int jifeiStyleID, Long qiyeID);

    List<buxiKeChengVo> ExportbuxiCourse(@Param("ew") QueryWrapper queryWrapper);

    List<stubxStyleVo> getstubxStyle(Long kcID, Long qiyeID);

    //停课
    List<Pxbuxikechengtable> getbuxikc(Long stuID, Long qiyeID);

    List<Pxkechengtable> getKc(Long kcID, Long qiyeID);

    //获取转送课程
    Page<transferVo> getTransfer(Page page, QueryWrapper queryWrapper);

    List<HashMap<String, Object>> getZSkechengs(String stuID, Long qiyeID, QueryWrapper queryWrapper);

    List<kechengVos> getzhuangsongkecheng(QueryWrapper queryWrapper);

    List<kechengVos> getzhuangsongdaykecheng(Long stuID, Long qiyeID);

    List<listVo> getcamkecheng(Long campusID, Long qiyeID);

    //导出转送课时
    List<transferVo> ExportTransfer(@Param("ew") QueryWrapper queryWrapper);

    List<Pxbuxikechengtable> getStubxkc(Long stuID, Long kechengID, Long qiyeID);

    List<Pxbuxikechengtable> getStubxkcNoShow(Long stuID, Long kechengID, Long qiyeID);

    List<Pxbuxikechengtable> getShoubxkc(Long ID, BigDecimal kechengprice, Long qiyeID);

    //region 赠送课时
    Page<zengsongVo> getZengSong(Page page, QueryWrapper queryWrapper);

    //导出赠送课时
    List<zengsongVo> ExportZengSong(@Param("ew") QueryWrapper queryWrapper);

    List<Pxbuxikechengtable> getBxToStuAKc(Long stuID, Long kcID, Long qiyeID);

    List<Pxbuxikechengtable> getBxToType(Long stuID, Long kcID, BigDecimal kechengprice, Long qiyeID);

    List<Pxbuxikechengtable> getByIDAStuID(Long id, Long stuID, Long qiyeID);

    List<Pxbuxikechengtable> getByKechengID(Long kcID, Long qiyeID);

    List<Pxbuxikechengtable> getJF3(Long ID, Long stuID, Long qiyeID);
    //endregion


    //课程变动流水
    Page<bxChangeVo> getkcLiuShui(Page page, QueryWrapper queryWrapper);

    //导出变动流水
    List<bxChangeVo> ExportkcLiuShui(Long qiyeID);

    //学员成绩
    Page<scoreVo> getScore(Page page, QueryWrapper queryWrapper);

    //导出学员成绩
    List<scoreVo> ExportScore(@Param("ew") QueryWrapper queryWrapper);

    //学员考级
    Page<kaojiVo> getKaoJi(Page page, QueryWrapper queryWrapper);

    //导出考级
    List<kaojiVo> ExportKaoJi(Long qiyeID);

    //发证
    Page<fazhengVo> getfazhengPage(Page page, QueryWrapper queryWrapper);

    Page<classgetstuVo> getstumingdan(Page page, Long classID, Long qiyeID);

    Page<ZbCbVo> getzhuangbanchaban(Page page, QueryWrapper queryWrapper);

    List<Pxbuxikechengtable> getByStuID(Long stuID, Long qiyeID);

    List<listVo> getoldclass(Long buxiID, Long qiyeID);

    List<Pxbuxikechengtable> getAny(Long qiyeID, Long id);

    Page<havaclassVo> getHaveClass(Page page, Long id, Long qiyeID);

    List<allrkeshiVo> getallrkeshi(Long kechengID, Long stuID, Long qiyeID);

    List<DeriveChabanXinxiVo> DeriveChabanXinxi(Long qiyeID);

    List<Pxbuxikechengtable> getstuALLbuxiKC(Long stuID, Long buxiID, Long qiyeID);

    List<HashMap<String, Object>> getstuOtherbuxi(QueryWrapper queryWrapper);

    Page<HashMap<String, Object>> getshareInfo(Page page,QueryWrapper queryWrapper);
}
