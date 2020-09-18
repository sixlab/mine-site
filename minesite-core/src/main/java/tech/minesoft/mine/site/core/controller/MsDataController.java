package tech.minesoft.mine.site.core.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.minesoft.mine.site.core.models.MsData;
import tech.minesoft.mine.site.core.service.MsDataService;
import tech.minesoft.mine.site.core.utils.UserUtils;
import tech.minesoft.mine.site.core.vo.ResultJson;

import java.util.List;

@Controller
@RequestMapping("/ms/data")
public class MsDataController {

    @Autowired
    private MsDataService dataService;

    @ResponseBody
    @PreAuthorize("hasAuthority('user')")
    @GetMapping(value = "/listAll")
    public ResultJson listAll(String dataType) {
        List<MsData> dataList = dataService.loadAllData(dataType);
        return ResultJson.successData(dataList);
    }

    @ResponseBody
    @PreAuthorize("hasAuthority('user')")
    @RequestMapping(value = "/list")
    public ResultJson list(ModelMap map, String dataType,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {

        PageInfo<MsData> dataInfo = dataService.loadData(dataType, pageNum, pageSize);

        return ResultJson.successData(dataInfo);
    }

    @ResponseBody
    @PreAuthorize("hasAuthority('user')")
    @GetMapping(value = "/select/{id}")
    public ResultJson select(@PathVariable Integer id) {
        MsData data = dataService.select(id);
        return ResultJson.successData(data);
    }

    @ResponseBody
    @PreAuthorize("hasAuthority('user')")
    @GetMapping(value = "/select")
    public ResultJson select(String dataType, String dataId, String param) {
        MsData data = dataService.select(dataType, dataId, param);
        return ResultJson.successData(data);
    }

    @PreAuthorize("hasAuthority('user')")
    @ResponseBody
    @PostMapping(value = "/delete/{id}")
    public ResultJson delete(@PathVariable Integer id) {

        dataService.delete(id);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('user')")
    @ResponseBody
    @PostMapping(value = "/delete")
    public ResultJson delete(String dataType, String dataId, String param) {

        dataService.delete(dataType, dataId, param);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('user')")
    @ResponseBody
    @PostMapping(value = "/add")
    public ResultJson add(MsData data) {

        dataService.add(data);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('user')")
    @ResponseBody
    @PostMapping(value = "/modify")
    public ResultJson modify(MsData data) {
        dataService.modify(data);

        return ResultJson.success();
    }

}
