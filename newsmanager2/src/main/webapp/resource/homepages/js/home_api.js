var home_api_data = {

    init: function () {

        this.guanyuhonghe();
        this.xinwenzhongxi();
        this.xiangmufangan();
        this.lianxifangshi();

        var _this = this;

        $("#xinwenzx_left").find("li").click(function () {
            // alert($(this).index());
            _this.xinwenzhongxi(_this,$(this).index());

        });

    },

    getparam: {
        host: "http://localhost:8080",
        // host: "http://www.hhescf.com",
        page: -1,
        guanyhh: {
            url: "/cpbase/base/get",
            param: {"type": "101"}
        },
        wenhua: {
            url: "/cpbase/base/qywm/list",
            param: {page: 1, type: 102, pageSize: 500}
        },
        tuandui: {
            url: "/cpbase/base/team/get",
            param: {"type": "1"}
        },
        xinwen: {
            url: "/content/list",
            param: {page: 1, type: 2, pageSize: 10}
        },
        zixun: {
            url: "/content/list",
            param: {page: 1, type: 3, pageSize: 10}
        },
        baojia: {
            url: "/content/list",
            param: {page: 1, type: 106, pageSize: 100}
        },
        cpliebiao: {
            url: "/content/list",
            param: {page: 1, type: 4, pageSize: 100}
        },

        yhliuyan: {
            url: "/content/yhliuyan/get",
            // url: "/cpbase/base/get",
            // url: "/content/list",
            param: {page: 1, type: 4, pageSize: 100}
        },

        cpInfo: {
            url: "/content/get",
            param: {page: 1, pageSize: 100}
        },

        cptd: {
            // url: "/cpbase/base/cptd/list",
            url: "/content/get",
            param: {page: 1, type: 5, pageSize: 100}
        }

    },


    guanyuhonghe: function () {

        var contt = $("#jianjie");

        var p = this.getparam;
        if (contt && contt.length > 0) {
            contt.find("p").empty();
            $.get(p.host + p.guanyhh.url, p.guanyhh.param, function (data) {
                contt.find("p").empty().html(data.resultData.content.cont);
                contt.find("img").attr("src", data.resultData.content.img);
            })
        }
        var wenhua = $("#wenhua");
        if (wenhua && wenhua.length > 0) {
            $.get(p.host + p.wenhua.url, p.wenhua.param, function (data) {
                var data = data.resultData;
                var html = '<div class="row main-con guanyu-div-height about">';
                $.each(data, function (i, o) {
                    html += ' <div class="col-md-3 col-sm-6 col-xs-12">' +
                        '<a href="#">' +
                        '<img class="rotate-img" src="img/gear.png" alt="gear">' +
                        '<img class="icon-part" src="' + o.data.imgurl + '" alt="icon">' +
                        '</a>' +
                        '<h4>' + o.data.title + '</h4>'
                    $.each(o.data.subtitle, function (i, o) {
                        html += '<p>' + o + '</p>';
                    });
                    html += "</div>";
                });
                html += '</div>'

                wenhua.empty().html(html);
            })
        }

        var tuandui = $("#tuandui");

        if (tuandui && tuandui.length > 0) {
            $.get(p.host + p.tuandui.url, p.tuandui.param, function (data) {
                tuandui.empty().html(data.resultData.content);
            })
        }

    },

    xinwenzhongxi: function ($this,index_num) {

        var _this = this;

        if ($this) {
            _this = $this;
        }
        var xinwen = $("#xinwen");
        var p = _this.getparam;



        if (xinwen && xinwen.length > 0) {

            // if(index_num && index_num!=0){
            //     return ;
            // }

            if (_this.getparam.page != -1) {
                p.xinwen.param.page = _this.getparam.page;
            }
            $.get(p.host + p.xinwen.url, p.xinwen.param, function (data) {
                var html = '<ul class="news">';
                $.each(data.resultData.newsList, function (i, o) {
                    html += '<li><a href="xinwen1.html?id=' + o.id + '" target="_blank">' + o.title + '</a></li>';
                    // html += '<li><a href="xinwen1.html?id=' + o.id + '" target="_blank">' + o.title + '</a><span>' + o.creatTime + '</span></li>';
                });
                html += "</ul>"
                xinwen.empty().html(html);
                _this.pagers(data, xinwen, _this.xinwenzhongxi);
            })
        }
        var zixun = $("#zixun");
        var p = _this.getparam;
        if (zixun && zixun.length > 0) {

            // if(index_num && index_num!=1){
            //     return ;
            // }
            $.get(p.host + p.zixun.url, p.zixun.param, function (data) {
                var html = '<ul class="news">';
                $.each(data.resultData.newsList, function (i, o) {
                    html += '<li><a href="zixun1.html?id=' + o.id + '" target="_blank">' + o.title + '</a></li>';
                    // html += '<li><a href="zixun1.html?id=' + o.id + '" target="_blank">' + o.title + '</a><span>' + o.creatTime + '</span></li>';
                });
                html += "</ul>"
                zixun.empty().html(html);
                _this.pagers(data, zixun, _this.xinwenzhongxi);
            })
        }
        var baojia = $("#baojia");
        var p = _this.getparam;
        if (baojia && baojia.length > 0) {


            var html="";

            $.get(p.host + p.baojia.url, p.baojia.param, function (data) {

                $.each(data.resultData.newsList, function (i, o) {
                    // html += '<li pid="' + o.id + '">' + o.title + '</li>'
                    html += '<li><a href="baojia.html?id=' + o.id + '" target="_blank">' + o.title + '</a></li>';

                });

                baojia.find("ul").empty().html(html);

            })

            function getbaojiadata(id) {

                p.cpInfo.param.id = id;

                $.get(p.host + p.cpInfo.url, p.cpInfo.param, function (data) {
                    if (data.resultData.content) {
                        $("#chanpin").hide();
                        $("#tedian").hide();
                        var title = "<h4 class='pro'>" + data.resultData.title + "</h4>";
                        $("#cpdetail").empty().html(title + data.resultData.content).show();
                    }
                });

            }

        }



    },

    xiangmufangan: function () {
        var contt = $("#cptd_js");
        var p = this.getparam;
        var licc;
        if (contt && contt.length > 0) {
            // contt.find("p").empty();
            var html = "";
            $.get(p.host + p.cpliebiao.url, p.cpliebiao.param, function (data) {

                $.each(data.resultData.newsList, function (i, o) {
                    html += '<li pid="' + o.id + '">' + o.title + '</li>'

                    // if (contt.find("li").eq(0).hasClass("active") && i == 0) {
                    //
                    // }

                })

                licc = contt.find("ul").empty().append(html);

                // contt.find("ul").find("li").click(function () {
                //     // alert($(this).attr("pid"));
                //
                //     $(this).parent().find(".cptd_activ").removeClass("cptd_activ");
                //     $(this).addClass("cptd_activ");
                //     var id = $(this).attr("pid");
                //
                //     getcppdata(id);
                //
                // });

                contt.find("li").click(function () {


                    if ($(this).hasClass("org")) {
                        $(this).parent().find(".cptd_activ").removeClass("cptd_activ");

                        if ($(this).index() == 0) {
                            $("#chanpin").show();
                            $("#cpdetail").hide();
                            $("#tedian_detail").hide();
                            $("#tedian").hide();
                        } else {
                            $("#tedian_detail").hide();
                            $("#tedian").show();
                            $("#chanpin").hide();
                            $("#cpdetail").hide();

                        }
                        return;
                    } else {
                        $(this).parent().find(".cptd_activ").removeClass("cptd_activ");

                        contt.find(".active").removeClass("active");

                        $(this).addClass("cptd_activ");

                    }
                    var id = $(this).attr("pid");

                    // var id = contt.find("ul").find("li").eq(0).attr("pid");
                    // var title = $(this).text();
                    getcppdata(id, $(this));

                })

                function getcppdata(id, obj) {

                    p.cpInfo.param.id = id;
                    $.get(p.host + p.cpInfo.url, p.cpInfo.param, function (data) {
                        if (data.resultData.content) {
                            $("#chanpin").hide();
                            $("#tedian").hide();
                            var title = "<h4 class='pro'>" + data.resultData.title + "</h4>";
                            $("#cpdetail").empty().html(title + data.resultData.content).show();
                        }
                    });

                    // return;

                    // // var title = "<h4 class='pro cpdetailtd'>" + obj.html() + "</h4>";
                    // if (contt.find("li").eq(0).hasClass("active")) {
                    //     p.cpInfo.param.id = id;
                    //     $.get(p.host + p.cpInfo.url, p.cpInfo.param, function (data) {
                    //         if (data.resultData.content) {
                    //             $("#chanpin").hide();
                    //             var title = "<h4 class='pro'>" + data.resultData.title + "</h4>";
                    //             $("#cpdetail").empty().html(title + data.resultData.content).show();
                    //         }
                    //     });
                    // } else {
                    //     // p.cptd.param.cpId = id;
                    //     p.cptd.param.id = id;
                    //     $.get(p.host + p.cptd.url, p.cptd.param, function (data) {
                    //
                    //         var ccon = data.resultData.content;
                    //
                    //         if (!ccon) {
                    //             ccon = "";
                    //         }
                    //
                    //         $("#tedian").hide();
                    //         $("#tedian_detail").empty().html(title + ccon).show();
                    //
                    //     });
                    // }
                }

            })

        }


    },

    lianxifangshi: function () {
        var lianxi = $("#usermesgbut");

        if (lianxi && lianxi.length > 0) {
            var _this = this;
            lianxi.click(function () {
                var  form = $(this).parents("form");
                if( form.find("input[name='name']").val()==""){
                    alert("请填写姓名");
                    return;
                }

                if( form.find("input[name='industry']").val()==""){
                    alert("请填写行业类别");
                    return;
                }

                if( form.find("input[name='phone']").val()==""){
                    alert("请填写联系电话");
                    return;
                }

                if( form.find("input[name='email']").val()==""){
                    alert("请填写邮箱");
                    return;
                }

                if( form.find("input[name='message']").val()==""){
                    alert("请填写意见或建议");
                    return;
                }

                var dataf = form.serialize();
                $.post(_this.getparam.host+_this.getparam.yhliuyan.url,dataf,function (data) {
                    alert("留言成功");
                    window.location.reload();
                }, "json");
            });

        }

    },

    pagers: function (data, contener, fnback) {

        var c_id = contener.attr("id") + "_pager";

        var pagehtml = '<ul class="pagination" id="' + c_id + '">';
        var pagedata = data.resultData.page;

        if (pagedata.first) {
            pagehtml += '<li><a nopage="0">&lt;</a></li>'
        } else {
            pagehtml += '<li><a href="#">&lt;</a></li>'
        }

        $.each(pagedata.pagelist, function (i, o) {

            if (o === pagedata.curre) {
                pagehtml += '<li class="active"><a>' + o + '</a></li>'
            } else {
                pagehtml += '<li><a>' + o + '</a></li>';
            }
        })

        if (pagedata.end) {
            pagehtml += '<li><a nopage="0">&gt;</a></li>'
        } else {
            pagehtml += '<li><a href="#">&gt;</a></li>'
        }
        pagehtml += "</ul>";

        contener.append($(pagehtml));


        var _this = this;

        // 翻页
        $("#" + c_id).find("a").click(function () {


            if ($(this).attr("nopage") == 0) {
                return;
            }

            var pageno = $(this).text();


            if (pageno == "<") {
                pageno = $("#" + c_id).find("li.active").find("a").text() * 1 - 1;
            }
            if (pageno == ">") {
                pageno = $("#" + c_id).find("li.active").find("a").text() * 1 + 1;
            }

            _this.getparam.page = pageno;
            _this.getparam.userPager = true;
            fnback(_this);

        });


    }

}


$(function () {
    home_api_data.init();
})