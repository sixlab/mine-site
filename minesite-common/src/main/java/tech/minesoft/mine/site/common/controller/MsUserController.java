package tech.minesoft.mine.site.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.minesoft.mine.site.mysql.models.MsUser;
import tech.minesoft.mine.site.common.service.MsUserService;
import tech.minesoft.mine.site.core.vo.ResultJson;

import java.util.List;

@Controller
@RequestMapping("/ms/user")
public class MsUserController {

    @Autowired
    private MsUserService userService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/list")
    public String list(ModelMap map) {
        List<MsUser> dataList = userService.loadAll();
        map.put("dataList", dataList);
        return "ms/user/list";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/add")
    public String add(ModelMap map) {
        return "ms/user/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/edit/{id}")
    public String edit(ModelMap map, @PathVariable Integer id) {
        MsUser data = userService.select(id);
        map.put("data", data);
        return "ms/user/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/delete/{id}")
    public ResultJson delete(@PathVariable Integer id) {

        userService.delete(id);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/submit")
    public ResultJson submit(MsUser data) {

        if(null==data.getId()){
            userService.add(data);
        }else{
            userService.modify(data);
        }

        return ResultJson.success();
    }

}
