package cn.sixlab.mine.site.service.controller;

import cn.sixlab.mine.site.common.utils.UserUtils;
import cn.sixlab.mine.site.common.vo.ResultJson;
import cn.sixlab.mine.site.data.models.MsUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserUtils userUtils;

    @ResponseBody
    @RequestMapping("/info")
    public ResultJson info() {
        MsUser msUser = userUtils.loginedUser();
        return ResultJson.successData(msUser);
    }
}
