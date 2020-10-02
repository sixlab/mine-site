package tech.minesoft.mine.site.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.minesoft.mine.site.core.mapper.MsDataMapper;
import tech.minesoft.mine.site.core.models.MsData;
import tech.minesoft.mine.site.core.utils.Err;
import tech.minesoft.mine.site.core.utils.UserUtils;
import tech.minesoft.mine.site.core.vo.MineException;

import java.util.Date;
import java.util.List;

@Service
public class MsDataService {
    @Autowired
    private MsDataMapper dataMapper;
    @Autowired
    private UserUtils userUtils;

    public List<MsData> loadAllData(String dataType) {
        return dataMapper.selectList(userUtils.loginedUserId(), dataType, "");
    }

    public List<MsData> loadData(String dataType, String param, Integer pageNum, Integer pageSize) {
        // PageHelper.startPage(pageNum, pageSize);

        List<MsData> infoList = dataMapper.selectList(userUtils.loginedUserId(), dataType, param);

        // return new PageInfo<>(infoList);
        return infoList;
    }

    public void add(MsData data) {
        Integer userId = userUtils.loginedUserId();

        MsData old = dataMapper.selectOne(userId, data.getDataType(), data.getDataId(), null);

        if(null == old){
            // 新增
            data.setId(null);
            data.setUserId(userId);
            data.setCreateTime(new Date());
            dataMapper.insert(data);
        }else{
            throw MineException.error(Err.ERR_EXIST, "data.exist");
        }
    }

    public void modify(MsData data) {
        Integer userId = userUtils.loginedUserId();

        MsData old;
        if(null != data.getId()){
            // 根据 ID 修改
            old = dataMapper.selectByPrimaryKey(data.getId());

            if(null==old){
                throw MineException.error(Err.ERR_NOT_EXIST, "data.not.exist");
            }

            if( !userId.equals(old.getUserId()) ){
                throw MineException.error(Err.AUTH_NONE, "auth.none");
            }
        }else{
            // 根据数据标识修改
            old = dataMapper.selectOne(userId, data.getDataType(), data.getDataId(), null);

            if(null==old){
                throw MineException.error(Err.ERR_NOT_EXIST, "data.not.exist");
            }
        }

        old.setDataType(data.getDataType());
        old.setDataId(data.getDataId());
        old.setData(data.getData());

        dataMapper.updateByPrimaryKeyWithBLOBs(data);
    }

    public MsData select(Integer id) {
        return dataMapper.selectByPrimaryKey(id);
    }

    public MsData select(String dataType, String dataId, String param) {
        return dataMapper.selectOne(userUtils.loginedUserId(), dataType, dataId, param);
    }

    public void delete(Integer id) {
        dataMapper.deleteByPrimaryKey(id);
    }

    public void delete(String dataType, String dataId, String param) {
        dataMapper.delete(userUtils.loginedUserId(), dataType, dataId, param);
    }
}
