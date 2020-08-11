<link rel="stylesheet" href="/static/plugins/bootstrap/css/bootstrap.min.css">
<script src="/static/plugins/jquery/jquery.min.js"></script>

<style>
    #loginFrm{
        width: 400px;
        margin: 100px auto;
    }
    #loginSubmit{
        width: 400px;
    }
</style>

<form id="loginFrm">
    <div class="text-center mb-4">
        <img class="mb-4" src="${(siteInfo.logo)!'/static/images/ms.png'}" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">登录</h1>
    </div>
    <div class="form-label-group">
        <input type="email" class="form-control" placeholder="用户名" id="username" name="username">
        <label for="username"></label>
    </div>
    <div class="form-label-group">
        <input type="password" class="form-control" placeholder="密码" id="password" name="password">
        <label for="password"></label>
    </div>
    <button type="submit" class="btn btn-primary" id="loginSubmit">登录</button>
</form>

<div class="modal fade" id="MsgModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">提示</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="ErrMsg">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $("#loginSubmit").click(function () {
            $.ajax({
                url:"/login",
                method:"post",
                dataType:"json",
                data:$("#loginFrm").serialize(),
                success:function(data){
                    if(data.success){
                        $('#ErrMsg').html("成功，UUID为：<br/>"+data.data);
                        $('#MsgModal').modal("show");
                    }else {
                        $('#ErrMsg').text(data.message);
                        $('#MsgModal').modal("show");
                    }
                },
                error:function(err){
                    $('#ErrMsg').text("错误");
                    $('#MsgModal').modal("show");
                }
            });

            return false;
        });
    });
</script>
<script src="/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
