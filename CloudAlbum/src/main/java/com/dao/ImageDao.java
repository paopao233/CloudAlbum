package com.dao;

import com.pojo.Images;

import java.util.List;

/**
 * @author x1yyy
 */
public interface ImageDao {

    /**
     * 在照片存入数据库前先检查照片的名称是否已经存在
     *
     * @param name 要存入的照片名称
     * @param userKey 用户主键
     * @return 模糊查询后的照片名称的最后一个，存在或null
     */
    String selectName(String name, int userKey);

    /**
     * 照片的信息存入数据库
     *
     * @param name 照片的名称,经过处理只存在唯一
     * @param imgUrl Controller生成的照片的url
     * @param classify 照片的类别
     * @return 返回照片是否存入成功
     */
    boolean uploadImage(String name, String imgUrl, String classify, String thumbUrl, int classifyKey, double size, int userKey);


    /**
     * 页面初始化获得所有照片
     *
     * @param userKey 用户标识符
     * @return 数据库中所有的照片
     */
    List<Images> initPage(int userKey);

    /**
     * 删除
     *
     * @param name 名
     * @param userKey
     * @return 是否成功
     */
    Boolean deleteImage(String name, int userKey);

    /**
     * 查询某分类下所有照片
     *
     * @param userKey 用户主键
     * @param classify 分类
     * @return 所有照片
     */
    List<Images> selectClassify(int classify, int userKey);

    int queryClassifyKey(String classify);

    List<Images> searchImages(String imageName, int userKey);
}
