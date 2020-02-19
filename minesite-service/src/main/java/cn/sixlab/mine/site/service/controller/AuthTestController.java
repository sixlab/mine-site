package cn.sixlab.mine.site.service.controller;

import cn.sixlab.mine.site.common.vo.Result;
import cn.sixlab.mine.site.common.utils.ResultUtils;
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
    public Result index() {
        log.info("不添加 PreAuthorize/PostAuthorize 的，不判断权限");
        log.info("可以查看 Spring Security 的 PreAuthorize/PostAuthorize 方法 ");
        log.info(" hasPermission() 是自定义的，见示例");
        return ResultUtils.success();
    }

    @PostAuthorize("denyAll")
    @ResponseBody
    @RequestMapping("/post")
    public Result post() {
        log.info(" PostAuthorize 会在运行完成，return 之后判断权限 ");
        log.info(" denyAll/denyAll() 所有请求会被拦截 ");
        return ResultUtils.success();
    }

    @PreAuthorize("permitAll()")
    @ResponseBody
    @RequestMapping("/pre")
    public Result pre() {
        log.info(" PreAuthorize 会在运行之前判断权限 ");
        log.info(" permitAll/permitAll() 所有请求通过，和不加一样 ");
        return ResultUtils.success();
    }

    @PreAuthorize("hasPermission('code', '')")
    @ResponseBody
    @RequestMapping("/admin")
    public Result admin() {
        log.info(" hasPermission 三个参数的第一个参数无效，剩下两个和两参数的一样 ");
        log.info(" 详细逻辑见 cn.sixlab.minesite.config.MinePermissionEvaluator ");

        log.info(" hasPermission 第一个参数是管理员的权限控制 ");
        log.info(" hasPermission 第二个参数是前端用户的权限控制，待定 ");

        log.info(" 第一个参数，如果是空或者permitAll，则所有管理员都可访问 ");
        log.info(" 如果是逗号分割字符串，是多个 MsAuth 的 authCode，有对应权限的才可访问 ");

        return ResultUtils.success();
    }

    @PreAuthorize("hasPermission('button,menu', 'write')")
    @ResponseBody
    @RequestMapping("/user")
    public Result user() {

        log.info(" 第二个参数，如果是空或者permitAll，则所有前端用户都可访问 ");
        log.info(" 其他逻辑待定 ");

        return ResultUtils.success();
    }
}
