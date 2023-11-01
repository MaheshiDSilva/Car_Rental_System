$("#btnLogin").on("click", function () {

    $.ajax({
        url: baseUrl + "login",
        method: "post",
        data: $("#loginForm").serialize(),
        success: function (res) {

            switch (res.data.role) {
                case "Admin":
                    window.open("adminPage.html",'_self');
                    break;
                case "Customer":
                    window.open("customerPage.html", '_self');
                    break;
                case "Driver":
                    window.open("driverPage.html", '_self');
                    break;
                default:
                    alert("none");
            }

        },
        error:function (res) {
            errorAlert("Wrong User Details..Please enter correct details..!");
        }
    });

});