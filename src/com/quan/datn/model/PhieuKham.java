/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.model;

/**
 *
 * @author NguyenHongQuan
 */
public class PhieuKham {
    private int id;
    private String maPK;
    private String ngayKham;
    private String gioKham;
    private String ycKham;
    private String maBS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getMaPK() {
        return maPK;
    }

    public void setMaPK(String maPK) {
        this.maPK = maPK;
    }
    
    public String getYcKham() {
        return ycKham;
    }

    public void setYcKham(String ycKham) {
        this.ycKham = ycKham;
    }

    public String getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getGioKham() {
        return gioKham;
    }

    public void setGioKham(String gioKham) {
        this.gioKham = gioKham;
    }

    public String getMaBS() {
        return maBS;
    }

    public void setMaBS(String maBS) {
        this.maBS = maBS;
    }
    
    public PhieuKham() {
    }

    public PhieuKham(String maPK, String ngayKhamString, String gioKhamString, String ycKham) {
        this.maPK = maPK;
        this.ngayKham = ngayKhamString;
        this.gioKham = gioKhamString;
        this.ycKham = ycKham;
    }

    public PhieuKham(String maPK, String ngayKhamString, String gioKhamString, String ycKham, String maBS) {
        this.maPK = maPK;
        this.ngayKham = ngayKhamString;
        this.gioKham = gioKhamString;
        this.ycKham = ycKham;
        this.maBS = maBS;
    }

    public PhieuKham(int id, String maPK, String ngayKham, String gioKham, String ycKham, String maBS) {
        this.id = id;
        this.maPK = maPK;
        this.ngayKham = ngayKham;
        this.gioKham = gioKham;
        this.ycKham = ycKham;
        this.maBS = maBS;
    }
    
}
