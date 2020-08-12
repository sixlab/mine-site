<#include "/ms/frame.ftl"/>
<@AdminFrame title="系统参数列表">

<div id="content">
    <div class="data-op">
        <a class="btn btn-outline-success my-2 my-sm-0" href="/ms/meta/add">添加</a>
    </div>

    <table class="table table-striped data-table">
        <thead class="thead-dark">
        <tr>
            <th scope="col" width="8%">外ID</th>
            <th scope="col" width="15%">组</th>
            <th scope="col" width="15%">键</th>
            <th scope="col" width="20%">值</th>
            <th scope="col" width="27%">备注</th>
            <th scope="col" width="15%">操作</th>
        </tr>
        </thead>
        <tbody>
        <#list dataList as item>
            <tr>
                <td>${item.fkId!""}</td>
                <td>${item.metaGroup!""}</td>
                <td>${item.metaKey!""}</td>
                <td>${item.metaVal!""}</td>
                <td>${item.remark!""}</td>
                <td>
                    <button data-id="${item.id}" class="btn btn-outline-danger my-2 my-sm-0 delete">删除</button>
                    <a target="_blank" class="btn btn-outline-info my-2 my-sm-0" href="/ms/meta/edit/${item.id}">修改</a>
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
                url:"/ms/meta/delete/"+$(this).data("id"),
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
