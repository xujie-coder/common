package response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xujie
 * @since 2025/01/06 21:15
 */
@Getter
@Setter
public class SingleResponse<T> extends BaseResponse{
    private static final long serialVersionUID = 1L;

    /**
     * 响应数据
     */
    private T data;

    public static <T> SingleResponse<T> of(T data){
        SingleResponse<T> singleResponse = new SingleResponse<>();
        singleResponse.setSuccess(true);
        singleResponse.setData(data);
        return singleResponse;
    }

    public static <T> SingleResponse<T> fail(String errorCode,String errorMessage){
        SingleResponse<T> singleResponse = new SingleResponse<>();
        singleResponse.setSuccess(false);
        singleResponse.setResponseCode(errorCode);
        singleResponse.setResponseMessage(errorMessage);
        return singleResponse;
    }
}
