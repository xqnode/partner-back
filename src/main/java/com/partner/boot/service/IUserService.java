package com.partner.boot.service;

import com.partner.boot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 程序员青戈
 * @since 2022-11-28
 */
public interface IUserService extends IService<User> {

    User login(User user);
}
