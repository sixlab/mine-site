package cn.sixlab.mine.site.service.controller;

import cn.sixlab.mine.site.common.vo.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/test")
public class AuthTestController {

    @ResponseBody
    @RequestMapping("/index")
    public ResultJson index() {
        log.info("不添加 PreAuthorize/PostAuthorize 的，不判断权限");
        log.info("可以查看 Spring Security 的 PreAuthorize/PostAuthorize 方法 ");
        log.info(" hasPermission() 是自定义的，见示例");
        return ResultJson.success();
    }

    @PostAuthorize("denyAll")
    @ResponseBody
    @RequestMapping("/post")
    public ResultJson post() {
        log.info(" PostAuthorize 会在运行完成，return 之后判断权限 ");
        log.info(" denyAll/denyAll() 所有请求会被拦截 ");
        return ResultJson.success();
    }

    @PreAuthorize("permitAll()")
    @ResponseBody
    @RequestMapping("/pre")
    public ResultJson pre() {
        log.info(" PreAuthorize 会在运行之前判断权限 ");
        log.info(" permitAll/permitAll() 所有请求通过，和不加一样 ");
        return ResultJson.success();
    }
}
