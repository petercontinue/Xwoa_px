package com.xwcloud.cloud.overall.BizException;

import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.common.ZidongException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ZidongException.class)
    @ResponseBody
    public AjaxJson error(ZidongException e) {
        System.out.printf("发生自定义异常！原因是：导入的Excel文件与模板文件有差异！");
        e.printStackTrace();
        AjaxJson ajaxJson = new AjaxJson();
        String msg = e.getMsg();
        System.out.printf("====================="+msg);
        ajaxJson.setMsg(msg);
        ajaxJson.setSuccess(false);
        ajaxJson.setCode("N");
        return ajaxJson;
    }

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public AjaxJson bizExceptionHandler(HttpServletRequest req, BizException e) {
        System.out.println("发生业务异常！原因是：" + e.getErrorMsg());
        e.printStackTrace();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setCode(e.getErrorCode());
        ajaxJson.setSuccess(false);
        return ajaxJson;
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public AjaxJson exceptionHandler(HttpServletRequest req, NullPointerException e) {
        System.out.printf("发生空指针异常！原因是:", e.toString());
        e.printStackTrace();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(false);
        ajaxJson.setMsg(e.getMessage());
        return ajaxJson;
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public AjaxJson exceptionHandler(HttpServletRequest req, Exception e) {
        System.out.println("未知异常！");
        e.printStackTrace();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(false);
        ajaxJson.setMsg(e.getMessage());
        return ajaxJson;
    }


}
