package com.zuoben.util.page;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2017/9/11.
 * <p>
 * Describe: 分页工具
 */
public class PageUtils {

    /**
     * 切割list
     *
     * @param pageNo
     * @param pageSize
     * @param list
     * @return
     */
    public static List<?> subList(int pageNo, int pageSize, List<?> list) {

        if (null == list || list.size() == 0) {
            return list;
        }

        int listSize = list.size();
        if (pageSize > listSize) {
            pageSize = listSize;
        }

        Pagination pagination = new Pagination(pageNo, pageSize, listSize);
        List<?> sList = new ArrayList<>();

        int endIndex = pagination.getPageNo() * pagination.getPageSize();
        if (endIndex > listSize) {
            endIndex = listSize;
        }
        int offset = (pagination.getPageNo() - 1) * pageSize;

        sList = list.subList(offset, endIndex);

        return sList;
    }


    public static <E> PageInfo<E> transData(PageInfo source, List<E> target) {
        PageInfo<E> result = new PageInfo<>();
        BeanUtils.copyProperties(source, result);
        result.setList(target);
        return result;
    }
}
