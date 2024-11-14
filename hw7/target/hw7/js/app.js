window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

window.ajax = function (data, successFunc, $error = null) {
    $.ajax({
        type: "POST",
        dataType: "json",
        data,
        success: function (response) {
            successFunc(response);
            if (response.error) {
                $error.text(response.error)
            }
            if (response.redirect) {
                location.href = response.redirect
            }
        }
    })
}