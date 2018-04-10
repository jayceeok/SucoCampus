package com.jc.school.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */
public class Feedback extends BmobObject{

    private String name;
    private String feedback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
