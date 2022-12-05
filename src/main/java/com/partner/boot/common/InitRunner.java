package com.partner.boot.common;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import com.partner.boot.mapper.UserMapper;
import com.partner.boot.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class InitRunner implements ApplicationRunner {

    @Resource
    UserMapper userMapper;

    /**
     * 在项目启动成功之后会运行这个方法
     *
     * @param args
     */
    @Override
    public void run(ApplicationArguments args) {

        ThreadUtil.execAsync(() -> {
            try {
                RedisUtils.ping();    // redis数据探测，初始化连接
//                userMapper.select1();  // 数据库探测，帮我在项目启动的时候查询一次数据库，防止数据库的懒加载
                log.info("启动项目tomcat连接查询成功");   // 发送一次异步的web请求，来初始化 tomcat连接
                HttpUtil.get("http://localhost:9090/");
                log.info("启动项目web请求查询成功");   // 发送一次异步的web请求，来初始化 tomcat连接
            } catch (Exception e) {
                log.warn("启动优化失败", e);
            }
        });
    }

}
