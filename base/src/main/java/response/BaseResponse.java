package response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 通用出参
 *
 * @author xujie
 * @since 2025/01/06 20:22
 */
@Getter
@Setter
@ToString
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 响应码
     */
    private String responseCode;

    /**
     * 响应信息
     */
    private String responseMessage;
}
