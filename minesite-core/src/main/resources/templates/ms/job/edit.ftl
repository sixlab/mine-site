<link rel="stylesheet" href="/static/plugins/bootstrap/css/bootstrap.min.css">
<script src="/static/plugins/jquery/jquery.min.js"></script>
<style>
    #editFrm{
        width: 1000px;
        margin: 30px auto;
    }

    #btnGroup{
        width: 410px;
        margin: 0 auto;
    }

    #btnGroup .btn{
        width: 200px;
    }
</style>
<form id="editFrm">
    <input type="hidden" name="id" id="id" value="${(job.id)!''}">
    <div class="form-group">
        <label for="jobCode">编号</label>
        <input type="text" class="form-control" name="jobCode" id="jobCode" value="${(job.jobCode)!''}">
    </div>
    <div class="form-group">
        <label for="jobClz">类</label>
        <input type="text" class="form-control" name="jobClz" id="jobClz" value="${(job.jobClz)!''}">
    </div>
    <div class="form-group">
        <label for="jobMethod">方法</label>
        <input type="text" class="form-control" name="jobMethod" id="jobMethod" value="${(job.jobMethod)!''}">
    </div>
    <div class="form-group">
        <label for="jobName">名称</label>
        <input type="text" class="form-control" name="jobName" id="jobName" value="${(job.jobName)!''}">
    </div>
    <div class="form-group">
        <label for="status">状态</label>
        <input type="text" class="form-control" name="status" id="status" value="${(job.status)!''}">
    </div>
    <div class="form-group" id="btnGroup">
        <a class="btn btn-secondary" href="/ms/job/list">返回</a>
        <button type="submit" class="btn btn-primary" id="editSubmit">提交</button>
    </div>
</form>

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
<script src="/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
