/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.model.response;


public class PhongBenhResponse {
    private int id;
    private String maPB;
    private String tenPB;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public PhongBenhResponse(int id, String maPB, String tenPB) {
        this.id = id;
        this.maPB = maPB;
        this.tenPB = tenPB;
    }
    
}
