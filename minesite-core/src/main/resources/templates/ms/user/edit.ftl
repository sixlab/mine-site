<#include "/ms/frame.ftl"/>
<#assign title = (job??)?string('修改','新增')+'用户' />
<@AdminFrame title="${title}">

<div id="content">
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
</div>

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

</@AdminFrame>
