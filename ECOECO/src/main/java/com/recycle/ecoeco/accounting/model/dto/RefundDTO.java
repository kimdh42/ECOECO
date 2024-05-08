package com.recycle.ecoeco.accounting.model.dto;

import com.recycle.ecoeco.makerProject.model.dto.ProjectDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

public class RefundDTO {
    private int refundNo;
//    private int accountnum;
    private LocalDateTime refundRequestDate;
    private LocalDateTime refundDate;
    private String refundStatus;
    private int paymentNo;

    private PaymentDTO payment;
//    private ProjectDTO project;


    public RefundDTO() {
    }

    public RefundDTO(int refundNo, LocalDateTime refundRequestDate, LocalDateTime refundDate, String refundStatus, int paymentNo, PaymentDTO payment) {
        this.refundNo = refundNo;
        this.refundRequestDate = refundRequestDate;
        this.refundDate = refundDate;
        this.refundStatus = refundStatus;
        this.paymentNo = paymentNo;
        this.payment = payment;
    }

    public int getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(int refundNo) {
        this.refundNo = refundNo;
    }

    public LocalDateTime getRefundRequestDate() {
        return refundRequestDate;
    }

    public void setRefundRequestDate(LocalDateTime refundRequestDate) {
        this.refundRequestDate = refundRequestDate;
    }

    public LocalDateTime getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(LocalDateTime refundDate) {
        this.refundDate = refundDate;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public int getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(int paymentNo) {
        this.paymentNo = paymentNo;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "RefundDTO{" +
                "refundNo=" + refundNo +
                ", refundRequestDate=" + refundRequestDate +
                ", refundDate=" + refundDate +
                ", refundStatus='" + refundStatus + '\'' +
                ", paymentNo=" + paymentNo +
                ", payment=" + payment +
                '}';
    }
}
