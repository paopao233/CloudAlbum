package com.pojo;

/**
 * @author x1yyy
 */
public class UploadResults {
    private int status;
    private String key;
    private String value;
    private String classify;

    public UploadResults() {
    }

    public UploadResults(int status, String key, String value) {
        this.status = status;
        this.key = key;
        this.value = value;
    }

    public UploadResults(int status, String key, String value, String classify) {
        this.status = status;
        this.key = key;
        this.value = value;
        this.classify = classify;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "UploadResults{" +
                "status=" + status +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", classify='" + classify + '\'' +
                '}';
    }
}
