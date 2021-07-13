/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.model.request;

import com.quan.datn.model.BenhNhan;


public class AddBenhNhanRequest {
    private String maBN;
    private String hoBn;
    private String tenBn;
    private String gioiTinh;
    private String ngaySinh;
    private String cmnd;
    private String ngheNghiep;
    private String diaChi;
    private String sdt;
    private String publicKey;
    private BenhAnRequest benhAn;

    public AddBenhNhanRequest() {
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

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
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

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
    
    public BenhAnRequest getBenhAn() {
        return benhAn;
    }

    public void setBenhAn(BenhAnRequest benhAn) {
        this.benhAn = benhAn;
    }

    public AddBenhNhanRequest(String maBN, String hoBn, String tenBn, String gioiTinh, String ngaySinh, String cmnd, String ngheNghiep, String diaChi, String sdt, String publicKey, BenhAnRequest benhAn) {
        this.maBN = maBN;
        this.hoBn = hoBn;
        this.tenBn = tenBn;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.cmnd = cmnd;
        this.ngheNghiep = ngheNghiep;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.publicKey = publicKey;
        this.benhAn = benhAn;
    }
    
    public BenhNhan getBenhNhan(){
        BenhNhan benhNhan = new BenhNhan();
        benhNhan.setMaBN(this.maBN);
        benhNhan.setHoBn(this.hoBn);
        benhNhan.setTenBn(this.tenBn);
        benhNhan.setGioiTinh(this.gioiTinh);
        benhNhan.setNgaySinh(this.ngaySinh);
        benhNhan.setCmnd(this.cmnd);
        benhNhan.setNgheNghiep(this.ngheNghiep);
        benhNhan.setDiaChi(this.diaChi);
        benhNhan.setSdt(this.sdt);
        return benhNhan;
    }
    
}
