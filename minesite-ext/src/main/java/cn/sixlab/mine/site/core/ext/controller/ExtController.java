package cn.sixlab.mine.site.core.ext.controller;

import cn.sixlab.mine.site.core.vo.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ext")
public class ExtController {

    @ResponseBody
    @RequestMapping(value = {"/", "/index"})
    public ResultJson ext(ModelMap model) {

        return ResultJson.success();
    }

}
