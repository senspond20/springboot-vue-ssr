package com.rgbitsoft.blog.vo;

import com.rgbitsoft.blog.vo.request.SentimentListVo;
import org.junit.jupiter.api.Test;

public class SentimentListVoTest {

    @Test
    void test1(){
        //SentimentListVo sentimentListVo = new SentimentListVo();
        SentimentListVo sentimentListVo = SentimentListVo.builder().keyword("키워드").order("order").query("query").build();

        System.out.println(sentimentListVo);

    }
}
