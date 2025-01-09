package com.xjtu.base.response;

import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * restful api请求格式的响应
 *
 * @author xujie
 * @since 2025/01/06 21:23
 */
@Getter
@Setter
public class RestResponse extends BaseResponse{
    private JSONObject data;

    private JSONObject error;

    @Override
    public Boolean getSuccess(){
        return data != null;
    }

    @Override
    public String getResponseCode(){
        if (error != null) {
            return error.getString("code");
        }
        return null;
    }

    @Override
    public String getResponseMessage(){
        if (error != null) {
            return error.getString("message");
        }
        return null;
    }
}
