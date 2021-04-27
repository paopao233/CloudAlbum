package com.controller;

import com.pojo.Classify;
import com.service.ClassifyService;
import com.service.impl.ClassifyServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author x1yyy
 */
@Controller
public class ClassifyController {
    ClassifyService classifyService = new ClassifyServiceImpl();

    @ResponseBody
    @RequestMapping("/getAllClassify")
    public List<Classify> getAllClassify(HttpServletRequest request) {
        int userKey = Integer.parseInt(request.getHeader("userKey"));
        return classifyService.getAllClassify(userKey);
    }
}