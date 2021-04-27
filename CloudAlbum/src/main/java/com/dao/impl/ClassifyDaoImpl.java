package com.dao.impl;

import com.dao.ClassifyDao;
import com.pojo.Classify;
import com.utils.JDBCUtil;

import java.util.List;

/**
 *
 */
public class ClassifyDaoImpl implements ClassifyDao {
    /**
     * 获取所有分类
     * @return 分类
     */
    @Override
    public List<Classify> getAllClassify(int userKey) {
        String sql = "select DISTINCT(classify),classifyKey from image where userKey = ?";
        String filed1 = "classify";
        String filed2 = "classifyKey";
        return JDBCUtil.executeQry(Classify.class, sql, userKey);
    }
}
