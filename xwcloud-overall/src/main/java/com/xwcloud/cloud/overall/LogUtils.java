package com.xwcloud.cloud.overall;

import com.xwcloud.cloud.model.log.Logxjbtable;
import com.xwcloud.cloud.overall.Dao.ILogxiugaitableDao;
import com.xwcloud.cloud.overall.Dao.ILogxttableDao;
import com.xwcloud.cloud.model.log.Logxiugaijilutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LogUtils {

    @Autowired
    ILogxiugaitableDao iLogxiugaitableDao;
    @Autowired
    ILogxttableDao iLogxttableDao;

    public void setXGLog(Logxiugaijilutable xiugaijilu){
        iLogxiugaitableDao.insert(xiugaijilu);
    }

    public void setXTLog(Logxjbtable xtLog){
        iLogxttableDao.insert(xtLog);
    }

    /**
     * 员工日志
     * @param content
     * @param funcName
     * @param staffID
     * @param qiyeId
     */
    @Async
    public void setXTLogByStaff(String content,String funcName,String staffID,String qiyeId){
        Logxjbtable logxjbtable = new Logxjbtable();
        logxjbtable.setAddTime(new Date());
        logxjbtable.setLogType(1);
        logxjbtable.setSystemContent(content);
        logxjbtable.setFuncName(funcName);
        logxjbtable.setStaffID(Long.valueOf(staffID));
        logxjbtable.setQiyeID(Long.valueOf(qiyeId));
        iLogxttableDao.insert(logxjbtable);
    }

    /**
     * 学生日志
     * @param content
     * @param funcName
     * @param qiyeId
     */
    @Async
    public void setXTLogByStu(String content,String funcName,String stuId,String qiyeId){
        Logxjbtable logxjbtable = new Logxjbtable();
        logxjbtable.setAddTime(new Date());
        logxjbtable.setLogType(2);
        logxjbtable.setSystemContent(content);
        logxjbtable.setFuncName(funcName);
        logxjbtable.setStuID(Long.valueOf(stuId));
        logxjbtable.setQiyeID(Long.valueOf(qiyeId));
        iLogxttableDao.insert(logxjbtable);
    }

    /**
     * 系统自动生成日志
     * @param content
     * @param funcName
     * @param qiyeId
     */
    @Async
    public void setXTLogByXt(String content,String funcName,String qiyeId){
        Logxjbtable logxjbtable = new Logxjbtable();
        logxjbtable.setAddTime(new Date());
        logxjbtable.setLogType(3);
        logxjbtable.setSystemContent(content);
        logxjbtable.setFuncName(funcName);
        logxjbtable.setQiyeID(Long.valueOf(qiyeId));
        iLogxttableDao.insert(logxjbtable);
    }

    /**
     * 修改记录
     * @param
     */
    @Async
    public void setXGLogByStr(String stuID,String stuName,String qian,String hou,String staffID,int type,String qiyeID){
        Logxiugaijilutable logxiugaijilutable =new Logxiugaijilutable();
        logxiugaijilutable.setQiyeID(qiyeID);
        logxiugaijilutable.setStuID(stuID);
        logxiugaijilutable.setStuName(stuName);
        logxiugaijilutable.setXiugaiqianxinxi(qian);
        logxiugaijilutable.setXiugaihouxinxi(hou);
        logxiugaijilutable.setXiugaistaffID(staffID);
        logxiugaijilutable.setType(type);
        logxiugaijilutable.setQiyeID(qiyeID);
        logxiugaijilutable.setXiugaidateTime(new Date());
        iLogxiugaitableDao.insert(logxiugaijilutable);
    }
}
