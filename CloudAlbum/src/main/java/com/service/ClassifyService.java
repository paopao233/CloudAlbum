package com.service;

import com.pojo.Classify;

import java.util.List;

/**
 * @author x1yyy
 */
public interface ClassifyService {
    /**
     *  获取所有分类
     * @return 分类
     */
    List<Classify> getAllClassify(int userKey);
}
