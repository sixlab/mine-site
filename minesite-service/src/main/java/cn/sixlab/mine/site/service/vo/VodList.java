package cn.sixlab.mine.site.service.vo;

import lombok.Data;

import java.util.List;

@Data
public class VodList {
    private List<Vod> vodList;
    private Integer totalPage;
}
