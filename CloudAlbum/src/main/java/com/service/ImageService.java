package com.service;

import com.pojo.Images;

import java.util.List;

/**
 * @author x1yyy
 */
public interface ImageService {
    /**
     * 将照片的信息存入数据库
     *
     * @param name 存入照片的名称,需判断是否重复,重复需后加后缀标识
     * @param imgUrl 生成的url,可通过url获取照片
     * @param classify 照片的第一分类
     * @param classifyKey 照片分类的key
     * @return 存入数据库是否成功
     */
    Boolean uploadImage(String name, String imgUrl, String classify, String thumbUrl, int classifyKey, double size, int userKey);

    /**
     * 进行名称处理
     *
     * @param name 原名
     * @param userKey 用户主键
     * @return 处理过的名称供生成url
     */
    String checkRepeat(String name, int userKey);

    /**
     * 页面初始化获得所有照片
     *
     * @param userKey 用户标识符
     * @return 数据库中所有的照片
     */
    List<Images> initPage(int userKey);

    /**
     * 删除名称为name的照片
     *
     * @param name 传入的图片名
     * @param userKey 用户主键
     * @return 是否删除成功
     */
    Boolean deleteImage(String name, int userKey);

    /**
     * 查询某分类下所有照片
     *
     * @param classify 分类
     * @return 所有照片
     */
    List<Images> selectClassify(int classify, int userKey);

    int queryClassifyKey(String classify);

    List<Images> searchImages(String imageName, int userKey);
}
