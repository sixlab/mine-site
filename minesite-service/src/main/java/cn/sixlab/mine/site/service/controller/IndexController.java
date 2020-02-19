package cn.sixlab.mine.site.service.controller;

import cn.sixlab.mine.site.data.models.MsMenu;
import cn.sixlab.mine.site.service.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap model) {

        Page<MsMenu> msMenus = menuService.loadUserMenu(null, 1);
        model.put("result", msMenus);

        return "index";
    }

}
