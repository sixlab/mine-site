package tech.minesoft.minesite.core.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tech.minesoft.minesite.core.mapper.MsMetaMapper;
import tech.minesoft.minesite.core.models.MsMeta;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class VersionProcess implements ApplicationContextAware {

    @Autowired
    private MsMetaMapper metaMapper;

    @Value("${minesite.version}")
    private Integer version;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            metaMapper.checkTable();

            MsMeta msMeta = metaMapper.selectOne("minesite", "version");
            if (msMeta == null) {
                // 内置初始化
                runSqlFile("minesite.sql");

                // 应用初始化
                runSqlFile("init.sql");

                msMeta = new MsMeta();
                msMeta.setMetaGroup("minesite");
                msMeta.setMetaKey("version");
                msMeta.setMetaVal(version+"");
                metaMapper.insert(msMeta);
            }else{
                String val = msMeta.getMetaVal();
                Integer oldVersion = Integer.valueOf(val);
                if(oldVersion < version){
                    for (int i = oldVersion; i <= version; i++) {
                        runSqlFile( i+".sql");

                        metaMapper.updateVersion(msMeta.getId(), i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error",e);
            System.exit(0);
        }
    }

    private void runSqlFile (String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource("sql" + File.separator + filename);
        File file = resource.getFile();
        String sql = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        metaMapper.runSql(sql);
    }
}
