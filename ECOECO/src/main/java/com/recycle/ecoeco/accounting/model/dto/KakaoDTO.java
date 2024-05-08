package com.recycle.ecoeco.accounting.model.dto;

public class KakaoDTO {

    private String name;
    private int amount;
//    private String buyer_email;
    private String buyer_name;
    private String buyer_tel;
    private String buyer_addr;
    private String buyer_postcode;

    private int projectNo;
    private int orderNo;
    private int rewardNo;
    private int userNo;

    public KakaoDTO(String name, int amount, String buyer_email, String buyer_name, String buyer_tel, String buyer_addr, String buyer_postcode, int projectNo, int orderNo, int rewardNo, int userNo) {
    }

    public KakaoDTO(String name, int amount, String buyer_name, String buyer_tel, String buyer_addr, String buyer_postcode, int projectNo, int orderNo, int rewardNo, int userNo) {
        this.name = name;
        this.amount = amount;
        this.buyer_name = buyer_name;
        this.buyer_tel = buyer_tel;
        this.buyer_addr = buyer_addr;
        this.buyer_postcode = buyer_postcode;
        this.projectNo = projectNo;
        this.orderNo = orderNo;
        this.rewardNo = rewardNo;
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getBuyer_tel() {
        return buyer_tel;
    }

    public void setBuyer_tel(String buyer_tel) {
        this.buyer_tel = buyer_tel;
    }

    public String getBuyer_addr() {
        return buyer_addr;
    }

    public void setBuyer_addr(String buyer_addr) {
        this.buyer_addr = buyer_addr;
    }

    public String getBuyer_postcode() {
        return buyer_postcode;
    }

    public void setBuyer_postcode(String buyer_postcode) {
        this.buyer_postcode = buyer_postcode;
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getRewardNo() {
        return rewardNo;
    }

    public void setRewardNo(int rewardNo) {
        this.rewardNo = rewardNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "KakaoDTO{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", buyer_name='" + buyer_name + '\'' +
                ", buyer_tel='" + buyer_tel + '\'' +
                ", buyer_addr='" + buyer_addr + '\'' +
                ", buyer_postcode='" + buyer_postcode + '\'' +
                ", projectNo=" + projectNo +
                ", orderNo=" + orderNo +
                ", rewardNo=" + rewardNo +
                ", userNo=" + userNo +
                '}';
    }
}
