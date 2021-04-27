package com.service.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.service.UserService;

/**
 * @author x1yyy
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public int getUserKey(String account, String password) {
        return userDao.getUserKey(account, password);
    }

    @Override
    public boolean register(String account, String password, int userKey) {
        return userDao.register(account, password, userKey);
    }

    @Override
    public boolean checkAccountExist(String account) {
        return userDao.checkAccountExist(account);
    }
}
