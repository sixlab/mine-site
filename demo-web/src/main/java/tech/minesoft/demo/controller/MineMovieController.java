package tech.minesoft.demo.controller;

import tech.minesoft.minesite.core.vo.ResultJson;
import tech.minesoft.demo.models.VodUserStar;
import tech.minesoft.demo.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/mine/movie")
public class MineMovieController {

    @Autowired
    private MovieService service;

    @ResponseBody
    @RequestMapping("/star/add")
    public ResultJson addStar(Integer userId, Integer infoId) {
        // Integer userId = UserUtils.loginedUserId();

        service.addStar(userId, infoId);

        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping("/star/del")
    public ResultJson delStar(Integer userId, Integer infoId) {
        // Integer userId = UserUtils.loginedUserId();

        service.delStar(userId, infoId);

        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping("/star/list")
    public ResultJson listStar(Integer userId) {
        // Integer userId = UserUtils.loginedUserId();

        List<VodUserStar> data = service.listStar(userId);

        return ResultJson.successData(data);
    }
}
