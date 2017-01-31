function validateAddSongForm(){
    var result = true;
    var FILL_FIELD = "*Fill field.";
    var BAD_LENGTH = "*Too long. Is should be < 45 symbol.";
    var errPerformance = document.getElementById("err-performance");
    var errName = document.getElementById("err-name");
    var errGenre = document.getElementById("err-genre");
    var errCost = document.getElementById("err-cost");
    errPerformance.innerHTML = "";
    errName.innerHTML = "";
    errGenre.innerHTML = "";
    errCost.innerHTML = "";
    var performance = document.forms[0]["performance"].value;
    var name = document.forms[0]["name"].value;
    var genre = document.forms[0]["genre"].value;
    var cost = document.forms[0]["cost"].value;
    if (!performance){
        errPerformance.innerHTML = FILL_FIELD;
        result = false;
    }
    if (performance && performance.length < 45){
        errPerformance.innerHTML = BAD_LENGTH;
        result = false;
    }
    if (!name){
        errName.innerHTML = FILL_FIELD;
        result = false;
    }
    if (name && name.length < 45){
        errName.innerHTML = BAD_LENGTH;
        result = false;
    }
    if (!genre){
        errGenre.innerHTML = FILL_FIELD;
        result = false;
    }
    if (genre && genre.length < 45){
        errGenre.innerHTML = BAD_LENGTH;
        result = false;
    }
    if (!cost){
        errCost.innerHTML = FILL_FIELD;
        result = false;
    }
    return result;
}

function validateAddGenreForm(){
    var result = true;
    var FILL_FIELD = "*Fill field.";
    var BAD_LENGTH = "*Too long. Is should be < 45 symbol.";
    var errGenre = document.getElementById("err-genre");
    errGenre.innerHTML = "";
    var genre = document.forms[0]["genre"].value;
    if (!genre){
        errGenre.innerHTML = FILL_FIELD;
        result = false;
    }
    if (genre && genre.length < 45){
        errGenre.innerHTML = BAD_LENGTH;
        result = false;
    }
    return result;
}

function validateAddPerformanceForm(){
    var result = true;
    var FILL_FIELD = "*Fill field.";
    var BAD_LENGTH = "*Too long. Is should be < 45 symbol.";
    var errPerformance = document.getElementById("err-performer");
    errPerformance.innerHTML = "";
    var performer = document.forms[0]["performer"].value;
    if (!performer){
        errPerformance.innerHTML = FILL_FIELD;
        result = false;
    }
    if (performer && performer.length < 45){
        errPerformance.innerHTML = BAD_LENGTH;
        result = false;
    }
    return result;
}