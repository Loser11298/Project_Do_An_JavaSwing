/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.model;

import com.quan.datn.model.request.BenhAnRequest;

/**
 *
 * @author NguyenHongQuan
 */
public class BenhNhan {
    private int id;
    private String maBN;
    private String hoBn;
    private String tenBn;
    private String gioiTinh;
    private String ngaySinh;
    private String cmnd;
    private String ngheNghiep;
    private String diaChi;
    private String sdt;
    private long amount;
    private String trangThai;
    private BenhAnRequest benhAn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getMaBN() {
        return maBN;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getHoBn() {
        return hoBn;
    }

    public void setHoBn(String hoBn) {
        this.hoBn = hoBn;
    }

    public String getTenBn() {
        return tenBn;
    }

    public void setTenBn(String tenBn) {
        this.tenBn = tenBn;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
    
    public BenhAnRequest getBenhAn() {
        return benhAn;
    }

    public void setBenhAn(BenhAnRequest benhAn) {
        this.benhAn = benhAn;
    }
    
    public BenhNhan() {
        
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public BenhNhan(String maBN, String ho, String ten, String cmnd, String ngaySinh, String diaChi, String sdt, String ngheNghiep, String gioiTinh, long amount) {
        this.maBN = maBN;
        this.hoBn = ho;
        this.tenBn = ten;
        this.cmnd = cmnd;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.ngheNghiep = ngheNghiep;
        this.gioiTinh = gioiTinh;
        this.amount = amount;
    }

    public BenhNhan(String maBN, String ho, String ten, String cmnd, String ngaySinh, String diaChi, String sdt, String ngheNghiep, String gioiTinh,long amount, BenhAnRequest benhAn) {
        this.maBN = maBN;
        this.hoBn = ho;
        this.tenBn = ten;
        this.cmnd = cmnd;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.ngheNghiep = ngheNghiep;
        this.gioiTinh = gioiTinh;
        this.amount = amount;
        this.benhAn = benhAn;
    }
    
}
