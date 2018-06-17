package com.jc.school.bean;

/**
 * @author jiangchao
 * @Module.Name
 * @Create.Date 2018/6/17 下午11:28
 * @Update.By jiangchao
 * @Update.Content
 * @Update.Date 2018/6/17 下午11:28
 * @see
 */


public class FocusNews {
    //标题
    private String title;
    //点击量
    private String ClicksCount;
    //日期
    private String date;
    //内容
    private String content;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClicksCount() {
        return ClicksCount;
    }

    public void setClicksCount(String clicksCount) {
        ClicksCount = clicksCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
