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
    <input type="hidden" name="id" id="id" value="${(meta.id)!''}">
    <div class="form-group">
        <label for="fkId">外键ID</label>
        <input type="text" class="form-control" name="fkId" id="fkId" value="${(meta.fkId)!''}">
    </div>
    <div class="form-group">
        <label for="metaGroup">组</label>
        <input type="text" class="form-control" name="metaGroup" id="metaGroup" value="${(meta.metaGroup)!''}">
    </div>
    <div class="form-group">
        <label for="metaKey">键</label>
        <input type="text" class="form-control" name="metaKey" id="metaKey" value="${(meta.metaKey)!''}">
    </div>
    <div class="form-group">
        <label for="metaVal">值</label>
        <input type="text" class="form-control" name="metaVal" id="metaVal" value="${(meta.metaVal)!''}">
    </div>
    <div class="form-group">
        <label for="remark">备注</label>
        <input type="text" class="form-control" name="remark" id="remark" value="${(meta.remark)!''}">
    </div>
    <div class="form-group" id="btnGroup">
        <a class="btn btn-secondary" href="/meta/list">返回</a>
        <button type="submit" class="btn btn-primary" id="editSubmit">提交</button>
    </div>
</form>

<script type="text/javascript">
    $(function(){
        $("#editSubmit").click(function () {
            $.ajax({
                url:"/meta/submit",
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
