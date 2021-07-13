/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.model.request;


public class AddPhongKhamRequest {
     private String maPK;
    private String tenPK;
    private String maBS;

    public String getMaPK() {
        return maPK;
    }

    public void setMaPK(String maPK) {
        this.maPK = maPK;
    }

    public String getTenPK() {
        return tenPK;
    }

    public void setTenPK(String tenPK) {
        this.tenPK = tenPK;
    }

    public String getMaBS() {
        return maBS;
    }

    public void setMaBS(String maBS) {
        this.maBS = maBS;
    }

    public AddPhongKhamRequest(String maPK, String tenPK, String maBS) {
        this.maPK = maPK;
        this.tenPK = tenPK;
        this.maBS = maBS;
    }
    
    

}
