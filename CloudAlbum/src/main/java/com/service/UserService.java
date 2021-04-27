package com.service;

import org.springframework.stereotype.Service;

/**
 * @author x1yyy
 */
@Service
public interface UserService {
    /**
     *  搜索用户的主键
     *
     * @param account 账号
     * @param password 密码
     * @return 搜索到的用户key
     */
    int getUserKey(String account, String password);

    /**
     * 将要注册的用户账号密码主键存入数据库
     *
     * @param account 账号
     * @param password 密码
     * @param userKey 主键
     * @return 存入结果
     */
    boolean register(String account, String password, int userKey);

    boolean checkAccountExist(String account);
}
