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
import com.quan.datn.model.DonThuocModel;
import com.quan.datn.model.request.BenhAnRequest;
import com.quan.datn.model.response.BenhAnResponse;
import java.util.ArrayList;
import java.util.List;

public class BenhAnRepository {

    
    
    public boolean addDonThuoc(DonThuocModel request) {
        try {
            String responString = APIManager.post(BASE_URL + "/api/donthuoc/add", request);
            CommonResponse response = new Gson().fromJson(responString, CommonResponse.class);
            return response != null && response.getStatus() == 200;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateBenhAn(BenhAnRequest request){
         try {
            String responString = APIManager.post(BASE_URL + "/api/benhan/update", request);
            CommonResponse response = new Gson().fromJson(responString, CommonResponse.class);
            return response != null && response.getStatus() == 200;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    public boolean addBenhAn(BenhAnRequest request) {
        try {
            String responString = APIManager.post(BASE_URL + "/api/benhan/add", request);
            CommonResponse response = new Gson().fromJson(responString, CommonResponse.class);
            return response != null && response.getStatus() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BenhAnResponse> getAllBenhAn(String maBN) {
        String responString = APIManager.get(BASE_URL + "/api/benhan/all?mabn=" + maBN);
        CommonResponse<List<BenhAnResponse>> response = new Gson().fromJson(responString, new TypeToken<CommonResponse<List<BenhAnResponse>>>() {
        }.getType());
        if (response != null && response.getStatus() == 200) {
            return response.getData();
        }
        return new ArrayList<>();
    }

}
