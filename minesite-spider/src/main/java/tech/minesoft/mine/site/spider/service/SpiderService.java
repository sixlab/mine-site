package tech.minesoft.mine.site.spider.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.minesoft.mine.site.core.utils.HttpUtils;
import tech.minesoft.mine.site.core.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpiderService {

    @Value("${minesite.dingtalk.token}")
    private String dtToken;

    public void sendText(String msg) {
        try {
            Map<String, Object> text = new HashMap<>();
            text.put("content", msg);

            Map<String, Object> at = new HashMap<>();
            at.put("isAtAll", true);

            Map<String, Object> json = new HashMap<>();
            json.put("msgtype", "text");
            json.put("text", text);
            json.put("at", at);

            String url = "https://oapi.dingtalk.com/robot/send?access_token=" + dtToken;

            HttpUtils.sendPostJson(url, JsonUtils.toJson(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
