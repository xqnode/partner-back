package com.partner.boot;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpTest {

    @Test
    public void test() throws InterruptedException {

    }

    public static void main(String[] args) throws InterruptedException {
        log.info("开始执行");
        String json = "{\"username\": \"bgg\", \"password\": \"123\"}";
        int count = 5;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        List<String> list = new ArrayList<>();
        // 模拟并发请求
        for (int i = 0; i < count; i++) {
            int finalI = i;
            new Thread(() -> {
                String res = HttpUtil.post("http://localhost:9090/login", json);
                list.add(LocalDateTime.now() + "第" + finalI + "次执行: " + res);
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        list.forEach(System.out::println);
    }

}
