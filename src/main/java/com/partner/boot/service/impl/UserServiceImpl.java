package com.partner.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.partner.boot.entity.User;
import com.partner.boot.exception.ServiceException;
import com.partner.boot.mapper.UserMapper;
import com.partner.boot.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 程序员青戈
 * @since 2022-11-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User login(User user) {
        User dbUser = null;
        try {
            dbUser = getOne(new UpdateWrapper<User>().eq("username", user.getUsername()));
        } catch (Exception e) {
            throw new RuntimeException("系统异常");
        }
        if (dbUser == null) {
            throw new ServiceException("未找到用户");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        return dbUser;
    }
}
