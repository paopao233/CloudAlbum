package com.pojo;

import java.util.Date;

/**
 * @author x1yyy
 */
public class Images {
    private int mainKey;
    private String name;
    private String imgUrl;
    private String classify;
    private Date date;
    private String thumbUrl;
    private int classifyKey;
    private double size;
    private int userKey;

    public Images() {
    }

    public Images(int mainKey, String name, String imgUrl, String classify, Date date, String thumbUrl, int classifyKey, double size, int userKey) {
        this.mainKey = mainKey;
        this.name = name;
        this.imgUrl = imgUrl;
        this.classify = classify;
        this.date = date;
        this.thumbUrl = thumbUrl;
        this.classifyKey = classifyKey;
        this.size = size;
        this.userKey = userKey;
    }

    public int getMainKey() {
        return mainKey;
    }

    public void setMainKey(int mainKey) {
        this.mainKey = mainKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public int getClassifyKey() {
        return classifyKey;
    }

    public void setClassifyKey(int classifyKey) {
        this.classifyKey = classifyKey;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getUserKey() {
        return userKey;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "Images{" +
                "mainKey=" + mainKey +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", classify='" + classify + '\'' +
                ", date=" + date +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", classifyKey=" + classifyKey +
                ", size=" + size +
                ", userKey=" + userKey +
                '}';
    }
}
