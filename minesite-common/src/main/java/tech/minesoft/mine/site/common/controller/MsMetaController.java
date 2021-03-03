package tech.minesoft.mine.site.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.minesoft.mine.site.mysql.models.MsMeta;
import tech.minesoft.mine.site.common.service.MsMetaService;
import tech.minesoft.mine.site.core.vo.ResultJson;

import java.util.List;

@Controller
@RequestMapping("/ms/meta")
public class MsMetaController {

    @Autowired
    private MsMetaService metaService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/list")
    public String list(ModelMap map) {
        List<MsMeta> dataList = metaService.loadAll();
        map.put("dataList", dataList);
        return "ms/meta/list";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/add")
    public String add(ModelMap map) {
        return "ms/meta/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/edit/{id}")
    public String edit(ModelMap map, @PathVariable Integer id) {
        MsMeta meta = metaService.select(id);
        map.put("meta", meta);
        return "ms/meta/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/delete/{id}")
    public ResultJson delete(@PathVariable Integer id) {

        metaService.delete(id);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/submit")
    public ResultJson submit(MsMeta meta) {

        if(null==meta.getId()){
            metaService.add(meta);
        }else{
            metaService.modify(meta);
        }

        return ResultJson.success();
    }

}
