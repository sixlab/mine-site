package tech.minesoft.minesite.core.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tech.minesoft.minesite.core.mapper.MsMetaMapper;
import tech.minesoft.minesite.core.models.MsMeta;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@Order(0)
public class VersionProcess implements ApplicationContextAware {

    @Autowired
    private MsMetaMapper metaMapper;

    @Value("${minesite.version.base}")
    private Integer baseVersion;

    @Value("${minesite.version.app}")
    private Integer appVersion;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            metaMapper.checkTable();
            // 框架
            processVersion("baseVersion","ms", baseVersion);

            // 应用
            processVersion("appVersion","", appVersion);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error",e);
            System.exit(0);
        }
    }

    private void processVersion(String versionKey,String prefix, Integer currentVersion) throws IOException {
        // 内置
        MsMeta msMeta = metaMapper.selectOne("minesite", versionKey);
        if(null==msMeta){
            runSqlFile(prefix+"init.sql");

            msMeta = new MsMeta();
            msMeta.setMetaGroup("minesite");
            msMeta.setMetaKey(versionKey);
            msMeta.setMetaVal(currentVersion +"");
            metaMapper.insert(msMeta);
        }else{
            Integer oldVersion = Integer.valueOf( msMeta.getMetaVal());
            if(oldVersion < currentVersion){
                for (int i = oldVersion; i <= currentVersion; i++) {
                    runSqlFile( prefix+i+".sql");

                    metaMapper.updateVal(msMeta.getId(), i);
                }
            }
        }
    }

    private void runSqlFile (String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource("sql" + File.separator + filename);
        File file = resource.getFile();
        String sql = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        metaMapper.runSql(sql);
    }
}
