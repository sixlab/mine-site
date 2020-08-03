package cn.sixlab.mine.site.service.controller;

import cn.sixlab.mine.site.core.models.MsUser;
import cn.sixlab.mine.site.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/i")
public class MineController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap model) {

        return "index";
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/{username}")
    public String test(ModelMap map, @PathVariable("username") String username) {
        MsUser user = userService.loadUser(username);
        map.put("userInfo", user);
        return "i/user";
    }

}
