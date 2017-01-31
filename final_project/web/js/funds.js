function validateFundsForm(){
    var result = true;
    var FILL_FIELD = "*Fill field.";
    var BAD_LENGTH_CARD = "*Should content 8 digits";
    var BAD_LENGTH_CVV = "*Should content 3 digits";
    var errCard = document.getElementById("err-card");
    var errCvv = document.getElementById("err-cvv");
    var errCash = document.getElementById("err-cash");
    errCard.innerHTML = "";
    errCvv.innerHTML = "";
    errCash.innerHTML = "";
    var card = document.forms[0]["card"].value;
    var cvv = document.forms[0]["cvv"].value;
    var cash = document.forms[0]["cash"].value;
    if (!card){
        errCard.innerHTML = FILL_FIELD;
        result = false;
    }
    if (card && card.length != 8){
        errCard.innerHTML = BAD_LENGTH_CARD;
        result = false;
    }
    if (!cvv){
        errCvv.innerHTML = FILL_FIELD;
        result = false;
    }
    if (cvv && cvv.length != 3){
        errCvv.innerHTML = BAD_LENGTH_CVV;
        result = false;
    }
    if (!cash){
        errCash.innerHTML = FILL_FIELD;
        result = false;
    }
    return result;
}