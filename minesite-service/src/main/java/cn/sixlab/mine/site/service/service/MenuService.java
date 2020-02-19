package cn.sixlab.mine.site.service.service;

import cn.sixlab.mine.site.data.dao.MsMenuDao;
import cn.sixlab.mine.site.data.dao.MsAuthResourceDao;
import cn.sixlab.mine.site.data.models.MsMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MsMenuDao menuDao;

    @Autowired
    private MsAuthResourceDao roleMenuDao;

    public Page<MsMenu> loadUserMenu(Integer userId, int level) {
        return menuDao.queryMsMenu(null, level, PageRequest.of(0, 3));
    }

    public MsMenu submitMenu(MsMenu menu) {
        if (null == menu.getId()) {
            menu.setStatus(1);
            menu.setCreateTime(new Date());
        }

        return menuDao.save(menu);
    }

    public void delMenu(Integer menuId) {
        Optional<MsMenu> byId = menuDao.findById(menuId);

        if (byId.isPresent()) {
            menuDao.deleteById(menuId);
            roleMenuDao.deleteAllByResourceIdAndType(byId.get().getId(), "menu");
        }

    }

    public void changeMenu(Integer menuId) {
        Optional<MsMenu> byId = menuDao.findById(menuId);

        if (byId.isPresent()) {
            MsMenu MsMenu = byId.get();
            if (0 == MsMenu.getStatus()) {
                MsMenu.setStatus(1);
            } else {
                MsMenu.setStatus(0);
            }

            menuDao.save(MsMenu);
        }
    }

    public List<MsMenu> loadTopMenu(Integer userId) {
        return menuDao.findActiveUserMenus(userId, 1);
    }

    public List<MsMenu> loadUserSubMenu(Integer userId, Integer... folderIds) {
        return menuDao.findActiveUserSubMenus(userId, folderIds);
    }
}
