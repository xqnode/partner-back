package com.partner.boot.common;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import com.partner.boot.entity.User;
import com.partner.boot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitRunner implements ApplicationRunner {

    @Autowired
    IUserService userService;

    /**
     * 在项目启动成功之后会运行这个方法
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 帮我在项目启动的时候查询一次数据库，防止数据库的懒加载
        // 发送一次异步的web请求，来初始化 tomcat连接
        ThreadUtil.execAsync(() -> {
            HttpUtil.get("http://localhost:9090/");
            log.info("启动项目tomcat连接查询成功");
        });
    }

}
