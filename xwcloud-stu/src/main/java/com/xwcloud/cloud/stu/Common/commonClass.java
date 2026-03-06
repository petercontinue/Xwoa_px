package com.xwcloud.cloud.stu.Common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import com.xwcloud.cloud.stu.Service.IPxbuxikechengtableService;
import com.xwcloud.cloud.stu.Service.IPxpaiketableService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 公共类封装
 */
public class commonClass {

    @Autowired
    IPxbuxikechengtableService iPxbuxikechengtableService;

    @Autowired
    IPxpaiketableService iPxpaiketableService;



    /**
     * 学员插班
     * long buxiID : 补习课程ID，pxbuxikechengtable表的id
     * long classID： 要插入的班级ID
     * String FromPaikeDate： 从这个班的哪一天的排课开始插入
     * long qiyeID：企业ID
     */
    public void stuInsertClass(long buxiID,long classID,String FromPaikeDate,long qiyeID){
        QueryWrapper<Pxpaiketable> pxpaiketableQueryWrapper = new QueryWrapper<Pxpaiketable>();
        pxpaiketableQueryWrapper.ge("haveClassDate",FromPaikeDate);
        List<Pxpaiketable> listpk = iPxpaiketableService.list(pxpaiketableQueryWrapper);
    }

    /**
     * 学员退班
     * long buxiID : 补习课程ID，pxbuxikechengtable表的id
     * long classID： 要插入的班级ID
     * String FromPaikeDate： 从这个班的哪一天的排课开始插入
     * long qiyeID：企业ID
     */
    public void stuExitClass(long buxiID,long classID,String FromPaikeDate,long qiyeID){

    }
}
