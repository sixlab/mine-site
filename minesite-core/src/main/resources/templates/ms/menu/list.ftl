<#include "/ms/frame.ftl"/>
<@AdminFrame title="菜单列表">

<div id="content">
    <div class="data-op">
        <a class="btn btn-outline-success my-2 my-sm-0" href="/ms/menu/add">添加</a>
    </div>

    <table class="table table-striped data-table">
        <thead class="thead-dark">
        <tr>
            <th scope="col" width="7%">级别</th>
            <th scope="col" width="8%">顺序</th>
            <th scope="col" width="10%">位置</th>
            <th scope="col" width="15%">名称</th>
            <th scope="col" width="25%">路径</th>
            <th scope="col" width="20%">备注</th>
            <th scope="col" width="15%">操作</th>
        </tr>
        </thead>
        <tbody>
        <#list dataList as item>
            <tr>
                <td>${item.menuLevel!""}</td>
                <td>${item.menuOrder!""}</td>
                <td>${item.menuPosition!""}</td>
                <td>${item.menuName!""}</td>
                <td>${item.menuPath!""}</td>
                <td>${item.menuSummary!""}</td>
                <td>
                    <button data-id="${item.id}" class="btn btn-outline-danger my-2 my-sm-0 delete">删除</button>
                    <a target="_blank" class="btn btn-outline-info my-2 my-sm-0" href="/ms/menu/edit/${item.id}">修改</a>
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
                url:"/ms/menu/delete/"+$(this).data("id"),
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

</@AdminFrame>
