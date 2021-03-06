<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>接口PAI</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand">升级接口API</a>
        </div>

        <div class="navbar-collapse collapse">
            <div class="col-md-6"></div>
            <ul class="nav navbar-nav">
                <li><a href="#"></a>
                </li>
                <li><a href=""></a>
                </li>
                <li><a href=""></a>
                </li>
                <li class="dropdown active">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a>
                        </li>
                        <li><a href="#">Another action</a>
                        </li>
                        <li><a href="#">Something else here</a>
                        </li>
                        <li class="divider"></li>
                        <li class="dropdown-header">Nav header</li>
                        <li><a href="#">Separated link</a>
                        </li>
                        <li><a href="#">One more separated link</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- Top Menu Items -->

        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse" id="apilist">
            <ul class="nav navbar-nav side-nav">
                <li class="active">
                    <a><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                </li>
                <li>
                    <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i
                            class="fa fa-fw fa-arrows-v"></i> Dropdown <i class="fa fa-fw fa-caret-down"></i></a>
                    <ul id="demo" class="collapse">
                        <li>
                            <a href="#">Dropdown Item</a>
                        </li>
                        <li>
                            <a href="#">Dropdown Item</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-4 col-xs-offset-0">

                <form role="form" id="apiform" target="jsonresult">

                    <div class="parambody">
                        <div class="parambody">
                            <div class="form-group"><label></label></div>
                            <div class="form-group"><label>URL</label>
                                <p class="form-control-static">http://localhost:8080www.baiud.com</p></div>
                            <div class="form-group"><label>METHOD</label>
                                <p class="form-control-static">POST</p></div>
                            <div class="form-group"><label>mobile</label><input class="form-control" name="mobile">
                            </div>
                            <div class="form-group"><label>password</label><input class="form-control" name="password">
                            </div>
                            <div class="form-group"><label>mobile</label><input class="form-control" name="mobile">
                            </div>
                            <div class="form-group"><label>password</label><input class="form-control" name="password">
                            </div>
                            <div class="form-group"><label>mobile</label><input class="form-control" name="mobile">
                            </div>
                            <div class="form-group"><label>password</label><input class="form-control" name="password">
                            </div>
                        </div>

                    </div>

                    <div class="form-group">
                        <button type="reset" class="btn btn-lg btn-info btn-block">Reset</button>
                    </div>
                    <div class="form-group">
                        <button id="subut" type="submit" onclick="return false"
                                class="btn btn-lg btn-primary btn-block">Submit
                        </button>
                    </div>
                </form>

            </div>
            <div class="col-sm-1"></div>
            <div class="col-lg-6">

                <div class="panel panel-red">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-9 text-left">
                                <label>返回结果：</label>
                            </div>
                        </div>
                    </div>
                    <iframe class="form-control" src="" name="jsonresult" style="min-height:500px"></iframe>
                </div>

            </div>
        </div>
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<input type="hidden" name="hidden" id="hinput">


<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="js/plugins/morris/raphael.min.js"></script>
<script src="js/plugins/morris/morris.min.js"></script>
<script src="js/plugins/morris/morris-data.js"></script>
<!--  <script src="js/apidata/apidatas.json"></script> -->

<script type="text/javascript">


    var apiarrys = [
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户登陆",
            "url": "www.baiud.com",
            "params": ["mobile", "password", "Email", "type"],
            "method": "POST"
        },
        {
            "title": "找回密码",
            "url": "www.baiud.com",
            "params": ["mobile", "password", "mobile", "password", "mobile", "password"],
            "method": "POST"
        },
        {
            "title": "修改密码",
            "url": "http://localhost:8081/get/data.josn",
            "params": ["mobile", "password", "mobile", "password"],
            "method": "POST"
        },
        {
            "title": "短信验证码",
            "url": "www.baiud.com/{mobile}/{password}",
            "params": ["mobile", "password", "addRess"],
            "method": "POST",
            "urlre": true
        },
        {
            "title": "验证码校验",
            "url": "www.baiud.com",
            "params": ["mobile", "password", "city"],
            "method": "POST"
        },
        {
            "title": "绑定手机号",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "第三方登录",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },
        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        },

        {
            "title": "用户注册",
            "url": "www.baiud.com",
            "params": ["mobile", "password"],
            "method": "POST"
        }
    ];


    var httphost = "http://localhost:8080";
    var ul = $("<ul>").addClass("nav").addClass("navbar-nav").addClass("side-nav");
    var paramobj;


    $.each(apiarrys, function (i, o) {


        $("<li>").append($("<a>").text(o.title).attr("index", i).attr("href", "#").click(function () {


            paramobj = apiarrys[$(this).attr("index")];

            $("#apiform").attr("method", paramobj.method).find(".parambody").empty();

            $("<div>").addClass("well")
                    .append($("<div>").addClass("form-group")
                            .append($("<label>").text(paramobj.title)))
                    .append($("<label>").text("接口URL："))
                    .append($("<p>").addClass("form-control-static").text(httphost + paramobj.url))
                    // .append($("<p>").addClass("form-control-static").text())
                    .append($("<label>").text("方法类型："))
                    .append($("<p>").addClass("form-control-static").text(paramobj.method))
                    .append($("<label>").text("请求地址："))
                    .append($("<p>").attr("id", "reqaddr").addClass("form-control-static").text(""))
                    .appendTo($("#apiform").find(".parambody"));

            $.each(paramobj.params, function (ip, op) {

                $("<div>").addClass("form-group")
                        .append($("<label>").text(op))
                        .append($("<input>").addClass("form-control").attr("name", op))
                        .appendTo($("#apiform").find(".parambody"));

            });

        })).appendTo(ul);
    });

    ul.appendTo($("#apilist")).find("a").click();


    $("#subut").click(function () {

        if (paramobj.urlre) {
            var pas = $("#apiform").serializeArray();
            var pattern = /\^{\*\}/g;
            var uul = "";
            $.each(pas, function (i, o) {

                if (i === 0) {
                    uul = paramobj.url.replace(new RegExp("\\{" + o.name + "\\}", "g"), o.value);
                } else {
                    uul = uul.replace(new RegExp("\\{" + o.name + "\\}", "g"), o.value);
                }
            });

            $("#reqaddr").text(uul).css("color", "red");

            $("#apiform").attr("action", uul).submit();
        } else {
            $("#reqaddr").text(paramobj.url).css("color", "red");
            $("#apiform").attr("action", paramobj.url).submit();
        }
    });


</script>

</body>

</html>
