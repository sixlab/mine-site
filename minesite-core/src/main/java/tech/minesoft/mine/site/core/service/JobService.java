package tech.minesoft.mine.site.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.minesoft.mine.site.core.api.Job;
import tech.minesoft.mine.site.core.mapper.MsJobMapper;
import tech.minesoft.mine.site.core.mapper.MsJobRecordMapper;
import tech.minesoft.mine.site.core.models.MsJob;
import tech.minesoft.mine.site.core.models.MsJobRecord;
import tech.minesoft.mine.site.core.utils.Ctx;

import java.util.Date;

@Service
public class JobService {

    @Autowired
    public MsJobMapper jobMapper;

    @Autowired
    public MsJobRecordMapper recordMapper;

    public void run(String jobClz) {
        MsJob msJob = jobMapper.selectByClz(jobClz);

        int status = 0;
        String msg = "";
        if (msJob != null && 1 == msJob.getStatus()) {
            try {
                Job job = Ctx.getBean(Job.class, jobClz);
                job.run();
                status = 1;
            }catch (Exception e){
                msg = e.getMessage();
            }

            msJob.setLastStatus(status);
            msJob.setLastTime(new Date());
            jobMapper.updateByPrimaryKey(msJob);

            MsJobRecord record = new MsJobRecord();
            record.setJobId(msJob.getId());
            record.setJobName(msJob.getJobName());
            record.setStatus(status);
            record.setMsg(msg);
            record.setCreateTime(new Date());
            recordMapper.insert(record);
        }
    }
}
