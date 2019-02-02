$(document).ready( function () {
    var table = $('#employeesTable').DataTable({
        "sAjaxSource": "http://localhost:8080/last/points/1",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "placeMarkId"},
            { "mData": "lon" },
            { "mData": "lat" },
            { "mData": "time" }
        ]
    })
});
