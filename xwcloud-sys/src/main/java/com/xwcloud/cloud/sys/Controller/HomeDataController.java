package com.xwcloud.cloud.sys.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.model.Sso.LoginUser;
import com.xwcloud.cloud.model.Vo.yejidataVO;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import com.xwcloud.cloud.sys.Service.IPxcampustableService;
import com.xwcloud.cloud.sys.Service.IPxliushuizhangtableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/sys/home")
@Api(tags = "欢迎页数据信息")
public class HomeDataController {
    @Autowired
    IPxliushuizhangtableService iPxliushuizhangtableService;

    @Autowired
    IPxcampustableService iPxcampustableService;

    @ResponseBody
    @RequestMapping(value = "/getCampusYejiList", method = RequestMethod.GET)
    @ApiOperation(value = "查询校区业绩信息")
    public AjaxJson getCampusYejiList(HttpServletRequest request, int type) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        Calendar cal = Calendar.getInstance();
        List<Pxcampustable> pxcampustable = iPxcampustableService.list(queryWrapper);
        List<yejidataVO> yijidataList = new ArrayList<>();
        List<String> AllDate = new ArrayList<>();
        cal.setTime(new Date());
        int days = 0;
        if (type == 1) {
            days = 7;
        } else if (type == 2) {
            days = 15;
        } else if (type == 3) {
            days = 30;
        } else if (type == 4) {
            days = 90;
        } else if (type == 5) {
            days = 180;
        } else {
            days = 365;
        }
        for (int i = 0; i < days; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            if (i == 0) {
                Date date = (Date) sdf.parse(new Date().toString());
                String formatStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                AllDate.add(formatStr);
            } else {
                cal.add(Calendar.DATE, -1);
                Date date = (Date) sdf.parse(cal.getTime().toString());
                String formatStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                AllDate.add(formatStr);
            }
        }
        for (Pxcampustable item : pxcampustable) {
            yejidataVO yejidataVO = new yejidataVO();
            yejidataVO.setCampusName(item.getCampusName());
            yejidataVO.setDateList(AllDate);
            List<BigDecimal> yejiList = new ArrayList<>();
            for (String date : AllDate) {

                yejiList.add((BigDecimal)iPxliushuizhangtableService.GetDayYejiMoney(loginUser.getQiyeID(), item.getId(), date));

            }
            yejidataVO.setYejiData(yejiList);
            yijidataList.add(yejidataVO);
        }
        ajaxJson.setObj(yijidataList);
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "/GetIndexKehaoInfo",method = RequestMethod.GET)
    public AjaxJson GetIndexKehaoInfo(HttpServletRequest request,int type) throws ParseException {
        AjaxJson ajaxJson=new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        Calendar cal = Calendar.getInstance();
        List<Pxcampustable> pxcampustable = iPxcampustableService.list(queryWrapper);
        List<yejidataVO> yijidataList = new ArrayList<>();
        List<String> AllDate = new ArrayList<>();
        cal.setTime(new Date());
        int days = 0;
        if (type == 1) {
            days = 7;
        } else if (type == 2) {
            days = 15;
        } else if (type == 3) {
            days = 30;
        } else if (type == 4) {
            days = 90;
        } else if (type == 5) {
            days = 180;
        } else {
            days = 365;
        }
        for (int i = 0; i < days; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            if (i == 0) {
                Date date = (Date) sdf.parse(new Date().toString());
                String formatStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                AllDate.add(formatStr);
            } else {
                cal.add(Calendar.DATE, -1);
                Date date = (Date) sdf.parse(cal.getTime().toString());
                String formatStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                AllDate.add(formatStr);
            }
        }
        for (Pxcampustable item : pxcampustable) {
            yejidataVO yejidataVO = new yejidataVO();
            yejidataVO.setCampusName(item.getCampusName());
            yejidataVO.setDateList(AllDate);
            List<BigDecimal> yejiList = new ArrayList<>();
            for (String date : AllDate) {
                yejiList.add((BigDecimal)iPxliushuizhangtableService.GetKehaoMoney(loginUser.getQiyeID(), item.getId(), date));
            }
            yejidataVO.setYejiData(yejiList);
            yijidataList.add(yejidataVO);
        }
        ajaxJson.setObj(yijidataList);
        return ajaxJson;
    }
}
