$(document).ready(function () {

    var dat;
    var code = 85040;
    $('.load-more').click(function () {
		console.log("load more button clicked");
        getData();
    });


    function getData() {

        $.ajax({
            url: $('#apiUrl').val(),
            type: 'GET',
            success: appendData
        });



    }


    function appendData(data) {

        console.log(data);
        for (var i = 0; i < data.length; i++) {
            console.log('as')
            $('.api-data-list').append('<li>Bachelor of Science with a ' + data[i].username + ' certificate ></li>');
        }
        code++;
    }


});