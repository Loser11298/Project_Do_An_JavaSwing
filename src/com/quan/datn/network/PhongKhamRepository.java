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
import com.quan.datn.model.PhongKham;
import com.quan.datn.model.request.AddPhongKhamRequest;
import java.util.ArrayList;
import java.util.List;

public class PhongKhamRepository {

    private Gson gson;

    public PhongKhamRepository() {
        gson = new Gson();
    }

    public boolean addPhongKham(AddPhongKhamRequest request) {
        try {
            String responString = APIManager.post(BASE_URL + "/api/phongkham/add", request);
            CommonResponse response = new Gson().fromJson(responString, CommonResponse.class);
            return response != null && response.getStatus() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePhongKham(String maPK) {
        String responString = APIManager.get(BASE_URL + "/api/phongkham/delete?mapk=" + maPK);
        CommonResponse response = gson.fromJson(responString, CommonResponse.class);
        return response != null && response.getStatus() == 200;
    }

    public List<PhongKham> getAllPhongKham() {
        String responString = APIManager.get(BASE_URL + "/api/phongkham/all");
        CommonResponse<List<PhongKham>> response = new Gson().fromJson(responString, new TypeToken<CommonResponse<List<PhongKham>>>() {
        }.getType());
        if (response != null && response.getStatus() == 200) {
            return response.getData();
        }
        return new ArrayList<>();
    }

}
