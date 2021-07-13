/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.utils;

import com.quan.datn.model.NhanVien;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import javax.swing.JOptionPane;

public class DBManager {

    public static DBManager sharedInstance = new DBManager();

    public DBManager() {

    }

    public NhanVien getSessionLogin() {
        NhanVien nhanVien = new NhanVien();
        try {
            File selectedFile = new File("login.data");
            BufferedReader buffreader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), "UTF-8"));
            String strline;
            while ((strline = buffreader.readLine()) != null) {
                String temp[] = strline.split("\\|");
                nhanVien.setId(Integer.parseInt(temp[0]));
                nhanVien.setMaNV(temp[1]);
                nhanVien.setHo(temp[2]);
                nhanVien.setTen(temp[3]);
                nhanVien.setAvatar(temp[4]);
                nhanVien.setGioiTinh(temp[5]);
                nhanVien.setNgaySinh(temp[6]);
                nhanVien.setChucVu(temp[7]);
                nhanVien.setCmnd(temp[8]);
                nhanVien.setHocHam(temp[9]);
                nhanVien.setHocVi(temp[10]);
                nhanVien.setTrinhDo(temp[11]);
                nhanVien.setDiaChi(temp[12]);
                nhanVien.setViTri(temp[13]);
                nhanVien.setEmail(temp[14]);
                nhanVien.setSdt(temp[15]);
                nhanVien.setNgayVaoLam(temp[16]);
                nhanVien.setAccessToken(temp[17]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi đọc ghi file");
        }
        return nhanVien;
    }

    public void cleanSessionLogin() {
        try {
            File files = new File("login.data");
            files.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean saveSessionLogin(NhanVien nhanVien) {
        try {
            File files = new File("login.data");
            if (!files.exists()) {
                files.createNewFile();
            }
            CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
            encoder.onMalformedInput(CodingErrorAction.IGNORE);
            encoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
            BufferedWriter file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(files), encoder));
            file.write(nhanVien.getId()+ "|"
                    + nhanVien.getMaNV() + "|"
                    + nhanVien.getHo() + "|"
                    + nhanVien.getTen() + "|"
                    + nhanVien.getAvatar() + "|"
                    + nhanVien.getGioiTinh() + "|"
                    + nhanVien.getNgaySinh() + "|"
                    + nhanVien.getChucVu() + "|"
                    + nhanVien.getCmnd() + "|"
                    + nhanVien.getHocHam() + "|"
                    + nhanVien.getHocVi() + "|"
                    + nhanVien.getTrinhDo() + "|"
                    + nhanVien.getDiaChi() + "|"
                    + nhanVien.getViTri() + "|"
                    + nhanVien.getEmail() + "|"
                    + nhanVien.getSdt() + "|"
                    + nhanVien.getNgayVaoLam() + "|"
                    + nhanVien.getAccessToken());
            file.write("\n");
            file.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public BufferedReader getDataInFile(String nameFile) {
        try {
            File file = new File("/database/" + nameFile);
            return new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc file");
        }
        return null;
    }

}
