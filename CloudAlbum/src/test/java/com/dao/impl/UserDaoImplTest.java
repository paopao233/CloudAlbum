package com.dao.impl;

import junit.framework.TestCase;

public class UserDaoImplTest extends TestCase {
    UserDaoImpl userDao = new UserDaoImpl();

    public void testRegister() {
        System.out.println(userDao.register("123", "123", 777));
    }
}