package tech.minesoft.mine.site.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.minesoft.mine.site.core.service.MsMetaService;

import java.util.Map;

@Controller
@RequestMapping
public class MsLoginController {

    @Autowired
    private MsMetaService metaService;

    @GetMapping(value = "/login")
    public String login(ModelMap modelMap) {
        Map<String, String> siteInfo = metaService.siteInfo();
        modelMap.put("siteInfo", siteInfo);
        return "login";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/ms")
    public String ms(ModelMap modelMap) {
        return "ms";
    }

}
