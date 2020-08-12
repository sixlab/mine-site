package tech.minesoft.mine.site.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.minesoft.mine.site.core.models.MsJob;
import tech.minesoft.mine.site.core.models.MsJobRecord;
import tech.minesoft.mine.site.core.service.MsJobService;
import tech.minesoft.mine.site.core.vo.ResultJson;

import java.util.List;

@Controller
@RequestMapping("/ms/job")
public class MsJobController {

    @Autowired
    private MsJobService jobService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/list")
    public String list(ModelMap map) {
        List<MsJob> dataList = jobService.loadAll();
        map.put("dataList", dataList);
        return "ms/job/list";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/record")
    public String record(ModelMap map) {
        List<MsJobRecord> dataList = jobService.loadLastRecord();
        map.put("dataList", dataList);
        return "ms/job/record";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/add")
    public String add(ModelMap map) {
        return "ms/job/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/edit/{id}")
    public String edit(ModelMap map, @PathVariable Integer id) {
        MsJob job = jobService.select(id);
        map.put("job", job);
        return "ms/job/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/delete/{id}")
    public ResultJson delete(@PathVariable Integer id) {

        jobService.delete(id);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/submit")
    public ResultJson submit(MsJob job) {

        if(null==job.getId()){
            jobService.add(job);
        }else{
            jobService.modify(job);
        }

        return ResultJson.success();
    }

}
