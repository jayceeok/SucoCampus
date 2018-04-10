package com.jc.school.bean;

import java.util.List;

/**
 * Created by Jaycee on 16/7/5.
 * E-mail：jayceeok@foxmail.com
 */
public class LocalNews {


    private int errorCode;
    private String errorMessage;
    private String str;

    private List<ListEntity> list;
    private List<?> list2;

    public int getErrorCode() { return errorCode;}

    public void setErrorCode(int errorCode) { this.errorCode = errorCode;}

    public String getErrorMessage() { return errorMessage;}

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage;}

    public String getStr() { return str;}

    public void setStr(String str) { this.str = str;}

    public List<ListEntity> getList() { return list;}

    public void setList(List<ListEntity> list) { this.list = list;}

    public List<?> getList2() { return list2;}

    public void setList2(List<?> list2) { this.list2 = list2;}

    public static class ListEntity {
        private String newsId;
        private String title;
        private String content;

        public ListEntity(String title, String content, String date, ImageEntity image,
                          String newsId) {
            this.content = content;
            this.date = date;
            this.image = image;
            this.newsId = newsId;
            this.title = title;
        }

        private ImageEntity image;
        private String videoAndriodURL;
        private String videoIphoneURL;
        private String videoIpadURL;
        private String date;

        public String getNewsId() { return newsId;}

        public void setNewsId(String newsId) { this.newsId = newsId;}

        public String getTitle() { return title;}

        public void setTitle(String title) { this.title = title;}

        public String getContent() { return content;}

        public void setContent(String content) { this.content = content;}

        public ImageEntity getImage() { return image;}

        public void setImage(ImageEntity image) { this.image = image;}

        public String getVideoAndriodURL() { return videoAndriodURL;}

        public void setVideoAndriodURL(
                String videoAndriodURL) { this.videoAndriodURL = videoAndriodURL;}

        public String getVideoIphoneURL() { return videoIphoneURL;}

        public void setVideoIphoneURL(
                String videoIphoneURL) { this.videoIphoneURL = videoIphoneURL;}

        public String getVideoIpadURL() { return videoIpadURL;}

        public void setVideoIpadURL(String videoIpadURL) { this.videoIpadURL = videoIpadURL;}

        public String getDate() { return date;}

        public void setDate(String date) { this.date = date;}

        public static class ImageEntity {
            private String imageId;
            private String itemId;
            private String src;
            private String alt;
            private String caption;
            private int imageTypeId;
            private int width;
            private int height;
            private String linkUrl;
            private String fileName;
            private String modifiedDate;
            private String createdDate;

            public String getImageId() { return imageId;}

            public void setImageId(String imageId) { this.imageId = imageId;}

            public String getItemId() { return itemId;}

            public void setItemId(String itemId) { this.itemId = itemId;}

            public String getSrc() { return src;}

            public void setSrc(String src) { this.src = src;}

            public String getAlt() { return alt;}

            public void setAlt(String alt) { this.alt = alt;}

            public String getCaption() { return caption;}

            public void setCaption(String caption) { this.caption = caption;}

            public int getImageTypeId() { return imageTypeId;}

            public void setImageTypeId(int imageTypeId) { this.imageTypeId = imageTypeId;}

            public int getWidth() { return width;}

            public void setWidth(int width) { this.width = width;}

            public int getHeight() { return height;}

            public void setHeight(int height) { this.height = height;}

            public String getLinkUrl() { return linkUrl;}

            public void setLinkUrl(String linkUrl) { this.linkUrl = linkUrl;}

            public String getFileName() { return fileName;}

            public void setFileName(String fileName) { this.fileName = fileName;}

            public String getModifiedDate() { return modifiedDate;}

            public void setModifiedDate(String modifiedDate) { this.modifiedDate = modifiedDate;}

            public String getCreatedDate() { return createdDate;}

            public void setCreatedDate(String createdDate) { this.createdDate = createdDate;}
        }
    }


}
