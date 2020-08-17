package tech.minesoft.mine.site.core.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

public class BaseController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 时间戳       -》   Date 类型
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                if (StringUtils.hasLength(value)) {
                    setValue(new Date(Long.valueOf(value)));
                }
            }
        });
    }

}
