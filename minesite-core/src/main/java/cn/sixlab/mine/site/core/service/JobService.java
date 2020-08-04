package cn.sixlab.mine.site.core.service;

import cn.sixlab.mine.site.core.api.Job;
import cn.sixlab.mine.site.core.mapper.MsJobMapper;
import cn.sixlab.mine.site.core.mapper.MsJobRecordMapper;
import cn.sixlab.mine.site.core.models.MsJob;
import cn.sixlab.mine.site.core.models.MsJobRecord;
import cn.sixlab.mine.site.core.utils.Ctx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
