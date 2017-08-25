/*链接*/
var quark_login_api = "http://127.0.0.1:8081/user/login";
var quark_getUser_api = "http://127.0.0.1:8081/user/token/";
var quark_logout_api = "http://127.0.0.1:8081/user/logout";
var quark_register_api = "http://127.0.0.1:8081/user/createUser"


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