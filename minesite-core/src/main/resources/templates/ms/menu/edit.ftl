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
        <label for="menuPosition">位置</label>
        <input type="text" class="form-control" name="menuPosition" id="menuPosition" value="${(data.menuPosition)!''}">
    </div>
    <div class="form-group">
        <label for="menuLevel">等级</label>
        <input type="text" class="form-control" name="menuLevel" id="menuLevel" value="${(data.menuLevel)!''}">
    </div>
    <div class="form-group">
        <label for="menuName">菜单名</label>
        <input type="text" class="form-control" name="menuName" id="menuName" value="${(data.menuName)!''}">
    </div>
    <div class="form-group">
        <label for="menuPath">路径</label>
        <input type="text" class="form-control" name="menuPath" id="menuPath" value="${(data.menuPath)!''}">
    </div>
    <div class="form-group">
        <label for="menuOrder">顺序</label>
        <input type="text" class="form-control" name="menuOrder" id="menuOrder" value="${(data.menuOrder)!''}">
    </div>
    <div class="form-group">
        <label for="menuSummary">备注</label>
        <input type="text" class="form-control" name="menuSummary" id="menuSummary" value="${(data.menuSummary)!''}">
    </div>
    <div class="form-group" id="btnGroup">
        <a class="btn btn-secondary" href="/ms/menu/list">返回</a>
        <button type="submit" class="btn btn-primary" id="editSubmit">提交</button>
    </div>
</form>

<script type="text/javascript">
    $(function(){
        $("#editSubmit").click(function () {
            $.ajax({
                url:"/ms/menu/submit",
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
