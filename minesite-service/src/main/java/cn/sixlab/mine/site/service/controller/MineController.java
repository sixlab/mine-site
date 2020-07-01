package cn.sixlab.mine.site.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/i")
public class MineController {

    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap model) {

        return "index";
    }

    @RequestMapping(value = "/{username}")
    public String test(@PathVariable("username") String username) {

        return "index";
    }

}
