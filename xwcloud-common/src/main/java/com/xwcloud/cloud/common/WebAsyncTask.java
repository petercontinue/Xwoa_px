package com.xwcloud.cloud.common;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebAsyncTask {
    private long Timeout = 20 * 1000;// 超时默认20秒

    public WebAsyncTask() {
        new WebAsyncTask(null, 0);
    }

    public WebAsyncTask( AsyncListener asyncListener) {
        new WebAsyncTask(asyncListener, 0);
    }

    public WebAsyncTask( AsyncListener asyncListener, long timeout) {
        Timeout=timeout == 0 ? Timeout : timeout;
        _asyncListener=asyncListener == null ? _asyncListener : asyncListener;

    }

    public void start(AsyncContext _asyncContext,runnableCallBack run) {
        _asyncContext.setTimeout(Timeout);
        _asyncContext.addListener(_asyncListener);
        _asyncContext.start(new Runnable() {
            @Override
            public void run() {
                run.run((HttpServletResponse) _asyncContext.getResponse());
                _asyncContext.complete();
            }
        });
    }

    /**
     * 发送消息
     *
     * @param response
     * @param isSuccess
     * @param msg
     * @param code      返回编号
     * @return
     */
    public static boolean sendJsonMsg(HttpServletResponse response, boolean isSuccess, String msg, String code) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(200);
        PrintWriter out = null;
        try {
            JSONObject res = new JSONObject();
            res.put("success", isSuccess);
            res.put("msg", msg);
            res.put("code", code);
            out = response.getWriter();
            out.append(res.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 监听异步
    private AsyncListener _asyncListener = new AsyncListener() {
        @Override
        public void onComplete(AsyncEvent asyncEvent) throws IOException {
            System.out.println("执行完成");
        }

        @Override
        public void onTimeout(AsyncEvent asyncEvent) throws IOException {
            System.out.println("超时了...");
        }

        @Override
        public void onError(AsyncEvent asyncEvent) throws IOException {
            sendJsonMsg((HttpServletResponse) asyncEvent.getAsyncContext().getResponse(), false, "线程执行错误", "N");
            System.out.println("发生错误：" + asyncEvent.getThrowable());
        }

        @Override
        public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
            System.out.println("线程开始");
        }
    };


    public interface runnableCallBack {
        //执行回调操作的方法
        void run(HttpServletResponse response);
    }
}
