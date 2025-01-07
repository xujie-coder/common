package response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页响应
 *
 * @author xujie
 * @since 2025/01/06 20:53
 */
@Getter
@Setter
public class PageResponse<T> extends MultiResponse<T>{
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 每页结果数
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总数
     */
    private int total;

    public static <T> PageResponse<T> of(List<T> datas,int total,int currentPage,int pageSize){
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setSuccess(true);
        pageResponse.setDatas(datas);
        pageResponse.setTotal(total);
        pageResponse.setCurrentPage(currentPage);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotalPage((total + pageSize - 1) / pageSize);
        return pageResponse;
    }

}
