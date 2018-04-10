package com.jc.school.bean;

import java.util.List;

/**
 * Created by Jaycee on 16/7/21.
 * E-mail：jayceeok@foxmail.com
 */
public class TestModel {


    /**
     * total : 15
     * rows : [{"productid":20,"productname":"测试0622","productcode":"20160622","startdate":1466564396000,"enddate":1466564402000,"rate":5,"amount":100000,"startamount":100,"limitamount":10000,"remainamount":96800,"sumdate":365,"saledate":1467216000000,"state":"6","agreementid":100,"createdate":1466564419000,"updatedate":1468424700000,"residualrate":96,"waitforpay":0,"residualquantity":96800,"orderBy":null,"states":null},{"productid":18,"productname":"测试0616","productcode":"123456789","startdate":1466491511000,"enddate":1498027529000,"rate":8,"amount":1000000,"startamount":1000,"limitamount":10000,"remainamount":981000,"sumdate":365,"saledate":1466524800000,"state":"6","agreementid":100,"createdate":1466059910000,"updatedate":1468487794000,"residualrate":98,"waitforpay":0,"residualquantity":981000,"orderBy":null,"states":null},{"productid":4,"productname":"汉爵8号（周年庆）","productcode":"2016060901015","startdate":1465401601000,"enddate":1496894280000,"rate":15,"amount":5000000,"startamount":100,"limitamount":10000,"remainamount":4879400,"sumdate":60,"saledate":1465315200000,"state":"6","agreementid":100,"createdate":1465027290000,"updatedate":1468458877000,"residualrate":97,"waitforpay":0,"residualquantity":4879400,"orderBy":null,"states":null},{"productid":16,"productname":"汉爵888号","productcode":"2016060601050","startdate":1465144079000,"enddate":1496651285000,"rate":50,"amount":1000000,"startamount":1000,"limitamount":1000000,"remainamount":0,"sumdate":10,"saledate":1465142400000,"state":"7","agreementid":100,"createdate":1465201742000,"updatedate":1465201801000,"residualrate":0,"waitforpay":0,"residualquantity":0,"orderBy":null,"states":null},{"productid":7,"productname":"汉爵200号","productcode":"2016060501080","startdate":1465099615000,"enddate":1496520422000,"rate":8,"amount":1.0E7,"startamount":100,"limitamount":100000,"remainamount":8508700,"sumdate":100,"saledate":1464969600000,"state":"6","agreementid":100,"createdate":1465027675000,"updatedate":1467362700000,"residualrate":85,"waitforpay":0,"residualquantity":8508700,"orderBy":null,"states":null},{"productid":6,"productname":"汉爵15号","productcode":"2016060403038","startdate":1465013048000,"enddate":1496459073000,"rate":3.8,"amount":1.0E7,"startamount":100,"limitamount":10000,"remainamount":9960800,"sumdate":600,"saledate":1464883200000,"state":"6","agreementid":100,"createdate":1465027565000,"updatedate":1467361800000,"residualrate":99,"waitforpay":0,"residualquantity":9960800,"orderBy":null,"states":null},{"productid":15,"productname":"汉爵99号","productcode":"2016060301018","startdate":1464883222000,"enddate":1496393190000,"rate":18,"amount":1.0E7,"startamount":100,"limitamount":10000,"remainamount":9619700,"sumdate":100,"saledate":1464796800000,"state":"6","agreementid":100,"createdate":1465030031000,"updatedate":1467360900000,"residualrate":96,"waitforpay":0,"residualquantity":9619700,"orderBy":null,"states":null},{"productid":11,"productname":"汉爵36号","productcode":"2016060201065","startdate":1464798202000,"enddate":1496305407000,"rate":6.5,"amount":1.0E7,"startamount":100,"limitamount":10000,"remainamount":9999600,"sumdate":200,"saledate":1464710400000,"state":"6","agreementid":100,"createdate":1465028657000,"updatedate":1467362700000,"residualrate":99,"waitforpay":0,"residualquantity":9999600,"orderBy":null,"states":null},{"productid":8,"productname":"汉爵66号","productcode":"2016060105060","startdate":1464754205000,"enddate":1496175010000,"rate":6,"amount":1.0E7,"startamount":100,"limitamount":10000,"remainamount":9990000,"sumdate":1000,"saledate":1464624000000,"state":"6","agreementid":100,"createdate":1465027857000,"updatedate":1465028446000,"residualrate":99,"waitforpay":0,"residualquantity":9990000,"orderBy":null,"states":null},{"productid":5,"productname":"汉爵9号","productcode":"2016052602043","startdate":1464235329000,"enddate":1495656142000,"rate":4.3,"amount":1.0E7,"startamount":100,"limitamount":10000,"remainamount":9979900,"sumdate":200,"saledate":1464105600000,"state":"6","agreementid":100,"createdate":1465027416000,"updatedate":1465028433000,"residualrate":99,"waitforpay":0,"residualquantity":9979900,"orderBy":null,"states":null}]
     */

