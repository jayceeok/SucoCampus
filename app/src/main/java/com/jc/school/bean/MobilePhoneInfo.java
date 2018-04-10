package com.jc.school.bean;

/**
 * Created by Jaycee on 16/7/3.
 * E-mail：jayceeok@foxmail.com
 */
public class MobilePhoneInfo {


    /**
     * resultcode : 200
     * reason : Return Successd!
     * result : {"province":"江苏","city":"扬州","areacode":"0514","zip":"225000","company":"中国移动","card":"移动全球通卡"}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    /**
     * province : 江苏
     * city : 扬州
     * areacode : 0514
     * zip : 225000
     * company : 中国移动
     * card : 移动全球通卡
     */

    private ResultEntity result;
    private int error_code;

    public String getResultcode() { return resultcode;}

    public void setResultcode(String resultcode) { this.resultcode = resultcode;}

    public String getReason() { return reason;}

    public void setReason(String reason) { this.reason = reason;}

    public ResultEntity getResult() { return result;}

    public void setResult(ResultEntity result) { this.result = result;}

    public int getError_code() { return error_code;}

    public void setError_code(int error_code) { this.error_code = error_code;}

    public static class ResultEntity {
        private String province;
        private String city;
        private String areacode;
        private String zip;
        private String company;
        private String card;

        public String getProvince() { return province;}

        public void setProvince(String province) { this.province = province;}

        public String getCity() { return city;}

        public void setCity(String city) { this.city = city;}

        public String getAreacode() { return areacode;}

        public void setAreacode(String areacode) { this.areacode = areacode;}

        public String getZip() { return zip;}

        public void setZip(String zip) { this.zip = zip;}

        public String getCompany() { return company;}

        public void setCompany(String company) { this.company = company;}

        public String getCard() { return card;}

        public void setCard(String card) { this.card = card;}
    }
}
