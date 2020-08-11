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
    <table class="table table-striped">
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
                <td>${item_index}</td>
                <td>${item.jobName!""}</td>
                <td>${item.msg!""}</td>
                <td>${item.status!""}</td>
                <td>${item.createTime?string("yyyy-MM-dd HH:mm:ss")!''}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

<script src="/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
