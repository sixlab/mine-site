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
    <input type="hidden" name="id" id="id" value="${(data.id)!''}">
    <div class="form-group">
        <label for="username">位置</label>
        <input type="text" class="form-control" name="username" id="username" value="${(data.username)!''}">
    </div>
    <div class="form-group">
        <label for="nickname">等级</label>
        <input type="text" class="form-control" name="nickname" id="nickname" value="${(data.nickname)!''}">
    </div>
    <div class="form-group">
        <label for="password">菜单名</label>
        <input type="text" class="form-control" name="password" id="password" value="">
    </div>
    <div class="form-group">
        <label for="role">路径</label>
        <input type="text" class="form-control" name="role" id="role" value="${(data.role)!''}">
    </div>
    <div class="form-group">
        <label for="status">顺序</label>
        <input type="text" class="form-control" name="status" id="status" value="${(data.status)!''}">
    </div>
    <div class="form-group" id="btnGroup">
        <a class="btn btn-secondary" href="/ms/user/list">返回</a>
        <button type="submit" class="btn btn-primary" id="editSubmit">提交</button>
    </div>
</form>

<script type="text/javascript">
    $(function(){
        $("#editSubmit").click(function () {
            $.ajax({
                url:"/ms/user/submit",
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
