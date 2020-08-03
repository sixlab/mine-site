package cn.sixlab.mine.site.core.vo;

import cn.sixlab.mine.site.core.models.VodInfo;
import lombok.Data;

import java.util.Map;

@Data
public class Vod {
    private VodInfo vodInfo;
    private String remark;
    private Map<String, String> urlTexts;
}
