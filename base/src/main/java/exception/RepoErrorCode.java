package exception;

/**
 * 远程调用错误码
 *
 * @author xujie
 * @since 2025/01/07 15:37
 */
public enum RepoErrorCode implements ErrorCode{
    /**
     * 未知错误
     */
    UNKNOWN_ERROR("UNKNOWN_ERROR","未知错误"),
    /**
     * 数据库插入失败
     */
    INSERT_FAILED("INSERT_FAILED","数据库插入失败"),
    /**
     * 数据库更新失败
     */
    UPDATE_FAILED("UPDATE_FAILED","数据库更新失败");

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    RepoErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 错误码
     *
     * @return 错误码
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * 错误信息
     *
     * @return 错误信息
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}
