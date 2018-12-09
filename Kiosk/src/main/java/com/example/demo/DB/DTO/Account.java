package com.example.demo.DB.DTO;


public class Account {

  private String bank;
  private String accountnum;
  private String owner;
  private String paymentid;
  private String ordercode;


  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }


  public String getAccountnum() {
    return accountnum;
  }

  public void setAccountnum(String accountnum) {
    this.accountnum = accountnum;
  }


  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
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

}
