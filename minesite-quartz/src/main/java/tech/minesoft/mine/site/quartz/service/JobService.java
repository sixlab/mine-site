package tech.minesoft.mine.site.ext.service;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.minesoft.mine.site.core.utils.CacheUtils;
import tech.minesoft.mine.site.core.utils.HttpUtils;
import tech.minesoft.mine.site.core.utils.JsonUtils;
import tech.minesoft.mine.site.core.vo.ResultJson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FeiShuService {

    @Value("${minesite.feishu.appId}")
    private String appId;

    @Value("${minesite.feishu.appSecret}")
    private String appSecret;

    @Value("${minesite.feishu.verifyToken}")
    private String verifyToken;

    @Value("${minesite.feishu.userId}")
    private String userId;

    public void sendText(String msg) {
        try {
            String accessToken = accessToken();

            if(StringUtils.isNotEmpty(accessToken)){
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", accessToken);

                Map<String, Object> content = new HashMap<>();
                content.put("text", msg);

                Map<String, Object> json = new HashMap<>();
                json.put("user_id", userId);
                json.put("msg_type", "text");
                json.put("content", content);

                String url = "https://open.feishu.cn/open-apis/message/v4/send/";

                HttpUtils.sendPostJson(url, JsonUtils.toJson(json), header);
            }else{
                System.out.println("token 获取失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String accessToken(){
        Long expire = CacheUtils.getLong("feishu_token_expire");
        if(null != expire && expire > new Date().getTime() ){
            String token = CacheUtils.getString("feishu_token");
            if(StringUtils.isNotEmpty(token)){
                return token;
            }
        }

        Map<String, Object> json = new HashMap<>();
        json.put("app_id", appId);
        json.put("app_secret", appSecret);

        String url = "https://open.feishu.cn/open-apis/auth/v3/app_access_token/internal/";

        ResultJson resultJson = HttpUtils.sendPostJson(url, JsonUtils.toJson(json));

        if(resultJson.isSuccess()){
            String jsonMessage = resultJson.getMessage();
            Map<String, Object> jsonMap = JsonUtils.toBean(jsonMessage, Map.class);
            String token = MapUtils.getString(jsonMap, "app_access_token");
            if(StringUtils.isNotEmpty(token)){
                token = "Bearer " + token;

                CacheUtils.put("feishu_token", token);
                CacheUtils.put("feishu_token_expire", DateUtils.addMinutes(new Date(), 100).getTime());

                return token;
            }
        }

        return "";
    }
}
