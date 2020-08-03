package cn.sixlab.mine.site.core.service;

import cn.sixlab.mine.site.core.utils.Ctx;
import cn.sixlab.mine.site.core.utils.Err;
import cn.sixlab.mine.site.core.vo.MineException;
import cn.sixlab.mine.site.core.mapper.*;
import cn.sixlab.mine.site.core.models.*;
import cn.sixlab.mine.site.core.api.MovieApiService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private VodSiteMapper siteMapper;
    @Autowired
    private VodInfoMapper infoMapper;
    @Autowired
    private VodInfoUrlsMapper urlsMapper;
    @Autowired
    private VodGroupMapper groupMapper;
    @Autowired
    private VodPlayerMapper playerMapper;
    @Autowired
    private VodUserStarMapper userStarMapper;

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

    public void addStar(Integer userId, Integer infoId) {
        VodInfo info = infoMapper.selectByPrimaryKey(infoId);

        if (null == info) {
            throw new MineException(Err.ERR_NOT_EXIST, "数据不存在");
        }

        VodUserStar userStar = userStarMapper.selectExist(userId, infoId);
        if (null == userStar) {
            userStar = new VodUserStar();
            userStar.setUserId(userId);
            userStar.setInfoId(infoId);
            userStar.setVodName(info.getVodName());
            userStar.setVodPic(info.getVodPic());
            userStar.setCreateTime(new Date());
            userStarMapper.insert(userStar);
        } else {
            throw new MineException(Err.ERR_EXIST, "已存在");
        }
    }

    public void delStar(Integer userId, Integer infoId) {
        userStarMapper.delExist(userId, infoId);
    }

    public List<VodUserStar> listStar(Integer userId) {
        return userStarMapper.selectByUserId(userId);
    }

    public Map<String,Object> info(Integer infoId) {
        VodInfo info = infoMapper.selectByPrimaryKey(infoId);

        if (null == info) {
            throw new MineException(Err.ERR_NOT_EXIST, "数据不存在");
        }

        List<VodInfoUrls> urlsList = urlsMapper.selectByInfoId(infoId);
        List<VodPlayer> playerList = playerMapper.selectEnable();

        Map<String, Object> data = new HashMap<>();
        data.put("info", info);
        data.put("urlList", urlsList);
        data.put("playerList", playerList);

        return data;
    }
}
