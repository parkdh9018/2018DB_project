package com.example.demo.DB.DTO;


public class DistributorSupplier {

  private String phone;
  private String address;
  private String manager;
  private String companyname;
  private String origin;
  private java.sql.Date supplieddate;


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }


  public String getCompanyname() {
    return companyname;
  }

  public void setCompanyname(String companyname) {
    this.companyname = companyname;
  }


  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }


  public java.sql.Date getSupplieddate() {
    return supplieddate;
  }

  public void setSupplieddate(java.sql.Date supplieddate) {
    this.supplieddate = supplieddate;
  }

}
