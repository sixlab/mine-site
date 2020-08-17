<#include "/ms/frame.ftl"/>
<@AdminFrame title="任务记录">

<div id="content">
    <table class="table table-striped data-table">
        <thead class="thead-dark">
        <tr>
            <th scope="col" width="10%">序号</th>
            <th scope="col" width="20%">名称</th>
            <th scope="col" width="35%">消息</th>
            <th scope="col" width="10%">状态</th>
            <th scope="col" width="25%">时间</th>
        </tr>
        </thead>
        <tbody>
        <#list dataList as item>
            <tr>
                <td>${item_index+1}</td>
                <td>${item.jobName!""}</td>
                <td>${item.msg!""}</td>
                <td>${item.status!""}</td>
                <td>${item.createTime?string("yyyy-MM-dd HH:mm:ss")!''}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

</@AdminFrame>
