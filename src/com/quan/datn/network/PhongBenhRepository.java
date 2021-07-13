/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static com.quan.datn.common.Constants.BASE_URL;
import com.quan.datn.model.CommonResponse;
import com.quan.datn.model.request.AddPhongBenhRequest;
import com.quan.datn.model.response.PhongBenhResponse;
import java.util.ArrayList;
import java.util.List;

public class PhongBenhRepository {

    private Gson gson;

    public PhongBenhRepository() {
        gson = new Gson();
    }

    public boolean addPhongBenh(AddPhongBenhRequest request) {
        try {
            String responString = APIManager.post(BASE_URL + "/api/phongbenh/add", request);
            CommonResponse response = new Gson().fromJson(responString, CommonResponse.class);
            return response != null && response.getStatus() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePhongBenh(String maPB) {
        String responString = APIManager.get(BASE_URL + "/api/phongbenh/delete?mapb=" + maPB);
        CommonResponse response = gson.fromJson(responString, CommonResponse.class);
        return response != null && response.getStatus() == 200;
    }

    public List<PhongBenhResponse> getAllPhongBenh() {
        String responString = APIManager.get(BASE_URL + "/api/phongbenh/all");
        CommonResponse<List<PhongBenhResponse>> response = new Gson().fromJson(responString, new TypeToken<CommonResponse<List<PhongBenhResponse>>>() {
        }.getType());
        if (response != null && response.getStatus() == 200) {
            return response.getData();
        }
        return new ArrayList<>();
    }

}
