// package cn.sixlab.minesite.config;
//
//
// import cn.sixlab.minesite.models.MsMenu;
// import cn.sixlab.minesite.service.MenuService;
// import cn.sixlab.minesite.utils.UserUtils;
// import freemarker.core.Environment;
// import freemarker.ext.beans.BeansWrapperBuilder;
// import freemarker.template.*;
// import org.apache.commons.collections4.MapUtils;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
//
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;
//
// @Component
// public class FrameInfoDirective implements TemplateDirectiveModel {
//
//     @Autowired
//     private MenuService service;
//
//     @Override
//     public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
//         Integer userId = UserUtils.loginedUserId();
//         String uri = MapUtils.getString(params, "uri", "/");
//
//         List<MsMenu> level1 = service.loadTopMenu(userId);
//
//         Integer topId = null;
//         for (MsMenu msMenu : level1) {
//             if(uri.startsWith(msMenu.getMenuPath())){
//                 topId = msMenu.getId();
//                 break;
//             }
//         }
//
//         List<MsMenu> level2;
//         if (topId == null) {
//             level2 = new ArrayList<>();
//             topId = 0;
//         } else {
//             level2 = service.loadUserSubMenu(userId, topId);
//         }
//
//         List<Integer> folderIds = new ArrayList<>();
//         for (MsMenu msMenu : level2) {
//             if (!msMenu.getIsLeaf()) {
//                 folderIds.add(msMenu.getId());
//             }
//         }
//
//         List<MsMenu> level3 = service.loadUserSubMenu(userId, folderIds.toArray(new Integer[]{}));
//
//         List messages = new ArrayList<>();
//
//         env.setVariable("topId", new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build().wrap(topId));
//
//         env.setVariable("level1", new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build().wrap(level1));
//         env.setVariable("level2", new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build().wrap(level2));
//         env.setVariable("level3", new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build().wrap(level3));
//
//         env.setVariable("messages", new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build().wrap(messages));
//
//         if (body != null) {
//             body.render(env.getOut());
//         }
//     }
// }
