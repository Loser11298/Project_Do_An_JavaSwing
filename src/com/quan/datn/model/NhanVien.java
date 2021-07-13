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
public class NhanVien {

    private int id;
    private String maNV;
    private String ho;
    private String ten;
    private String avatar;
    private String gioiTinh;
    private String ngaySinh;
    private String chucVu;
    private String cmnd;
    private String hocHam;
    private String hocVi;
    private String trinhDo;
    private String diaChi;
    private String viTri;
    private String email;
    private String sdt;
    private String accessToken;
    private String ngayVaoLam;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getHocHam() {
        return hocHam;
    }

    public void setHocHam(String hocHam) {
        this.hocHam = hocHam;
    }

    public String getHocVi() {
        return hocVi;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(String ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getFullName() {
        return ho + " "+ten;
    }
    
    public NhanVien() {
    }

    public NhanVien(String maNV, String ho, String ten) {
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
    }

    public void setNhanVien(NhanVien nv) {
        this.ho = nv.ho;
        this.ten = nv.ten;
        this.avatar = nv.avatar;
        this.gioiTinh = nv.gioiTinh;
        this.ngaySinh = nv.ngaySinh;
        this.chucVu = nv.chucVu;
        this.cmnd = nv.cmnd;
        this.hocHam = nv.hocHam;
        this.hocVi = nv.hocVi;
        this.trinhDo = nv.trinhDo;
        this.diaChi = nv.diaChi;
        this.viTri = nv.viTri;
        this.email = nv.email;
        this.sdt = nv.sdt;
        this.ngayVaoLam = nv.ngayVaoLam;
    }

}
