
    $("#search-component-form").submit(function(event){
		alert($('#path').val());
        event.preventDefault();
		$("#ajaxResponse").html("");
        //ajax call
        $.ajax({           
            url:'/bin/searchservice',     
            type:'GET',         
            data : {              
                searchKey : document.getElementById('searchKey').value,
                path: $('#path').val()
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



       // $('#search-component-form').trigger('reset');
    });

