package com.dao.impl;

import com.dao.ImageDao;
import com.pojo.Images;
import com.utils.JDBCUtil;

import java.util.List;

/**
 * @author x1yyy
 */
public class ImageDaoImpl implements ImageDao {

    @Override
    public String selectName(String name, int userKey) {
        String sql = "select name from image where name like ? and userKey = ? order by mainKey DESC limit 1";
        String filed = "name";
        return (String) JDBCUtil.executeQryOneFiled(sql, filed, name, userKey);
    }

    @Override
    public boolean uploadImage(String name, String imgUrl, String classify, String thumbUrl, int classifyKey, double size, int userKey) {
        int i = JDBCUtil.executeUpdate("insert into image(name,imgUrl,classify,thumbUrl,classifyKey,size,userKey) values(?,?,?,?,?,?,?)",name,imgUrl,classify,thumbUrl,classifyKey,size,userKey);
        return !(i == 0);
    }

    @Override
    public List<Images> initPage(int userKey) {
        String sql = "select * from image where userKey = ?";
        return JDBCUtil.executeQry(Images.class, sql, userKey);
    }

    @Override
    public Boolean deleteImage(String name, int userKey) {
        String sql = "delete from image where name = ? and userKey = ?";
        return !(JDBCUtil.executeUpdate(sql, name, userKey) == 0);
    }

    @Override
    public List<Images> selectClassify(int classify, int userKey) {
        String sql = "select * from image where classifyKey = ? and userKey = ?";
        return JDBCUtil.executeQry(Images.class, sql, classify, userKey);
    }

    @Override
    public int queryClassifyKey(String classify) {
        String sql = "select classifyKey from image where classify = ?";
        Object classifyKey = JDBCUtil.executeQryOneFiled(sql, "classifyKey", classify);
        return classifyKey == null ? -1 : (int) classifyKey;
    }

    @Override
    public List<Images> searchImages(String imageName, int userKey) {
        String sql = "select * from image where name like ? and userKey = ?";
        return JDBCUtil.executeQry(Images.class, sql, imageName, userKey);
    }
}
