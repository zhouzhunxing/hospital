// ajax 封装
function ajaxfuntion($, url, todata, type, fun) {
    $.ajax({
        url: url,
        data: todata,
        type: type,
        dataType: 'json',
        success: fun
    });
}

//请求带token认证
function ajaxsessionfuntion($, url, todata, type, fun) {
    $.ajax({
        url: url,
        data: todata,
        type: type,
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("token", localStorage.getItem("drugtoken"));
        },
        success: fun
    });
}

//layui 文件上传封装
function layuiImagepost(upload, $, imagediv, url, dataparams) {
    var uploadInst = upload.render({
        elem: imagediv
        , url: url
        //,data:datas
        , before: function (obj) {
            this.data = dataparams;
            obj.preview(function (index, file, result) {
                $(imagediv).attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            layer.msg(res.msg);
            //alert(JSON.stringify(res));
            //alert(res.data.img.outUrl);
            //$(imagediv).attr('value', res.data.outUrl);
            $(imagediv).attr("src", res.data.img.outUrl);
        }
    });
}

//表单创建封装
function createTable(table, dataurl, wheres, colsarray, pageflags, id, layer) {
    return table.render({
        elem: id
        , url: dataurl
        , where: wheres
        , cols: colsarray
        , page: pageflags
        , response: {
            statusName: 'status' //数据状态的字段名称，默认：code
            , statusCode: 1 //成功的状态码，默认：0
            , msgName: 'msg' //状态信息的字段名称，默认：msg
            , countName: 'all_count' //数据总数的字段名称，默认：count
            , dataName: 'list' //数据列表的字段名称，默认：data
        }
        , done: function (res) {
            layer.msg(res.msg);
        }
    });
}

//模块封装
function openModel(url, layer) {
    layer.open({
        type: 2,
        area: ['600px', '360px'],
        shadeClose: true, //点击遮罩关闭
        content: url
    });
}

function openModelNoArea(url, layer, namastr) {
    layer.open({
        type: 2,
        shadeClose: true,
        title: namastr,
        fixed: false, //不固定
        maxmin: true, //点击遮罩关闭
        content: url
    });
}

function openconfirm(url, layer, data, $) {
    var namastr = "您确定将" + data.realName + "积分" + data.integration + "消除";
    layer.confirm(namastr, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        ajaxsessionfuntion($, url, {"id": data.id}, "post", function (data) {
            layer.msg('更新成功', {icon: 1});
        });
    });
}

function openconfirmSettlement(url, layer, data, $, datainfo) {
    var namastr = "共" + data.info + "元";
    layer.confirm(namastr, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        ajaxsessionfuntion($, url, datainfo, "get", function (data) {
            layer.msg('更新成功', {icon: 1});
        });
    });
}