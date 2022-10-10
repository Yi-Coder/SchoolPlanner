package com.example.TravelPlanner.Model;

import java.util.List;

public class Subject {

    private String name;
    private String teacher;
    private Integer imgId;
    private List<String> tools;

    public Subject(){};


    public Subject(String name, String teacher, Integer imgId, List<String> tools) {
        this.name = name;
        this.teacher = teacher;
        this.imgId = imgId;
        this.tools = tools;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTools() {
        return tools;
    }

    public void setTools(List<String> tools) {
        this.tools = tools;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }
}
