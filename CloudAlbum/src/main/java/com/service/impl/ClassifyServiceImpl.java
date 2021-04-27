package com.service.impl;

import com.dao.ClassifyDao;
import com.dao.impl.ClassifyDaoImpl;
import com.pojo.Classify;
import com.service.ClassifyService;

import java.util.List;

/**
 * @author x1yyy
 */
public class ClassifyServiceImpl implements ClassifyService {
    ClassifyDao classifyDao = new ClassifyDaoImpl();

    /**
     * 获取所有分类
     * @return 分类
     */
    @Override
    public List<Classify> getAllClassify(int userKey) {
        return classifyDao.getAllClassify(userKey);
    }
}
