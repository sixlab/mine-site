<#-- @ftlvariable name="topId" type="java.lang.Integer" -->
<#-- @ftlvariable name="level1" type="java.util.List<cn.sixlab.mine.site.data.models.MsMenu>" -->
<#-- @ftlvariable name="level2" type="java.util.List<cn.sixlab.mine.site.data.models.MsMenu>" -->
<#-- @ftlvariable name="level3" type="java.util.List<cn.sixlab.mine.site.data.models.MsMenu>" -->

<#macro FrameHeader title=''>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <#if title??><title>${title} - Minesite</title><#else><title>Minesite</title></#if>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/api/static/plugins/fontawesome-free/css/all.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="../../plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/api/static/css/adminlte.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
</#macro>


<#macro FrameTop>
<nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
            </li>

            <#-- TODO 顶部菜单 <#if path?starts_with("/api/test/")> active</#if> -->
            <li class="nav-item d-none d-sm-inline-block">
                <a href="/" class="nav-link">首页</a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="/api/" class="nav-link">后台首页</a>
            </li>
            <li class="nav-item d-none d-sm-inline-block<#if path?starts_with("/api/test/")> active</#if>">
                <a href="/api/i/" class="nav-link">个人中心</a>
            </li>
            <li class="nav-item d-none d-sm-inline-block<#if path?starts_with("/api/test/")> active</#if>">
                <a href="/api/test/" class="nav-link">后台首页</a>
            </li>
        </ul>

        <!-- SEARCH FORM -->
        <form class="form-inline ml-3">
            <div class="input-group input-group-sm">
                <input class="form-control form-control-navbar" type="search" placeholder="Search"
                       aria-label="Search">
                <div class="input-group-append">
                    <button class="btn btn-navbar" type="submit">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
            </div>
        </form>

        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
            <!-- Messages Dropdown Menu -->
            <li class="nav-item dropdown">
                <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="far fa-comments"></i>
                    <span class="badge badge-danger navbar-badge">3</span>
                </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <#list messages as item>
                        <a href="#" class="dropdown-item">
                            <!-- Message Start -->
                            <div class="media">
                                <img src="/api/static/img/user1-128x128.jpg" alt="User Avatar"
                                     class="img-size-50 mr-3 img-circle">
                                <div class="media-body">
                                    <h3 class="dropdown-item-title">
                                        text-danger/muted/warning
                                        <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>
                                    </h3>
                                    <p class="text-sm">Call me whenever you can...</p>
                                    <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                                </div>
                            </div>
                            <!-- Message End -->
                        </a>
                        <div class="dropdown-divider"></div>
                    </#list>

                    <a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
                </div>
            </li>
            <!-- Notifications Dropdown Menu -->
            <li class="nav-item dropdown">
                <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="far fa-bell"></i>
                    <span class="badge badge-warning navbar-badge">15</span>
                </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <span class="dropdown-item dropdown-header">15 Notifications</span>
                    <div class="dropdown-divider"></div>

                    <#list messages as item>
                        <a href="#" class="dropdown-item">
                            <i class="fas fa-envelope mr-2"></i> 4 new messages
                            <span class="float-right text-muted text-sm">3 mins</span>
                        </a>
                        <div class="dropdown-divider"></div>
                    </#list>

                    <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#">
                    <i class="fas fa-th-large"></i>
                </a>
            </li>
        </ul>
    </nav>
</#macro>


