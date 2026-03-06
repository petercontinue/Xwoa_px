package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstukxqtable;

import com.xwcloud.cloud.model.Vo.listVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-29
 */
public interface IPxstukxqtableService extends IService<Pxstukxqtable> {
    List<Pxstukxqtable> getBystubx(Long stuID, Long bxkcID, Long kxqID,Long qiyeID);
    List<listVo> Getkxqstu(Long qiyeID);
    List<listVo> Getkxqbxkecheng(Long stuID,Long qiyeID);
    List<listVo> GetKxqCampus(Long buxiID, Long qiyeID);
}
