package cn.sixlab.mine.site.service.controller;

import cn.sixlab.mine.site.common.vo.Result;
import cn.sixlab.mine.site.data.models.MsMenu;
import cn.sixlab.mine.site.service.service.MenuService;
import cn.sixlab.mine.site.common.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("/userMenu")
    public Result userMenu() {
        Page<MsMenu> msMenus = menuService.loadUserMenu(null, 1);

        return ResultUtils.success(msMenus);
    }
}
