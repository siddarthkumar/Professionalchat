$(document).ready(function(){
    $("select.q_study").change(function(){
        var selectedOption = $(this).children("option:selected").val();
        if (selectedOption=="Study") {
        	$(".study").removeClass('hide');
        	$(".job").addClass('hide');
        }

        else if (selectedOption=="Job") {
        	$(".job").removeClass('hide');
        	$(".study").addClass('hide');
        }
    });
});