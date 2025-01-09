package com.xjtu.base.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 响应
 *
 * @author xujie
 * @since 2025/01/06 20:28
 */
@Getter
@Setter
public class MultiResponse<T> extends BaseResponse{
    private static final long serialVersionUID = 1L;

    /**
     * 响应数据
     */
    private List<T> datas;

    public static <T> MultiResponse<T> of(List<T> datas){
        MultiResponse<T> multiResponse = new MultiResponse<>();
        multiResponse.setSuccess(true);
        multiResponse.setDatas(datas);
        return multiResponse;
    }
}
