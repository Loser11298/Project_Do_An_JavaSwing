/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.model;

import java.util.List;


public class DonThuocModel {
    private String maBA;
    private List<DonThuoc> donthuocs;

    public String getMaBA() {
        return maBA;
    }

    public void setMaBA(String maBA) {
        this.maBA = maBA;
    }

    public List<DonThuoc> getDonthuocs() {
        return donthuocs;
    }

    public void setDonthuocs(List<DonThuoc> donthuocs) {
        this.donthuocs = donthuocs;
    }
    
}
