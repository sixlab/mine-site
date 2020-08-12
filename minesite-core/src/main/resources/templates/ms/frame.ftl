<#macro AdminFrame title="">
<@SiteInfo>
<!DOCTYPE HTML>
<html lang='zh-CN'>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><#if title??>${title} ${siteInfo.titleSeparator!'-'} </#if>${siteInfo.siteName!''}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/static/plugins/bootstrap/css/bootstrap.min.css">
    <script src="/static/plugins/jquery/jquery.min.js"></script>
    <style>
        main{
            padding-left: 1.5rem!important;
            margin-top: 1.5rem!important;
        }
        #btnGroup{
            width: 410px;
            margin: 0 auto;
        }

        #btnGroup .btn{
            width: 200px;
        }

        .data-op{
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- Fixed navbar -->
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                <div class="sidebar-sticky pt-3">
                    <@MenuInfo position='admin'>
                    <ul class="nav flex-column">
                        <#assign totalNum = menuInfo?size />

                        <#list menuInfo as item>

                            <#assign nextIndex = (item_index+1) />
                            <#assign nextLevel = 0 />
                            <#assign hasSub = false />
                            <#assign hasNext = (nextIndex<totalNum) />

                            <#if hasNext>
                                <#assign nextLevel = menuInfo[nextIndex].menuLevel />
                                <#assign hasSub = (item.menuLevel == 1 && nextLevel == 2) />
                            </#if>

                            <#if item.menuLevel == 1 >
                                <#if hasSub>
                        </ul>
                        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                            <span>${item.menuName}</span>
                            <a class="d-flex align-items-center text-muted" href="#" aria-label="${item.menuName}">
                                <span data-feather="plus-circle"></span>
                            </a>
                        </h6>
                        <ul class="nav flex-column mb-2">
                                <#else>
                        <li class="nav-item">
                            <a class="nav-link" href="${item.menuPath}">
                                <span data-feather="layers"></span>
                                ${item.menuName}
                            </a>
                        </li>
                                </#if>
                            <#elseif item.menuLevel == 2 >
                            <li class="nav-item">
                                <a class="nav-link" href="${item.menuPath}">
                                    <span data-feather="layers"></span>
                                    ${item.menuName}
                                </a>
                            </li>
                            </#if>
                        </#list>

                    </ul>
                    </@MenuInfo>
                </div>
            </nav>

        <!-- Begin page content -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">首页</a></li>
                    <li class="breadcrumb-item active" aria-current="page">${title}</li>
                </ol>
            </nav>
            <div class="container">
                <#nested />
            </div>
        </main>
    </div>
</div>

<footer class="footer mt-auto py-3 text-center">
    <div class="container">
        <span class="text-muted">
            <strong>Copyright &copy; ${siteInfo.copyrightYear!'2020'} <a href="/">${siteInfo.siteName!'sixlab'}</a>.</strong> All rights reserved.
        </span>
    </div>
</footer>

<script src="/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
</@SiteInfo>
</#macro>