package com.partner.boot.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流操作
 */
@Component
@Slf4j
public class MyFilter implements Filter {
    // 时间窗口

    // 1秒 之内只允许通过  2个  请求

    private static volatile long startTime = System.currentTimeMillis();

    private static final long windowTime = 1000L;

    private static final int door = 2;

    private static final AtomicInteger bear = new AtomicInteger(0);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        int count = bear.incrementAndGet();  // 只要来了一个人，就+1
        if (count == 1) {   // 并发安全的   //  3个请求计算都是2000 ms
            startTime = System.currentTimeMillis();
        }
        // 发生了请求
        long now = System.currentTimeMillis();
        log.info("拦截了请求， count： {}", count);
        //  0 -> 1  1 -> 2  2 -> 3
        log.info("时间窗口: {}ms, count: {}", (now - startTime), count);
        if (now - startTime <= windowTime) {
            if (count > door) {  // 超过了阈值
                // 就要进行限制  限流操作
                log.info("关门放狗成功， 拦截了请求, count: {}", count);
                return;   // 关门放狗
            }
        } else {
            // 重新进入下一个窗口
            startTime = System.currentTimeMillis();
            bear.set(1);
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        filterChain.doFilter(servletRequest, servletResponse);  //  恭喜你，可以正常通过
        log.info("接口请求的路径：{}", request.getServletPath());
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
