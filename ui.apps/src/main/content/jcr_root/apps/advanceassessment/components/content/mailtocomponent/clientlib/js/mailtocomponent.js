$('#contact-form').on('submit',function(e){     
    e.preventDefault();   //ajax call      
	console.log("calling js");
    $.ajax({           
        url:'http://localhost:4502/content/advanceassessment/en/advanceassessmentspage/jcr:content/root/mailtocomponent.groups.html',     
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







