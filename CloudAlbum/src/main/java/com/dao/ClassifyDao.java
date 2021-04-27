package com.dao;

import com.pojo.Classify;

import java.util.List;

/**
 * @author x1yyy
 */
public interface ClassifyDao {
    /**
     * 获取所有分类
     * @return 分类
     */
    List<Classify> getAllClassify(int userKey);
}
