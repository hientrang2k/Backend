package com.globits.Backend.dto;

public class ChartDto {

    private Integer month;
    private Integer totalDengueLocationItem;//vector chuyền bệnh
    private Integer totalLocationItem ;
    private Integer totalPatient;
    private Integer totalUserFeetback;

    public ChartDto(Object[] entity){
        this.month = entity[0] != null? Integer.parseInt(entity[0].toString()):null;
        this.totalDengueLocationItem = entity[1] != null? Integer.parseInt(entity[1].toString()):null;
        this.totalLocationItem = entity[2] != null? Integer.parseInt(entity[2].toString()):null;
        this.totalPatient = entity[3] != null? Integer.parseInt(entity[3].toString()):null;
        this.totalUserFeetback = entity[4] != null? Integer.parseInt(entity[4].toString()):null;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTotalDengueLocationItem() {
        return totalDengueLocationItem;
    }

    public void setTotalDengueLocationItem(Integer totalDengueLocationItem) {
        this.totalDengueLocationItem = totalDengueLocationItem;
    }

    public Integer getTotalLocationItem() {
        return totalLocationItem;
    }

    public void setTotalLocationItem(Integer totalLocationItem) {
        this.totalLocationItem = totalLocationItem;
    }

    public Integer getTotalPatient() {
        return totalPatient;
    }

    public void setTotalPatient(Integer totalPatient) {
        this.totalPatient = totalPatient;
    }

    public Integer getTotalUserFeetback() {
        return totalUserFeetback;
    }

    public void setTotalUserFeetback(Integer totalUserFeetback) {
        this.totalUserFeetback = totalUserFeetback;
    }
}
