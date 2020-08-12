package tech.minesoft.mine.site.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.minesoft.mine.site.core.models.MsMenu;
import tech.minesoft.mine.site.core.service.MsMenuService;
import tech.minesoft.mine.site.core.vo.ResultJson;

import java.util.List;

@Controller
@RequestMapping("/ms/menu")
public class MsMenuController {

    @Autowired
    private MsMenuService menuService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/list")
    public String list(ModelMap map) {
        List<MsMenu> dataList = menuService.loadAll();
        map.put("dataList", dataList);
        return "ms/menu/list";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/add")
    public String add(ModelMap map) {
        return "ms/menu/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/edit/{id}")
    public String edit(ModelMap map, @PathVariable Integer id) {
        MsMenu data = menuService.select(id);
        map.put("data", data);
        return "ms/menu/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/delete/{id}")
    public ResultJson delete(@PathVariable Integer id) {

        menuService.delete(id);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/submit")
    public ResultJson submit(MsMenu data) {

        if(null==data.getId()){
            menuService.add(data);
        }else{
            menuService.modify(data);
        }

        return ResultJson.success();
    }

}
