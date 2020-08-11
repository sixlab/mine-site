package tech.minesoft.mine.site.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Ctx {
    private static ApplicationContext ctx;

    @Autowired
    public void setCtx(ApplicationContext ctx) {
        Ctx.ctx = ctx;
        System.out.println(ctx);
    }

    public static String getProperty(String code){
        return Ctx.getBean(Environment.class).getProperty(code);
    }

    public static <T> Map<String, T> getBeans(Class<T> clz) {
        return ctx.getBeansOfType(clz);
    }

    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clz) {
        return ctx.getBean(clz);
    }

    public static <T> T getBean(Class<T> clz, String componentName) {
        return ctx.getBean(componentName, clz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clz) {

        return ctx.getBeansOfType(clz);
    }
}
