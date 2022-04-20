package com.rgbitsoft.blog.vo.request;

import com.rgbitsoft.blog.vo.PageRequestVo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=true)
@ToString
public class SentimentListVo extends PageRequestVo {
    private String serviceType;
    private String keyword;
    private String query;
    private String order;

    @Builder
    public SentimentListVo(String loginUserId, String serviceType, String keyword, String query, String order) {
        super();

        this.serviceType = serviceType;
        this.keyword = keyword;
        this.query = query;
        this.order = order;
    }
//    private SentimentWordVo vo;
}
