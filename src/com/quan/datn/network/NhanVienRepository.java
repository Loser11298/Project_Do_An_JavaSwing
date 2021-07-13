/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.network;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import static com.quan.datn.common.Constants.BASE_URL;
import com.quan.datn.model.CommonResponse;
import com.quan.datn.model.NhanVien;
import com.quan.datn.model.response.BacSiResponse;
import com.quan.datn.model.request.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NhanVienRepository {

    private final Gson gson;

    public NhanVienRepository() {
        gson = new Gson();
    }

    public NhanVien login(String phongNumber, String password) {
        try {
            String responString = APIManager.post(BASE_URL + "/api/nhanvien/login", new LoginRequest(phongNumber, password));
            CommonResponse<NhanVien> response = gson.fromJson(responString, new TypeToken<CommonResponse<NhanVien>>() {
            }.getType());
            if (response != null && response.getStatus() == 200) {
                return response.getData();
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean updatePassword(String phongNumber, String newPassword) {
        try {
            String responString = APIManager.post(BASE_URL + "/api/nhanvien/updatePass", new UpdatePasswordRequest(phongNumber, newPassword));
            CommonResponse response = gson.fromJson(responString, CommonResponse.class);
            return response != null && response.getStatus() == 200;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public NhanVien addNhanVien(NhanVien request, File avatarFile) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            HttpPostMultipart multipart = new HttpPostMultipart(BASE_URL + "/api/nhanvien/add", "utf-8", headers);
            multipart.addFormField("profile", gson.toJson(request));
            if (avatarFile != null) {
                multipart.addFilePart("avatar", avatarFile);
            }
            String responString = multipart.finish();
            CommonResponse<NhanVien> response = gson.fromJson(responString, new TypeToken<CommonResponse<NhanVien>>() {
            }.getType());
            if (response != null && response.getStatus() == 200) {
                return response.getData();
            }
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhanVien updateInfoNhanVien(NhanVien request, File avatarFile) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            HttpPostMultipart multipart = new HttpPostMultipart(BASE_URL + "/api/nhanvien/update", "utf-8", headers);
            multipart.addFormField("profile", gson.toJson(request));
            if (avatarFile != null) {
                multipart.addFilePart("avatar", avatarFile);
            }
            String responString = multipart.finish();
            CommonResponse<NhanVien> response = gson.fromJson(responString, new TypeToken<CommonResponse<NhanVien>>() {
            }.getType());
            if (response != null && response.getStatus() == 200) {
                return response.getData();
            }
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteNhanVien(String maNV) {
        String responString = APIManager.get(BASE_URL + "/api/nhanvien/delete?manv=" + maNV);
        CommonResponse response = gson.fromJson(responString, CommonResponse.class);
        return response != null && response.getStatus() == 200;
    }

    public List<NhanVien> getAllNhanVien() {
        String responString = APIManager.get(BASE_URL + "/api/nhanvien/all");
        CommonResponse<List<NhanVien>> response = gson.fromJson(responString, new TypeToken<CommonResponse<List<NhanVien>>>() {
        }.getType());
        if (response != null && response.getStatus() == 200) {
            return response.getData();
        }
        return new ArrayList<>();
    }

    public List<BacSiResponse> getAllBacSi() {
        String responString = APIManager.get(BASE_URL + "/api/nhanvien/all/bacsi");
        CommonResponse<List<BacSiResponse>> response = gson.fromJson(responString, new TypeToken<CommonResponse<List<BacSiResponse>>>() {
        }.getType());
        if (response != null && response.getStatus() == 200) {
            return response.getData();
        }
        return new ArrayList<>();
    }

}
