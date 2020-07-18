$('#servicecategory').submit(() => {
        event.preventDefault(); 
		alert('hello'); 
        //ajax call
        $.ajax({
            url:'/bin/servicecategory/faq',
            type:'GET',
            data : {
                selected : $('#myselect').children("option:selected").val(),
                    path: $('#path').val() 
            },
            success:function(resp){
                alert(resp);
                $('.test').html(resp);
                alert(resp);
                console.log(resp);
            }
        }) 
        $('#servicecategory').trigger('reset');
    });