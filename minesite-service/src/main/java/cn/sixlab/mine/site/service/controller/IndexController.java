package cn.sixlab.mine.site.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap model) {

        return "index";
    }

}
