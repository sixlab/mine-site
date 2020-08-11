package tech.minesoft.mine.site.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.minesoft.mine.site.core.models.MsMeta;
import tech.minesoft.mine.site.core.service.MsMetaService;
import tech.minesoft.mine.site.core.vo.ResultJson;

import java.util.List;

@Controller
@RequestMapping("/meta")
public class MsMetaController {

    @Autowired
    private MsMetaService metaService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/list")
    public String list(ModelMap map) {
        List<MsMeta> dataList = metaService.loadAll();
        map.put("dataList", dataList);
        return "meta/list";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/add")
    public String add(ModelMap map) {
        return "meta/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/edit/{id}")
    public String edit(ModelMap map, @PathVariable Integer id) {
        MsMeta meta = metaService.select(id);
        map.put("meta", meta);
        return "meta/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/delete/{id}")
    public ResultJson delete(ModelMap map, @PathVariable Integer id) {

        metaService.delete(id);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/submit")
    public ResultJson submit(ModelMap map, MsMeta meta) {

        if(null==meta.getId()){
            metaService.add(meta);
        }else{
            metaService.modify(meta);
        }

        return ResultJson.success();
    }

}
