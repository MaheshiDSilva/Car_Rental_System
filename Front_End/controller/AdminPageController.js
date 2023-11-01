let currentUser;

$.ajax({
    url: baseUrl + "login",
    method: "get",
    async: false,
    dataType: "json",
    contentType: "application/json",
    success: function (res) {
        currentUser = res.data;
        $("#user").text(res.data.username);
    }
});

loadHome();
manageHomePage();

function loadHome() {
    $('#home').fadeIn();
    $("#home").attr("style", "display : block !important");
    $("#viewCustomer").attr("style", "display : none !important");
    $("#manageCustomers").attr("style", "display : none !important");
    $("#manageCar").attr("style", "display : none !important");
    $("#viewCar").attr("style", "display : none !important");
    $("#manageDriver").attr("style", "display : none !important");
    $("#drivers").attr("style", "display : none !important");
    $("#rents").attr("style", "display : none !important");
    $("#payments").attr("style", "display : none !important");
    $("#reports").attr("style", "display : none !important");
}


function manageHomePage() {

    $("#btnHome").on("click", function () {
        loadHome();
        loadData();

    });

    loadData();

    function loadData() {
        $.ajax({
            url: baseUrl + "customer/count",
            method: "get",
            dataType: "json",
            success: function (res) {
                $("#reg-users").text(res.data);
            }
        });

        $.ajax({
            url: baseUrl + "rent/count",
            method: "get",
            dataType: "json",
            success: function (res) {
                $("#rent-count").text(res.data);
            }
        });

        $.ajax({
            url: baseUrl + "car/count",
            method: "get",
            dataType: "json",
            success: function (res) {
                $("#available-cars").text(res.data);
            }
        });

        $.ajax({
            url: baseUrl + "car/count/reserved",
            method: "get",
            dataType: "json",
            success: function (res) {
                $("#reserved-cars").text(res.data);
            }
        });

        $.ajax({
            url: baseUrl + "car/count/maintain",
            method: "get",
            dataType: "json",
            success: function (res) {
                $("#maintain-cars").text(res.data);
            }
        });

        $.ajax({
            url: baseUrl + "driver/available",
            method: "get",
            dataType: "json",
            success: function (res) {
                $("#available-drivers").text(res.data);
            }
        });

        $.ajax({
            url: baseUrl + "driver/reserved",
            method: "get",
            dataType: "json",
            success: function (res) {
                $("#reserved-drivers").text(res.data);
            }
        });

    }

    var dataPoints = [];

    var options = {
        animationEnabled: true,
        theme: "light2",
        title: {
            text: "Daily Sales Income"
        },
        axisX: {
            valueFormatString: "DD MMM YYYY",
        },
        axisY: {
            title: "LKR",
            titleFontSize: 24
        },
        data: [{
            type: "spline",
            yValueFormatString: "$#,###.##",
            dataPoints: dataPoints
        }]
    };

    $.ajax({
        url: baseUrl + "payment/daily",
        method: "get",
        success: function (res) {
            for (var i = 0; i < res.data.length; i++) {
                dataPoints.push({
                    x: new Date(res.data[i][0]),
                    y: res.data[i][1]
                });
            }
            $("#chartContainer").CanvasJSChart(options);
        }
    });

    let points = [];

    var brandOptions = {
        title: {
            text: "Car Brands"
        },
        subtitles: [{
            text: "About Car Brands"
        }],
        animationEnabled: true,
        data: [{
            type: "pie",
            startAngle: 40,
            toolTipContent: "<b>{label}</b>: {y}%",
            showInLegend: "true",
            legendText: "{label}",
            indexLabelFontSize: 16,
            indexLabel: "{label} - {y}%",
            dataPoints: points
        }]
    };

    $.ajax({
        url: baseUrl + "car/brand",
        method: "get",
        success: function (res) {
            for (var i = 0; i < res.data.length; i++) {
                points.push({
                    y: res.data[i][1],
                    label: res.data[i][0]
                });
            }
            $("#brandChart").CanvasJSChart(brandOptions);
        }
    });

}