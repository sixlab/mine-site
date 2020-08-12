<link rel="stylesheet" href="/static/plugins/bootstrap/css/bootstrap.min.css">
<script src="/static/plugins/jquery/jquery.min.js"></script>
<style>
    #content{
        width: 1000px;
        margin: 30px auto;
    }

    table{
        margin-top: 20px;
    }
</style>
<div id="content">
    <a class="btn btn-outline-success my-2 my-sm-0" href="/ms/user/add">添加</a>

    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col" width="25%">用户名</th>
            <th scope="col" width="25%">昵称</th>
            <th scope="col" width="20%">角色</th>
            <th scope="col" width="10%">状态</th>
            <th scope="col" width="20%">操作</th>
        </tr>
        </thead>
        <tbody>
        <#list dataList as item>
            <tr>
                <td>${item.username!""}</td>
                <td>${item.nickname!""}</td>
                <td>${item.role!""}</td>
                <td>${item.status!""}</td>
                <td>
                    <button data-id="${item.id}" class="btn btn-outline-danger my-2 my-sm-0 delete">删除</button>
                    <a target="_blank" class="btn btn-outline-info my-2 my-sm-0" href="/ms/user/edit/${item.id}">修改</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    $(function(){
        $(".delete").click(function () {
            $.ajax({
                url:"/ms/user/delete/"+$(this).data("id"),
                method:"post",
                dataType:"json",
                success:function(data){
                    alert(JSON.stringify(data));
                    location.reload();
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
