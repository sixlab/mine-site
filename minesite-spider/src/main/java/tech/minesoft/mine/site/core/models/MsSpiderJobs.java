package tech.minesoft.mine.site.core.models;

import java.io.Serializable;
import java.util.Date;

public class MsSpiderJobs implements Serializable {
    private Integer id;

    private Integer userId;

    private String jobName;

    private String jobCron;

    private String jobStatus;

    private String proxyType;

    private String uaType;

    private String threadType;

    private Date createTime;

    private String urlCode;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobCron() {
        return jobCron;
    }

    public void setJobCron(String jobCron) {
        this.jobCron = jobCron == null ? null : jobCron.trim();
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus == null ? null : jobStatus.trim();
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType == null ? null : proxyType.trim();
    }

    public String getUaType() {
        return uaType;
    }

    public void setUaType(String uaType) {
        this.uaType = uaType == null ? null : uaType.trim();
    }

    public String getThreadType() {
        return threadType;
    }

    public void setThreadType(String threadType) {
        this.threadType = threadType == null ? null : threadType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUrlCode() {
        return urlCode;
    }

    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode == null ? null : urlCode.trim();
    }
}