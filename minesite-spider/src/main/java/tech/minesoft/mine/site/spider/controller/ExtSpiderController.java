package tech.minesoft.mine.site.spider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.minesoft.mine.site.core.vo.ResultJson;

@Controller
@RequestMapping("/ext/spider")
public class ExtSpiderController {

    @ResponseBody
    @RequestMapping(value = {"/", "/index"})
    public ResultJson ext(ModelMap model) {

        return ResultJson.success();
    }

}
