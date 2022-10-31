// modalshow
function DetailModalShow() {
    $('#detail_modal').modal({backdrop: 'static', keyboard: false})
    console.log("show");
    $("#detail_modal").modal("show");
}

// modal hide
function DetailModalHide() {
    console.log("hide");
    $("#detail_modal").modal("hide");
}

function DetailModalInput() {
    $('#detailInput_modal').modal({backdrop: 'static', keyboard: false})
    console.log("show");
    $("#detailInput_modal").modal("show");
}

// modal hide
function DetailModalClose() {
    console.log("hide");
    $("#detailInput_modal").modal("hide");
}

function ExpendModalInput() {
    $('#workTime_modal').modal({backdrop: 'static', keyboard: false})
    console.log("show");
    $("#workTime_modal").modal("show");
}

// modal hide
function ExpendModalClose() {
    console.log("hide");
    $("#workTime_modal").modal("hide");
}

function ConfirmHolidayModal() {
    $('#detail_workTime_modal').modal({backdrop: 'static', keyboard: false})
    console.log("show");
    $("#detail_workTime_modal").modal("show");
}

// modal hide
function ConfirmHolidayModalClose() {
    console.log("hide");
    $("#detail_workTime_modal").modal("hide");
}