<#macro FrameLeft>
<div class="sidebar">
        <!-- Sidebar user (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="/api/static/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
            </div>
            <#--        <div class="info">-->
            <#--            <a href="#" class="d-block">Alexander Pierce</a>-->
            <#--        </div>-->
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar nav-child-indent nav-compact flex-column" data-widget="treeview" role="menu"
                data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->

                <#-- TODO 侧边菜单 <#if path = "/api/test/"> active</#if> -->
                <@MenuItem menuName="测试0" menuUri="/api/test/" />
                <@MenuItem menuName="测试abc" menuUri="/api/test/abc" />
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
</#macro>

<#macro MenuItem menuUri="" menuName="">
    <li class="nav-item">
        <a href="${menuUri}" class="nav-link <#if path = menuUri> active</#if>">
            <#--fa-th-->
            <i class="nav-icon fas fa-th"></i>
            <p>
                ${menuName}
                <#--<span class="right badge badge-danger">New</span>-->
            </p>
        </a>
    </li>
</#macro>

<#macro subMenu>
    <li class="nav-item has-treeview">
        <a href="#" class="nav-link">
            <i class="nav-icon fas fa-tachometer-alt"></i>
            <p>
                ${parent.menuName}
                <i class="right fas fa-angle-left"></i>
            </p>
        </a>
        <ul class="nav nav-treeview">
            <!-- 叶子 -->
            <li class="nav-item">
                <a href="${parent.menuPath}" class="nav-link">
                    <#--fa-th-->
                    <i class="nav-icon fas ${parent.menuIcon}"></i>
                    <p>
                        ${parent.menuName}
                        <#--<span class="right badge badge-danger">New</span>-->
                    </p>
                </a>
            </li>
        </ul>
    </li>
</#macro>


<#macro FrameMenus>
<@FrameInfo uri="${springMacroRequestContext.requestUri}">
    <!-- Navbar -->
    <@FrameTop />
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="/api/" class="brand-link elevation-4">
            <img src="/api/static/images/logo.png"
                 alt="AdminLTE Logo"
                 class="brand-image img-circle elevation-3"
                 style="opacity: .8">
            <span class="brand-text font-weight-light">Minesite</span>
        </a>

        <!-- Sidebar -->
        <@FrameLeft />
        <!-- /.sidebar -->
    </aside>
</@FrameInfo>
</#macro>


<#macro FrameBody title=''>
<!DOCTYPE HTML>
<html lang='zh-CN'>
<@FrameHeader title='${title}'/>
<body class="hold-transition sidebar-mini layout-navbar-fixed">
    <!-- Site wrapper -->
    <div class="wrapper">
        <@FrameMenus />

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
<#--            <section class="content-header">-->
<#--                <div class="container-fluid">-->
<#--                    <div class="row mb-2">-->
<#--                        <div class="col-sm-6">-->
<#--                            <h1>Fixed Navbar Layout</h1>-->
<#--                        </div>-->
<#--                        <div class="col-sm-6">-->
<#--                            <ol class="breadcrumb float-sm-right">-->
<#--                                <li class="breadcrumb-item"><a href="#">Home</a></li>-->
<#--                                <li class="breadcrumb-item"><a href="#">Layout</a></li>-->
<#--                                <li class="breadcrumb-item active">Fixed Navbar Layout</li>-->
<#--                            </ol>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div><!-- /.container-fluid &ndash;&gt;-->
<#--            </section>-->

            <!-- Main content -->
            <section class="content">
                <div class="container-fluid">
                    <#nested />
                </div>
            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <footer class="main-footer">
            <div class="float-right d-none d-sm-block">
                <b>Version</b> 3.0.2
            </div>
            <strong>Copyright &copy; 2020 <a href="https://sixlab.cn">sixlab</a>.</strong> All rights
            reserved.
        </footer>

        <!-- Control Sidebar -->
        <aside class="control-sidebar control-sidebar-dark">
            <!-- Control sidebar content goes here -->
        </aside>
        <!-- /.control-sidebar -->
    </div>
    <!-- ./wrapper -->

    <!-- jQuery -->
    <script src="/api/static/plugins/jquery/jquery.min.js"></script>
    <!-- Bootstrap 4 -->
    <script src="/api/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- overlayScrollbars -->
    <script src="../../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
    <!-- AdminLTE App -->
    <script src="/api/static/js/adminlte.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="/api/static/js/demo.js"></script>
</body>
</html>
</#macro>

