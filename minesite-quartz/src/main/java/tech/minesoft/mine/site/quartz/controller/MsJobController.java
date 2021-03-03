package tech.minesoft.mine.site.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.minesoft.mine.site.core.utils.Err;
import tech.minesoft.mine.site.core.utils.JsonUtils;
import tech.minesoft.mine.site.core.vo.JobVo;
import tech.minesoft.mine.site.core.vo.ResultJson;
import tech.minesoft.mine.site.quartz.service.MsJobService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ms/job")
public class MsJobController {

    @Autowired
    private MsJobService jobService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/list")
    public String list(ModelMap map) {
        List<Map<String, Object>> jobs = jobService.queryAllJob();
        map.put("jobs", jobs);
        map.put("currentTitle", "所有");
        return "ms/job/list";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/active")
    public String active(ModelMap map) {
        List<Map<String, Object>> jobs = jobService.queryRunJob();
        map.put("jobs", jobs);
        map.put("currentTitle", "运行中");
        return "ms/job/list";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/add")
    public String add(ModelMap map) {
        return "ms/job/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/edit/{jobName}/{groupName}")
    public String edit(ModelMap map, @PathVariable String jobName,  @PathVariable String groupName) {
        Map<String, Object> job = jobService.selectJob(jobName, groupName);
        map.put("job", job);
        return "ms/job/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/delete")
    public ResultJson delete(String jobName, String groupName) {

        jobService.deleteJob(jobName, groupName);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/run")
    public ResultJson run(String jobName, String groupName) {

        jobService.runAJobNow(jobName, groupName);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/submit")
    public ResultJson submit(JobVo job) {
        try {
            Class clz = Class.forName(job.getJobClass());
            Map jobData = JsonUtils.toBean(job.getJobData(), Map.class);

            jobService.deleteJob(job.getJobName(), job.getGroupName());
            jobService.addJob(clz, job.getJobName(), job.getGroupName(), job.getJobTime(), jobData);
            return ResultJson.success();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return ResultJson.error(Err.EXCEPTION, "不存在类："+job.getJobClass());
        }
    }

}
