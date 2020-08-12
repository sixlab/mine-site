package tech.minesoft.mine.site.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.minesoft.mine.site.core.mapper.MsMenuMapper;
import tech.minesoft.mine.site.core.models.MsMenu;

import java.util.Date;
import java.util.List;

@Service
public class MsMenuService {
    @Autowired
    private MsMenuMapper menuMapper;

    public List<MsMenu> loadAll() {
        return menuMapper.selectAll();
    }

    public MsMenu select(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    public void add(MsMenu meta) {
        meta.setCreateTime(new Date());
        menuMapper.insert(meta);
    }

    public void modify(MsMenu meta) {
        MsMenu old = menuMapper.selectByPrimaryKey(meta.getId());

        old.setMenuLevel(meta.getMenuLevel());
        old.setMenuName(meta.getMenuName());
        old.setMenuOrder(meta.getMenuOrder());
        old.setMenuPath(meta.getMenuPath());
        old.setMenuPosition(meta.getMenuPosition());
        old.setMenuSummary(meta.getMenuSummary());

        menuMapper.updateByPrimaryKey(old);
    }

    public void delete(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    public List<MsMenu> loadPostion(String position) {
        return menuMapper.selectPositionMenu(position);
    }
}
