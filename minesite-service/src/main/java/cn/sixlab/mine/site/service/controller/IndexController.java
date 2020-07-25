package cn.sixlab.mine.site.service.controller;

import cn.sixlab.mine.site.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private UserUtils userUtils;

    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap model) {

        String ab1 = userUtils.put("abc", "----");
        String ab2 = userUtils.put("abc", "----");

        return "index";
    }

    @RequestMapping(value = "/test")
    public String test(ModelMap model) {

        return "index";
    }

    @RequestMapping(value = "/test/")
    public String test0(ModelMap model) {

        return "index";
    }

    @RequestMapping(value = "/test/abc")
    public String test1(ModelMap model) {

        return "index";
    }

}
