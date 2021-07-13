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
import com.quan.datn.model.BenhNhan;
import com.quan.datn.model.CommonResponse;
import com.quan.datn.model.request.AddBenhNhanRequest;
import com.quan.datn.model.request.UpdateBenhNhanRequest;
import com.quan.datn.model.response.BenhNhanResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenhNhanRepository {

    private final Gson gson;

    public BenhNhanRepository() {
        gson = new Gson();
    }
    
    public BenhNhanResponse getBenhNhanInfo(String maBN) {
        String responString = APIManager.get(BASE_URL + "/api/benhnhan/get-info-by-mabn?mabn=" + maBN);
        System.out.println("Response"+ responString);
        CommonResponse<BenhNhanResponse> response = gson.fromJson(responString, new TypeToken<CommonResponse<BenhNhanResponse>>() {
        }.getType());
        if (response != null && response.getStatus() == 200) {
            return response.getData();
        }
        return null;
    }
    
    public boolean xuatVien(String maBN) {
        String responString = APIManager.get(BASE_URL + "/api/benhnhan/xuatvien?mabn=" + maBN);
        CommonResponse response = gson.fromJson(responString, CommonResponse.class);
        return response != null && response.getStatus() == 200;
    }

    public boolean nhapVien(String maBN) {
        String responString = APIManager.get(BASE_URL + "/api/benhnhan/nhapvien?mabn=" + maBN);
        CommonResponse response = gson.fromJson(responString, CommonResponse.class);
        return response != null && response.getStatus() == 200;
    }

    public boolean addBenhNhan(AddBenhNhanRequest benhNhan, File avatarFile) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            HttpPostMultipart multipart = new HttpPostMultipart(BASE_URL + "/api/benhnhan/add", "utf-8", headers);
            multipart.addFormField("profile", gson.toJson(benhNhan));
            if (avatarFile != null) {
                multipart.addFilePart("avatar", avatarFile);
            }
            CommonResponse response = gson.fromJson(multipart.finish(), CommonResponse.class);
            if (response != null && response.getStatus() == 200) {
                return true;
            }
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public BenhNhan updateBenhNhan(UpdateBenhNhanRequest request, File avatarFile) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            HttpPostMultipart multipart = new HttpPostMultipart(BASE_URL + "/api/benhnhan/update", "utf-8", headers);
            multipart.addFormField("profile", gson.toJson(request));
            if (avatarFile != null) {
                multipart.addFilePart("avatar", avatarFile);
            }
            String responString = multipart.finish();
            System.out.println(responString);
            CommonResponse<BenhNhan> response = gson.fromJson(responString, new TypeToken<CommonResponse<BenhNhan>>() {
            }.getType());
            if (response != null && response.getStatus() == 200) {
                return response.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteBenhNhan(String maBN) {
        String responString = APIManager.get(BASE_URL + "/api/benhnhan/delete?mabn=" + maBN);
        CommonResponse response = gson.fromJson(responString, CommonResponse.class);
        return response != null && response.getStatus() == 200;
    }

    public List<BenhNhanResponse> getAllBenhNhan() {
        String responString = APIManager.get(BASE_URL + "/api/benhnhan/all");
        CommonResponse<List<BenhNhanResponse>> response = gson.fromJson(responString, new TypeToken<CommonResponse<List<BenhNhanResponse>>>() {
        }.getType());
        if (response != null && response.getStatus() == 200) {
            return response.getData();
        }
        return new ArrayList<>();
    }

    public List<BenhNhanResponse> getAllBenhNhanCho() {
        String responString = APIManager.get(BASE_URL + "/api/benhnhan/getAllBenhNhanCho");
        CommonResponse<List<BenhNhanResponse>> response = gson.fromJson(responString, new TypeToken<CommonResponse<List<BenhNhanResponse>>>() {
        }.getType());
        if (response != null && response.getStatus() == 200) {
            return response.getData();
        }
        return new ArrayList<>();
    }

    public List<BenhNhanResponse> getAllBenhNhanDieuTri() {
        String responString = APIManager.get(BASE_URL + "/api/benhnhan/getAllBenhNhanDieuTri");
        CommonResponse<List<BenhNhanResponse>> response = gson.fromJson(responString, new TypeToken<CommonResponse<List<BenhNhanResponse>>>() {
        }.getType());
        if (response != null && response.getStatus() == 200) {
            return response.getData();
        }
        return new ArrayList<>();
    }

}
