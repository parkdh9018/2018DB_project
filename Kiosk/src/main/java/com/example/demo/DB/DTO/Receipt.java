package com.example.demo.DB.DTO;


public class Receipt {

  private String receiptcode;
  private String storeid;
  private String paymentid;
  private String ordercode;
  private java.sql.Date issueddate;


  public String getReceiptcode() {
    return receiptcode;
  }

  public void setReceiptcode(String receiptcode) {
    this.receiptcode = receiptcode;
  }


  public String getStoreid() {
    return storeid;
  }

  public void setStoreid(String storeid) {
    this.storeid = storeid;
  }


  public String getPaymentid() {
    return paymentid;
  }

  public void setPaymentid(String paymentid) {
    this.paymentid = paymentid;
  }


  public String getOrdercode() {
    return ordercode;
  }

  public void setOrdercode(String ordercode) {
    this.ordercode = ordercode;
  }


  public java.sql.Date getIssueddate() {
    return issueddate;
  }

  public void setIssueddate(java.sql.Date issueddate) {
    this.issueddate = issueddate;
  }

}
