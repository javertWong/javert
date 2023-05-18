package com.wjk.wxappletweb.service;

import com.wjk.wxappletweb.vo.User;

/**
 * @author wangjunkai
 * @description
 * @date 2023/5/18 14:06
 */
public interface UserService {
    String login(User user);

    String loginByKey(User user);
}
