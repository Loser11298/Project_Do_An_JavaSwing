/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.model.response;

import com.quan.datn.model.request.UpdateBenhNhanRequest;

public class BenhNhanResponse {

    private int id;
    private String maBN;
    private String hoBn;
    private String tenBn;
    private String avatar;
    private String gioiTinh;
    private String ngaySinh;
    private String cmnd;
    private String ngheNghiep;
    private String diaChi;
    private String sdt;
    private long amount;
    private long vienPhi;
    private String publicKey;
    private String trangThai;
    private String token;
    private BenhAnResponse benhAnResponse;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public long getVienPhi() {
        return vienPhi;
    }

    public void setVienPhi(long vienPhi) {
        this.vienPhi = vienPhi;
    }
    
    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BenhAnResponse getBenhAnResponse() {
        return benhAnResponse;
    }

    public void setBenhAnResponse(BenhAnResponse benhAnResponse) {
        this.benhAnResponse = benhAnResponse;
    }

    public String getFullName() {
        return this.getHoBn() + " " + this.getTenBn();
    }

    public UpdateBenhNhanRequest getUpdateRequest() {
        UpdateBenhNhanRequest request = new UpdateBenhNhanRequest();
        request.setId(this.getId());
        request.setMaBN(this.getMaBN());
        request.setHoBn(this.getHoBn());
        request.setTenBn(this.getTenBn());
        request.setGioiTinh(this.getGioiTinh());
        request.setNgaySinh(this.getNgaySinh());
        request.setCmnd(this.getCmnd());
        request.setNgheNghiep(this.getNgheNghiep());
        request.setDiaChi(this.getDiaChi());
        request.setSdt(this.getSdt());
        request.setAmount(this.getAmount());
        request.setTrangThai(this.getTrangThai());
        request.setPublicKey(this.getPublicKey());
        return request;
    }

}
