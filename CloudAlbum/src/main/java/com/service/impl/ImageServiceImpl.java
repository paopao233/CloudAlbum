package com.service.impl;

import com.dao.ImageDao;
import com.dao.impl.ImageDaoImpl;
import com.pojo.Images;
import com.service.ImageService;

import java.util.List;

/**
 * @author x1yyy
 */
public class ImageServiceImpl implements ImageService {
    ImageDao imageDao = new ImageDaoImpl();

    @Override
    public String checkRepeat(String name, int userKey) {
        // 将name进行处理进行模糊查询
        int index = name.lastIndexOf(".");
        String checkName = name.substring(0, index) + "%" + name.substring(index);

        // 检查照片名称是否重复
        String existName = imageDao.selectName(checkName, userKey);

        // 数据库中存在相同名称的照片
        if (existName != null) {
            int pointIndex = existName.lastIndexOf(".");
            int numberIndex = existName.lastIndexOf("-");
            try {
                if (numberIndex == -1) {
                    // 只存在一个名称相同的照片未进行编号,后缀为1
                    name = name.substring(0,pointIndex) + "-1" + name.substring(pointIndex);
                } else {
                    if (existName.equals(name)) {
                        // 存在名称相同的照片且照片名称本来就存在数字后缀
                        name = name.substring(0,pointIndex) + "-1" + name.substring(pointIndex);
                    } else {
                        // 存在名称相同的照片且存在后缀,后缀加1
                        int number = Integer.parseInt(existName.substring(numberIndex + 1,pointIndex)) + 1;
                        name = existName.substring(0,numberIndex + 1) + number + existName.substring(pointIndex);
                    }
                }
            } catch (NumberFormatException e) {
                // 只存在一个名称相同的照片未进行编号,且存在字符串“-”转换为int失败,后缀为1
                name = name.substring(0,pointIndex) + "-1" + name.substring(pointIndex);
            }
        }
        return name;
    }


    @Override
    public List<Images> initPage(int userKey) {
        return imageDao.initPage(userKey);
//        return list.stream().collect(Collectors.groupingBy(Images :: getClassify));
    }

    @Override
    public Boolean deleteImage(String name, int userKey) {
        return imageDao.deleteImage(name, userKey);
    }

    @Override
    public List<Images> selectClassify(int classify, int userKey) {
        return imageDao.selectClassify(classify, userKey);
    }

    @Override
    public int queryClassifyKey(String classify) {
        int classifyKey = imageDao.queryClassifyKey(classify);
        if (classifyKey == -1) {
            classifyKey = (int) (Math.random() * (999999 - 100000) + 100000);
        }
        return classifyKey;
    }

    @Override
    public List<Images> searchImages(String imageName, int userKey) {
        imageName = "%" + imageName + "%";
        return imageDao.searchImages(imageName, userKey);
    }

    @Override
    public Boolean uploadImage(String name, String imgUrl, String classify, String thumbUrl, int classifyKey, double size, int userKey) {
        return imageDao.uploadImage(name, imgUrl, classify, thumbUrl, classifyKey, size, userKey);
    }
}
