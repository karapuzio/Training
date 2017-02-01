function validateRegistrationForm(){
    var result = true;
    var FILL_FIELD = "*Fill field.";
    var BAD_LOGIN = "*Min.length - 5, max - 20. Can contain a-zA-Z0-9_";
    var PWD_NOT_EQUAL = "*Passwords are'n same.";
    var BAD_PASSWORD = "*Not good enough password. Min.length - 8, max - 20. It should content one big and small letter, and one digit.";
    var BAD_EMAIL = "*Not correct email.";
    var errLogin = document.getElementById("err-login");
    var errPassword = document.getElementById("err-password");
    var errConfirm = document.getElementById("err-confirm");
    var errEmail = document.getElementById("err-email");
    errLogin.innerHTML = "";
    errPassword.innerHTML = "";
    errConfirm.innerHTML = "";
    errEmail.innerHTML = "";
    var login = document.forms[0]["login"].value;
    var password = document.forms[0]["password"].value;
    var password_confirm = document.forms[0]["confirm"].value;
    var email = document.forms[0]["email"].value;
    if (!login){
        errLogin.innerHTML = FILL_FIELD;
        result = false;
    }
    if (login && login.search(/^[a-zA-Z][a-zA-Z0-9_]{4,20}$/) !== 0){
        errLogin.innerHTML = BAD_LOGIN;
        result = false;
    }
    if (!password){
        errPassword.innerHTML = FILL_FIELD;
        result = false;
    }
    if (!password_confirm){
        errConfirm.innerHTML = FILL_FIELD;
        result = false;
    }
    if (password && password_confirm && (password.length > 20 || password.length < 8 || password.search(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/) !== 0)){
        errPassword.innerHTML = BAD_PASSWORD;
        errConfirm.innerHTML = BAD_PASSWORD;
        result = false;
    }
    if (password && password_confirm && password_confirm !== password){
        errPassword.innerHTML = PWD_NOT_EQUAL;
        errConfirm.innerHTML = PWD_NOT_EQUAL;
        result = false;
    }
    if (!email){
        errEmail.innerHTML = FILL_FIELD;
        result = false;
    }
    if (email && email.search(/@.*\./) == 0){
        errEmail.innerHTML = BAD_EMAIL;
        result = false;
    }
    return result;
}