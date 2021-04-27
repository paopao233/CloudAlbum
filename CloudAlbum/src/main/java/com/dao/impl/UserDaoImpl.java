package com.dao.impl;

import com.dao.UserDao;
import com.pojo.User;
import com.utils.JDBCUtil;

import static com.utils.JDBCUtil.executeUpdate;

/**
 * @author x1yyy
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int getUserKey(String account, String password) {
        String sql = "select userKey from user where account = ? and password = ?";
        int userKey = -1;

        try {
            userKey = (int)JDBCUtil.executeQryOneFiled(sql, "userKey", account, password);
        } catch (Exception e) {
            return -1;
        }

        return userKey;
    }

    @Override
    public boolean register(String account, String password, int userKey) {
        String sql = "insert into user(userKey,account,password) values(?,?,?)";
        int isSuccess = JDBCUtil.executeUpdate(sql, userKey, account, password);
        return isSuccess != 0;
    }

    @Override
    public boolean checkAccountExist(String account) {
        String sql = "select * from user where account = ?";
        User user = JDBCUtil.executeQryOne(User.class, sql, account);
        return user != null;
    }
}
