package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.paikexiaoekestuVO;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import com.xwcloud.cloud.model.entity.Pxxuanketable;

import com.xwcloud.cloud.model.Vo.teacherIDVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxxuanketableService extends IService<Pxxuanketable> {
    List<paikexiaoekestuVO> getpaikexiaoekestuList(@Param("ew") QueryWrapper queryWrapper);
    List<Pxxuanketable> selectxuanke(QueryWrapper queryWrapper);
    List<teacherIDVo> gexkStuBypkID(Long paikeID);
    List<Pxpaiketable> getNokehaoStutoPk(Long kechengID , Long stuID, Long qiyeID);
}
