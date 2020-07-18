
    $(".one-col > span").click(function () {
            event.preventDefault();

		//$("#ajaxResponse").html("");

        var tagValue = $(this).attr("id");

        alert(tagValue);
        //ajax call
        $.ajax({           
            url:'/bin/tagservice',     
            type:'GET',         
            data : {              
                tagValue : tagValue,
                path: "/content/sampleproject/en/product-page"
            },

            success:function(resp){

				 alert(resp);
                 //$('#ajaxResponse').html(resp);
                //console.log(resp);

                var obj = JSON.parse(resp);
                var tmp =' ';

                $.each( obj, function( key, value ) {
                    //$("#ajaxResponse").html("");
                    $("#ajaxResponse").append('<h5>' + value.title + '</h5>');
                    $("#ajaxResponse").append('<a href="' + value.pagePath + '"target="_blank">' +value.pagePath+ '</a>');
                    $("#ajaxResponse").append('</div">');

                });

            }
    })
});
