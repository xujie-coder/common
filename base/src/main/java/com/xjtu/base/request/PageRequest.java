package com.xjtu.base.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页请求入参
 *
 * @author xujie
 * @since 2025/01/06 19:59
 */
@Getter
@Setter
public class PageRequest extends BaseRequest{
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 每页结果数
     */
    private int pageSize;
}
