<#include "/ms/frame.ftl"/>
<#assign title = (job??)?string('修改','新增')+'任务' />
<@AdminFrame title="${title}">

<div id="content">
    <form id="editFrm">
        <div class="form-group">
            <label for="jobName">名称</label>
            <input type="text" class="form-control" name="jobName" id="jobName" value="${(job.jobName)!''}">
        </div>
        <div class="form-group">
            <label for="groupName">组</label>
            <input type="text" class="form-control" name="groupName" id="groupName" value="${(job.groupName)!''}">
        </div>
        <div class="form-group">
            <label for="jobData">编号</label>
            <input type="text" class="form-control" name="jobData" id="jobData" value="${(job.jobData)!''}">
        </div>
        <div class="form-group">
            <label for="jobClass">类</label>
            <input type="text" class="form-control" name="jobClass" id="jobClass" value="${(job.jobClass)!''}">
        </div>
        <div class="form-group">
            <label for="jobTime">方法</label>
            <input type="text" class="form-control" name="jobTime" id="jobTime" value="${(job.jobTime)!''}">
        </div>
        <div class="form-group" id="btnGroup">
            <a class="btn btn-secondary" href="/ms/job/list">返回</a>
            <button type="submit" class="btn btn-primary" id="editSubmit">提交</button>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(function(){
        $("#editSubmit").click(function () {
            $.ajax({
                url:"/ms/job/submit",
                method:"post",
                dataType:"json",
                data:$("#editFrm").serialize(),
                success:function(data){
                    alert(JSON.stringify(data));
                },
                error:function(err){
                    alert("err");
                }
            });

            return false;
        });
    });
</script>

</@AdminFrame>
