package response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
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

    private Boolean success;

    private String responseCode;

    private String responseMessage;
}
