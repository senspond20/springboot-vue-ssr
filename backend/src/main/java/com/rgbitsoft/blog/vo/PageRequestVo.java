package com.rgbitsoft.blog.vo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=true) // 부모필드값 들도 동일한지 체크
@ToString
public class PageRequestVo extends LoginUserId{
    private int page;
    private int pageSize;
    //private boolean sampleExcelDownload;
    public PageRequestVo(){
        super();
    }
    public PageRequestVo(String loginuserId){
        super(loginuserId);
    }
    public void addPage(){
        page++;
    }
    public int getOffset(){
        return (page-1)*pageSize;
    }
}

