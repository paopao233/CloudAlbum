package com.pojo;

public class Classify {
    private String classify;
    private int classifyKey;

    public Classify() {
    }

    public Classify(String classify, int classifyKey) {
        this.classify = classify;
        this.classifyKey = classifyKey;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public int getClassifyKey() {
        return classifyKey;
    }

    public void setClassifyKey(int classifyKey) {
        this.classifyKey = classifyKey;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "classify='" + classify + '\'' +
                ", classifyKey='" + classifyKey + '\'' +
                '}';
    }
}
