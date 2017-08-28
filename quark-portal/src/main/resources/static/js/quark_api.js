/*链接*/
var quark_login_api = "http://127.0.0.1:8081/user/login";
var quark_getUser_api = "http://127.0.0.1:8081/user/";
var quark_logout_api = "http://127.0.0.1:8081/user/logout";
var quark_register_api = "http://127.0.0.1:8081/user";
var quark_upload_api = "http://127.0.0.1:8081/upload/image";
var quark_posts_add_api = "http://127.0.0.1:8081/posts";
var quark_posts_get = "http://127.0.0.1:8081/posts";
var quark_label_getall = "http://127.0.0.1:8081/label";


function setCookie(data) {
    var expiresDate= new Date();
    expiresDate.setTime(expiresDate.getTime() + (60 * 60 * 1000));
    $.cookie("QUARK_TOKEN",data,{
        path: '/',
        expires : 1
    });
}

function getCookie() {
    return $.cookie('QUARK_TOKEN');
}

function deleteCookie() {
    $.cookie("QUARK_TOKEN",null);
}

(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);