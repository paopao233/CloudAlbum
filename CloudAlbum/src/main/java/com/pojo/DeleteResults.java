package com.pojo;

public class DeleteResults {
    private String name;
    private String result;

    public DeleteResults() {
    }

    public DeleteResults(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DeleteResults{" +
                "name='" + name + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
