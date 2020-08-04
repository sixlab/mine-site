package tech.minesoft.demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class VodList {
    private List<Vod> vodList;
    private Integer totalPage;
}
