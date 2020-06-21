package cn.sixlab.mine.site.service.service;

import cn.sixlab.mine.site.common.utils.Ctx;
import cn.sixlab.mine.site.common.utils.Err;
import cn.sixlab.mine.site.common.vo.MineException;
import cn.sixlab.mine.site.data.mapper.VodGroupMapper;
import cn.sixlab.mine.site.data.mapper.VodPlayerMapper;
import cn.sixlab.mine.site.data.mapper.VodSiteMapper;
import cn.sixlab.mine.site.data.models.VodGroup;
import cn.sixlab.mine.site.data.models.VodPlayer;
import cn.sixlab.mine.site.data.models.VodSite;
import cn.sixlab.mine.site.service.api.MovieApiService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private VodSiteMapper siteMapper;
    @Autowired
    private VodGroupMapper groupMapper;
    @Autowired
    private VodPlayerMapper playerMapper;

    public void init() {
        List<VodSite> siteList = siteMapper.selectInit();

        if (CollectionUtils.isNotEmpty(siteList)) {
            for (VodSite site : siteList) {
                MovieApiService service = Ctx.getBean(MovieApiService.class, "movie_" + site.getSiteCode());
                service.init();
            }
        }
    }

    public void addSite(VodSite site) {
        VodSite old = siteMapper.selectByCode(site.getSiteCode());
        if (null == old) {
            site.setStatus(1);
            site.setPage(0);
            site.setBeginTime(new Date());
            site.setEndTime(new Date());
            site.setCreateTime(new Date());
            siteMapper.insert(site);
        }else{
            throw new MineException(Err.ERR_EXIST, "已存在");
        }
    }

    public void updateSite(VodSite site) {
        VodSite toUpdate = new VodSite();
        toUpdate.setId(site.getId());
        toUpdate.setSiteName(site.getSiteName());
        toUpdate.setSiteCode(site.getSiteCode());
        toUpdate.setSiteType(site.getSiteType());
        toUpdate.setSiteUrl(site.getSiteUrl());
        toUpdate.setGroupRelate(site.getGroupRelate());

        siteMapper.updateByPrimaryKeySelective(toUpdate);
    }

    public void addPlayer(VodPlayer player) {
        VodPlayer old = playerMapper.selectByCode(player.getPlayerCode());
        if (null == old) {
            player.setStatus(1);
            player.setCreateTime(new Date());
            playerMapper.insert(player);
        } else {
            throw new MineException(Err.ERR_EXIST, "已存在");
        }
    }

    public void updatePlayer(VodPlayer player) {
        VodPlayer toUpdate = new VodPlayer();
        toUpdate.setId(player.getId());
        toUpdate.setSiteCode(player.getSiteCode());
        toUpdate.setPlayerCode(player.getPlayerCode());
        toUpdate.setPlayerName(player.getPlayerName());

        playerMapper.updateByPrimaryKeySelective(toUpdate);
    }

    public void addGroup(VodGroup group) {
        VodGroup old = groupMapper.selectByName(group.getGroupName());
        if (null == old) {
            group.setStatus(1);
            group.setCreateTime(new Date());
            groupMapper.insert(group);
        } else {
            throw new MineException(Err.ERR_EXIST, "已存在");
        }
    }

    public void updateGroup(VodGroup group) {
        VodGroup toUpdate = new VodGroup();
        toUpdate.setId(group.getId());
        toUpdate.setGroupName(group.getGroupName());

        groupMapper.updateByPrimaryKeySelective(toUpdate);
    }
}
