package tech.minesoft.demo.controller;

import tech.minesoft.mine.site.core.vo.ResultJson;
import tech.minesoft.demo.models.VodGroup;
import tech.minesoft.demo.models.VodPlayer;
import tech.minesoft.demo.models.VodSite;
import tech.minesoft.demo.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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

    @ResponseBody
    @RequestMapping("/info")
    public ResultJson info(Integer infoId) {

        Map<String, Object> data = service.info(infoId);

        return ResultJson.successData(data);
    }

    @ResponseBody
    @RequestMapping("/site/add")
    public ResultJson add(@RequestBody VodSite site) {

        service.addSite(site);

        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping("/site/update")
    public ResultJson update(@RequestBody VodSite site) {

        service.updateSite(site);

        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping("/player/add")
    public ResultJson addPlayer(@RequestBody VodPlayer player) {

        service.addPlayer(player);

        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping("/player/update")
    public ResultJson updatePlayer(@RequestBody VodPlayer player) {

        service.updatePlayer(player);

        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping("/group/add")
    public ResultJson addGroup(@RequestBody VodGroup group) {

        service.addGroup(group);

        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping("/group/update")
    public ResultJson updateGroup(@RequestBody VodGroup group) {

        service.updateGroup(group);

        return ResultJson.success();
    }
}
