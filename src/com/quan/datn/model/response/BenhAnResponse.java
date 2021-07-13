/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.model.response;

import com.quan.datn.model.DonThuoc;
import java.util.List;

public class BenhAnResponse {
    private int id;
    private String maBA;
    private String thoiGianKham;
    private float canNang;
    private String nhomMau;
    private float nhietDo;
    private int mach;
    private int huyetAp;
    private int nhipTho;
    private String lyDoKham;
    private String tinhTrangHT;
    private String benhSu;
    private String chanDoanSoBo;
    private String yeuCauThem;
    private String chanDoanSauCung;
    private String huongDieuTri;
    private PhongKhamResponse phongKham;
    private PhongBenhResponse phongBenh;
    private List<DonThuoc> donThuoc;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaBA() {
        return maBA;
    }

    public void setMaBA(String maBA) {
        this.maBA = maBA;
    }

    public String getThoiGianKham() {
        return thoiGianKham;
    }

    public void setThoiGianKham(String thoiGianKham) {
        this.thoiGianKham = thoiGianKham;
    }

    public float getCanNang() {
        return canNang;
    }

    public void setCanNang(float canNang) {
        this.canNang = canNang;
    }

    public String getNhomMau() {
        return nhomMau;
    }

    public void setNhomMau(String nhomMau) {
        this.nhomMau = nhomMau;
    }

    public float getNhietDo() {
        return nhietDo;
    }

    public void setNhietDo(float nhietDo) {
        this.nhietDo = nhietDo;
    }

    public int getMach() {
        return mach;
    }

    public void setMach(int mach) {
        this.mach = mach;
    }

    public int getHuyetAp() {
        return huyetAp;
    }

    public void setHuyetAp(int huyetAp) {
        this.huyetAp = huyetAp;
    }

    public int getNhipTho() {
        return nhipTho;
    }

    public void setNhipTho(int nhipTho) {
        this.nhipTho = nhipTho;
    }

    public String getLyDoKham() {
        return lyDoKham;
    }

    public void setLyDoKham(String lyDoKham) {
        this.lyDoKham = lyDoKham;
    }

    public String getTinhTrangHT() {
        return tinhTrangHT;
    }

    public void setTinhTrangHT(String tinhTrangHT) {
        this.tinhTrangHT = tinhTrangHT;
    }

    public String getBenhSu() {
        return benhSu;
    }

    public void setBenhSu(String benhSu) {
        this.benhSu = benhSu;
    }

    public String getChanDoanSoBo() {
        return chanDoanSoBo;
    }

    public void setChanDoanSoBo(String chanDoanSoBo) {
        this.chanDoanSoBo = chanDoanSoBo;
    }

    public String getYeuCauThem() {
        return yeuCauThem;
    }

    public void setYeuCauThem(String yeuCauThem) {
        this.yeuCauThem = yeuCauThem;
    }

    public String getChanDoanSauCung() {
        return chanDoanSauCung;
    }

    public void setChanDoanSauCung(String chanDoanSauCung) {
        this.chanDoanSauCung = chanDoanSauCung;
    }

    public String getHuongDieuTri() {
        return huongDieuTri;
    }

    public void setHuongDieuTri(String huongDieuTri) {
        this.huongDieuTri = huongDieuTri;
    }

    public PhongKhamResponse getPhongKham() {
        return phongKham;
    }

    public void setPhongKham(PhongKhamResponse phongKham) {
        this.phongKham = phongKham;
    }

    public PhongBenhResponse getPhongBenh() {
        return phongBenh;
    }

    public void setPhongBenh(PhongBenhResponse phongBenh) {
        this.phongBenh = phongBenh;
    }

    public List<DonThuoc> getDonThuoc() {
        return donThuoc;
    }

    public void setDonThuoc(List<DonThuoc> donThuoc) {
        this.donThuoc = donThuoc;
    }
    
}
