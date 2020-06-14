package cn.sixlab.mine.site.service.controller;

import cn.sixlab.mine.site.common.vo.ResultJson;
import cn.sixlab.mine.site.service.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService service;

    @ResponseBody
    @RequestMapping("/init")
    public ResultJson init() {

        service.init();

        return ResultJson.success();
    }
}
