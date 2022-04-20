package com.rgbitsoft.blog.vo;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class PageResponseVo<T> {
    private final boolean serverPaging=true, serverSorting=true;
    private final int page;
    private final int pageSize;
    private final int offset;
    private final long total;
    private final List<T> data;

    private Object object;

    public PageResponseVo(PageRequestVo pageRequestVo, long total, List data){
        this.page=pageRequestVo.getPage();
        this.pageSize=pageRequestVo.getPageSize();
        this.offset=pageRequestVo.getOffset();
        this.total=total;
        this.data=data==null ? new ArrayList() : data;
    }

    public PageResponseVo setObject(Object object){
        this.object=object;
        return this;
    }
}
