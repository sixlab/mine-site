package cn.sixlab.mine.site.service.vo;

import cn.sixlab.mine.site.data.models.VodInfo;
import lombok.Data;

import java.util.Map;

@Data
public class Vod {
    private VodInfo vodInfo;
    private String remark;
    private Map<String, String> urlTexts;
}
