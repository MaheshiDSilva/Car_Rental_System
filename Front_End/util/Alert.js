function saveAlert() {
    Al.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Successfully Saved..!',
        showConfirmButton: false,
        timer: 1500
    });
}

function updateAlert() {
    Al.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Successfully Updated..!',
        showConfirmButton: false,
        timer: 1500
    });
}

function deleteAlert() {
    Al.fire({
        position: 'top-end',
        icon: 'warning',
        title: 'Successfully Deleted..!',
        showConfirmButton: false,
        timer: 1500
    });
}

function errorAlert(title) {
    Al.fire({
        position: 'top-end',
        icon: 'error',
        title: title,
        showConfirmButton: false,
        timer: 1500
    });
}
