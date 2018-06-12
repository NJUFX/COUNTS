package com.fx.HttpRequestor.api;

import com.fx.HttpRequestor.domain.ApiResult;
import com.fx.bean.MLWholeLabel;

import java.util.Map;

/**
 * Created by thinkpad on 2018/6/11.
 */
public class Connecter {
    public MLWholeLabel mlwholeLabel(String Base64Image) {

        String url = "https://api.ai.qq.com/fcgi-bin/vision/vision_imgtotext";

        BaseApiService baseApiService = new BaseApiService();
        Map<String, String> params = baseApiService.buildCommonParam();

        params.put("image", Base64Image);

        try {
            ApiResult<Map> apiResult = baseApiService.postApi(url, params);
            System.out.println(apiResult);
            return new MLWholeLabel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
