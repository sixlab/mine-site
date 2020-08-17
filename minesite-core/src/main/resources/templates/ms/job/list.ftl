<#include "/ms/frame.ftl"/>
<@AdminFrame title="${currentTitle}任务列表">

<div id="content">
    <div class="data-op">
        <a class="btn btn-outline-success my-2 my-sm-0" href="/ms/job/add">添加</a>
    </div>

    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col" width="10%">序号</th>
            <th scope="col" width="15%">组</th>
            <th scope="col" width="20%">名称</th>
            <th scope="col" width="20%">cron</th>
            <th scope="col" width="15%">状态</th>
            <th scope="col" width="20%">操作</th>
        </tr>
        </thead>
        <tbody>
        <#list jobs as item>
            <tr>
                <td>${item_index+1}</td>
                <td>${item.groupName!""}</td>
                <td>${item.jobName!""}</td>
                <td>${item.jobTime!""}</td>
                <td>${item.jobStatus!""}</td>
                <td>
                    <button data-job="${item.jobName!""}" data-group="${item.groupName!""}" class="btn btn-outline-danger my-2 my-sm-0 delete">删除</button>
                    <button data-job="${item.jobName!""}" data-group="${item.groupName!""}" class="btn btn-outline-danger my-2 my-sm-0 run-once">运行</button>
                    <a target="_blank" class="btn btn-outline-info my-2 my-sm-0" href="/ms/job/edit/${item.jobName!""}/${item.groupName!""}">修改</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    $(function(){
        $(".delete").click(function () {
            var that = $(this);
            $.ajax({
                url:"/ms/job/delete",
                method:"post",
                dataType:"json",
                data:{
                    jobName:that.data("job"),
                    groupName:that.data("group"),
                },
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


        $(".run-once").click(function () {
            var that = $(this);
            $.ajax({
                url:"/ms/job/run",
                method:"post",
                dataType:"json",
                data:{
                    jobName:that.data("job"),
                    groupName:that.data("group"),
                },
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
