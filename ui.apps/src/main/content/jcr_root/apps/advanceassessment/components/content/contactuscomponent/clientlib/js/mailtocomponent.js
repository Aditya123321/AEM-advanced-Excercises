$('#contact-form').on('submit',function(e){     
    e.preventDefault();   //ajax call      
	console.log("calling js");
    $.ajax({           
        url:'/bin/servlet/advanceassessment',     
        type:'GET',         
        data : {              
            fname : $('#fname').val(),      
            lname : $('#lname').val(),           
            email : $('#email').val(),         
            phno : $('#phno').val(),             
            ques : $('#ques').val(),                
            checkbox : $('#checkbox').prop('checked') },   
            success:function(resp){                
                alert(resp);            
            }     
     })

      $('#contact-form').trigger('reset');     
});







