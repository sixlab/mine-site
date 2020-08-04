package tech.minesoft.demo.vo;

import tech.minesoft.demo.models.VodInfo;
import lombok.Data;

import java.util.Map;

@Data
public class Vod {
    private VodInfo vodInfo;
    private String remark;
    private Map<String, String> urlTexts;
}
