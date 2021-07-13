/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.model.request;

public class AddPhongBenhRequest {

    private String maPB;
    private String tenPB;

    public String getMaPB() {
        return maPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public AddPhongBenhRequest(String maPB, String tenPB) {
        this.maPB = maPB;
        this.tenPB = tenPB;
    }
    
    
}
