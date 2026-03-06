package com.xwcloud.cloud.pkxk.Controller;


import com.xwcloud.cloud.model.log.Logxjbtable;
import com.xwcloud.cloud.pkxk.Service.ILogxjbtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class savePxLog {
    @Autowired
    ILogxjbtableService iLogxjbtableService;

    public void savepxlog(
            String logtext,
            String funcName,
            Long ID,
            String Name,
            int logType,
            Long qiyeID
    ) {
        Logxjbtable log = new Logxjbtable();
        log
                .setLogType(logType)
                .setSystemContent(logtext)
                .setFuncName(funcName);

        if (logType == 1) {
            //员工产生的日志
            log
                    .setStaffID(ID)
                    .setStaffName(Name)
                    .setLogType(1);
        } else if (logType == 2) {
            //学员产生的数据
            log
                    .setStuID(ID)
                    .setStuName(Name)
                    .setLogType(2);
        } else if (logType == 3) {
            //系统自动产生的数据
            log.setLogType(3);
        }

        log
                .setAddTime(new Date())
                .setQiyeID(qiyeID);
        iLogxjbtableService.save(log);
    }

}
