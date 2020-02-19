package cn.sixlab.mine.site.data.dao;

import cn.sixlab.mine.site.data.models.MsAuthResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsAuthResourceDao extends JpaRepository<MsAuthResource, Integer> {

    void deleteAllByAuthId(Integer authId);

    void deleteAllByResourceIdAndType(Integer resourceId, String type);

}
