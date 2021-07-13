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
import com.quan.datn.model.response.BaoCaoResponse;
import com.quan.datn.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class BaoCaoRepository {

    public List<BaoCaoResponse> getAllBaoCao(String startTime, String endTime) {
        String responString = APIManager.get(BASE_URL + "/api/baocao/all?startTime=" + Utils.encodeValue(startTime) + "&endTime=" + Utils.encodeValue(endTime));
        CommonResponse<List<BaoCaoResponse>> response = new Gson().fromJson(responString, new TypeToken<CommonResponse<List<BaoCaoResponse>>>() {
        }.getType());
        if (response != null && response.getStatus() == 200) {
            return response.getData();
        }
        return new ArrayList<>();
    }

}
