package com.partner.boot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.partner.boot.entity.User;
import com.partner.boot.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PartnerBackApplicationTests {

    @Autowired
    IUserService userService;

    @Test
    void contextLoads() {
        userService.removeById(7);
        System.out.println("查询username weg的用户 ======" + userService.getOne(new QueryWrapper<User>().eq("username", "weg")));
    }

}
