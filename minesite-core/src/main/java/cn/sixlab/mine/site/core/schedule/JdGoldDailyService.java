package cn.sixlab.mine.site.core.schedule;

import cn.sixlab.mine.site.core.utils.HttpUtils;
import cn.sixlab.mine.site.core.utils.JsonUtils;
import cn.sixlab.mine.site.core.vo.ResultJson;
import cn.sixlab.mine.site.core.mapper.MsDataMapper;
import cn.sixlab.mine.site.core.mapper.MsNotifyConfigMapper;
import cn.sixlab.mine.site.core.api.Job;
import cn.sixlab.mine.site.core.service.DingTalkService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class JdGoldDailyService implements Job {
    private static String CODE = "jdGold";

    @Autowired
    private MsDataMapper dataDao;

    @Autowired
    private MsNotifyConfigMapper notifyConfigDao;

    @Autowired
    private DingTalkService dingTalkService;

    @Override
    public void run() {
        String apiUrl = "https://ms.jr.jd.com/gw/generic/hj/h5/m/latestPrice";

        Map<String, String> header = new HashMap<>();
        header.put("host", "ms.jr.jd.com");
        header.put("referer", "https://m.jdjygold.com/finance-gold/msjgold/homepage?orderSource=7");

        ResultJson resultJson = HttpUtils.sendGet(apiUrl, null, header);
        if (resultJson.isSuccess()) {
            Map map = JsonUtils.toBean(resultJson.getMessage(), Map.class);

            Map resultData = MapUtils.getMap(map, "resultData");
            Map datas = MapUtils.getMap(resultData, "datas");

            Date time = new Date(MapUtils.getLong(datas, "time"));
            String timeStr = DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss");
            BigDecimal price = new BigDecimal(MapUtils.getString(datas, "price"));

            StringBuilder builder = new StringBuilder();
            builder.append("\n价格：");
            builder.append(price.toPlainString());
            builder.append("\n日期：");
            builder.append(timeStr);
            builder.append("\n访问：https://datastrend.com/api/gold/index\n");

            dingTalkService.sendText(builder.toString());
        }
    }

}
