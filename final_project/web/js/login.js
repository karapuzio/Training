function validateLoginForm(){
    var result = true;
    var FILL_FIELD = "*Fill field.";
    var BAD_LENGTH = "*Too long. Is should be < 21 symbol.";
    var errLogin = document.getElementById("err-login");
    var errPassword = document.getElementById("err-password");
    errLogin.innerHTML = "";
    errPassword.innerHTML = "";
    var login = document.forms[0]["login"].value;
    var password = document.forms[0]["password"].value;
    if (!login){
        errLogin.innerHTML = FILL_FIELD;
        result = false;
    }
    if (login && login.length > 20){
        errLogin.innerHTML = BAD_LENGTH;
        result = false;
    }
    if (!password){
        errPassword.innerHTML = FILL_FIELD;
        result = false;
    }
    if (password && password.length > 20){
        errPassword.innerHTML = BAD_LENGTH;
        result = false;
    }
    return result;
}