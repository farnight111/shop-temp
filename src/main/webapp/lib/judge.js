/*! function () {
    $.ajaxSetup({
        //设置ajax请求结束后的执行动作
        complete : function(XMLHttpRequest, textStatus) {
            // 通过XMLHttpRequest取响应头中信息,判断是否是重定向
            var redirect = XMLHttpRequest.getResponseHeader("REDIRECT");
            if (redirect == "REDIRECT") {
                window.top.location.href= XMLHttpRequest.getResponseHeader("PATH");//取出路径，重定向
            }
        }
    });
}();*/

layui.$.ajaxSetup({
    beforeSend: function(jqXHR, settings) {
    },
    success:function(result,status,xhr){
    },
    error:function(xhr,status,error){
    },
    complete:function(xhr,status){
        var redirect = xhr.getResponseHeader('REDIRECT');
        if (redirect === "REDIRECT") {
            window.top.location.href= xhr.getResponseHeader("PATH");//取出路径，重定向
        }
    }
});