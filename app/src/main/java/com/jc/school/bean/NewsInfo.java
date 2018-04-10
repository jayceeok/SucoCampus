package com.jc.school.bean;

/**
 * Created by Jaycee on 16/6/25.
 * E-mail：jayceeok@foxmail.com
 */
public class NewsInfo {


    /**
     * ID : 2473
     * TypeID : 3
     * Title : 扬州大学：学科建设实现“十三五”开门红
     * PostDate : 2016-6-24 9:46:36
     */

    private int ID;
    private int TypeID;
    private String Title;
    private String PostDate;

    public NewsInfo(String postDate, String title) {
        PostDate = postDate;
        Title = title;
    }

    public int getID() { return ID;}

    public void setID(int ID) { this.ID = ID;}

    public int getTypeID() { return TypeID;}

    public void setTypeID(int TypeID) { this.TypeID = TypeID;}

    public String getTitle() { return Title;}

    public void setTitle(String Title) { this.Title = Title;}

    public String getPostDate() { return PostDate;}

    public void setPostDate(String PostDate) { this.PostDate = PostDate;}
}
