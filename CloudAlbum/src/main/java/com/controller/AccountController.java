package com.controller;

import com.service.UserService;
import com.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @author x1yyy
 */
@Controller
public class AccountController {
    final static int REGISTER_EXIST = -1;
    final static int REGISTER_FAILED = -2;

    UserService userService = new UserServiceImpl();

    @ResponseBody
    @RequestMapping("/login")
    public int login(HttpServletRequest request) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        return userService.getUserKey(account, password);
    }

    @ResponseBody
    @RequestMapping("/register")
    public int register(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        int userKey = (int) (Math.random() * (999999 - 100000) + 100000);

        String account = request.getParameter("account");
        String password = request.getParameter("password");

        if (userService.checkAccountExist(account)) {
            return REGISTER_EXIST;
        }

        boolean isRegister = userService.register(account, password, userKey);

        return isRegister ? userKey : REGISTER_FAILED;
    }
}
