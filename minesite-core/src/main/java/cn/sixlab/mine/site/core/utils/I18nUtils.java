package cn.sixlab.mine.site.core.utils;

import cn.sixlab.mine.site.core.vo.I18nMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.util.Locale;

@Slf4j
@Component
public class I18nUtils {
    private static MessageSource messageSource;

    public I18nUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static String get(String code, Object... args) {
        try {
            return messageSource.getMessage(code, args, code, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return code;
        }
    }

    public static String get(ServletRequest request, String code, Object... args) {
        try {
            String locale = request.getParameter("lang");
            if (StringUtils.isEmpty(locale)) {
                locale = "zh_CN";
            }

            return messageSource.getMessage(code, args, code, org.springframework.util.StringUtils.parseLocaleString(locale));
        } catch (Exception e) {
            return code;
        }
    }

    public static String get(Locale locale, String code, Object... args) {
        try {
            return messageSource.getMessage(code, args, code, locale);
        } catch (Exception e) {
            return code;
        }
    }

    public static String toMsg(String i18nMsg) {
        I18nMsg msg = JsonUtils.toBean(i18nMsg, I18nMsg.class);

        return get(msg.getCode(), msg.getArgs());
    }

    public static String toMsg(ServletRequest request, String i18nMsg) {
        I18nMsg msg = JsonUtils.toBean(i18nMsg, I18nMsg.class);

        return get(request, msg.getCode(), msg.getArgs());
    }
}