    private int total;
    /**
     * productid : 20
     * productname : 测试0622
     * productcode : 20160622
     * startdate : 1466564396000
     * enddate : 1466564402000
     * rate : 5.0
     * amount : 100000.0
     * startamount : 100.0
     * limitamount : 10000.0
     * remainamount : 96800.0
     * sumdate : 365
     * saledate : 1467216000000
     * state : 6
     * agreementid : 100
     * createdate : 1466564419000
     * updatedate : 1468424700000
     * residualrate : 96
     * waitforpay : 0.0
     * residualquantity : 96800.0
     * orderBy : null
     * states : null
     */

    private List<RowsEntity> rows;

    public int getTotal() { return total;}

    public void setTotal(int total) { this.total = total;}

    public List<RowsEntity> getRows() { return rows;}

    public void setRows(List<RowsEntity> rows) { this.rows = rows;}

    public static class RowsEntity {
        private int productid;
        private String productname;
        private String productcode;
        private long startdate;
        private long enddate;
        private double rate;
        private double amount;
        private double startamount;
        private double limitamount;
        private double remainamount;
        private int sumdate;
        private long saledate;
        private String state;
        private int agreementid;
        private long createdate;
        private long updatedate;
        private int residualrate;
        private double waitforpay;
        private double residualquantity;
        private Object orderBy;
        private Object states;

        public RowsEntity(String productname, int productid) {
            this.productname = productname;
            this.productid = productid;
        }

        public int getProductid() { return productid;}

        public void setProductid(int productid) { this.productid = productid;}

        public String getProductname() { return productname;}

        public void setProductname(String productname) { this.productname = productname;}

        public String getProductcode() { return productcode;}

        public void setProductcode(String productcode) { this.productcode = productcode;}

        public long getStartdate() { return startdate;}

        public void setStartdate(long startdate) { this.startdate = startdate;}

        public long getEnddate() { return enddate;}

        public void setEnddate(long enddate) { this.enddate = enddate;}

        public double getRate() { return rate;}

        public void setRate(double rate) { this.rate = rate;}

        public double getAmount() { return amount;}

        public void setAmount(double amount) { this.amount = amount;}

        public double getStartamount() { return startamount;}

        public void setStartamount(double startamount) { this.startamount = startamount;}

        public double getLimitamount() { return limitamount;}

        public void setLimitamount(double limitamount) { this.limitamount = limitamount;}

        public double getRemainamount() { return remainamount;}

        public void setRemainamount(double remainamount) { this.remainamount = remainamount;}

        public int getSumdate() { return sumdate;}

        public void setSumdate(int sumdate) { this.sumdate = sumdate;}

        public long getSaledate() { return saledate;}

        public void setSaledate(long saledate) { this.saledate = saledate;}

        public String getState() { return state;}

        public void setState(String state) { this.state = state;}

        public int getAgreementid() { return agreementid;}

        public void setAgreementid(int agreementid) { this.agreementid = agreementid;}

        public long getCreatedate() { return createdate;}

        public void setCreatedate(long createdate) { this.createdate = createdate;}

        public long getUpdatedate() { return updatedate;}

        public void setUpdatedate(long updatedate) { this.updatedate = updatedate;}

        public int getResidualrate() { return residualrate;}

        public void setResidualrate(int residualrate) { this.residualrate = residualrate;}

        public double getWaitforpay() { return waitforpay;}

        public void setWaitforpay(double waitforpay) { this.waitforpay = waitforpay;}

        public double getResidualquantity() { return residualquantity;}

        public void setResidualquantity(
                double residualquantity) { this.residualquantity = residualquantity;}

        public Object getOrderBy() { return orderBy;}

        public void setOrderBy(Object orderBy) { this.orderBy = orderBy;}

        public Object getStates() { return states;}

        public void setStates(Object states) { this.states = states;}
    }
}
