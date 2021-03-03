package tech.minesoft.mine.site.common.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.minesoft.mine.site.mysql.mapper.MsMetaMapper;
import tech.minesoft.mine.site.mysql.models.MsMeta;

import java.util.*;

@Service
public class MsMetaService {
    @Autowired
    private MsMetaMapper metaMapper;

    public Map<String, String> siteInfo() {
        Map<String,String> siteMap = new HashMap<>();

        List<MsMeta> metaList = metaMapper.selectGroup("six-siteInfo");
        for (MsMeta meta : metaList) {
            siteMap.put(meta.getMetaKey(), meta.getMetaVal());
        }

        if(StringUtils.isEmpty(siteMap.get("copyrightYear"))){
            siteMap.put("copyrightYear", Calendar.getInstance().get(Calendar.YEAR)+"");
        }

        return siteMap;
    }

    public List<MsMeta> loadAll() {
        return metaMapper.selectAll();
    }

    public MsMeta select(Integer id) {
        return metaMapper.selectByPrimaryKey(id);
    }

    public void add(MsMeta meta) {
        meta.setCreateTime(new Date());
        metaMapper.insert(meta);
    }

    public void modify(MsMeta meta) {
        MsMeta old = metaMapper.selectByPrimaryKey(meta.getId());

        old.setFkId(meta.getFkId());
        old.setMetaGroup(meta.getMetaGroup());
        old.setMetaKey(meta.getMetaKey());
        old.setMetaVal(meta.getMetaVal());
        old.setRemark(meta.getRemark());

        metaMapper.updateByPrimaryKeyWithBLOBs(old);
    }

    public void delete(Integer id) {
        metaMapper.deleteByPrimaryKey(id);
    }
}
