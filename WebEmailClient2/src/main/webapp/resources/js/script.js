function sendMessage()
{	
	
	var receiver=$("#receiver").val();
	console.log(receiver);	
	var subject=$("#subject").val();
	console.log(subject);	
	var content=$("#content").val();
	console.log(content);
	    $.ajax({
        url : 'sendMessage',
        data:{"receiver":receiver,"subject":subject,"content":content},
        type: 'GET'
    });
}
function MakeRequest()
{
	    $.ajax({
        url : 'inbox',
        data:{},
        type: 'GET',

        success: function(data){
            $('#mainpage').html(data);
        }
    });
}
function replyTo(receiver) {
	$("#receiver").val(receiver);
}

function deleteMessage(event,id) {
	var domElement =$(event.target);
	var $row = $(domElement).closest("tr");
	$row.remove();
	
	$.ajax(
			{
				
				url : 'deleteMessage',
				data:{'id':id},
				type:'GET'
					}
	);	
}


function makeStarred(event,id){
	
	var domElement =$(event.target);
	var $span = $(domElement).closest("span");
	$span.toggleClass("STARRED");
	
	$.ajax(
			{
				
				url : 'starredMessage',
				data:{'id':id},
				type:'GET'
					}
	);	
	
}





$(document).ready(function(){
	MakeRequest();
	$("#mytable #checkall").click(function () {
	        if ($("#mytable #checkall").is(':checked')) {
	            $("#mytable input[type=checkbox]").each(function () {
	                $(this).prop("checked", true);
	            });

	        } else {
	            $("#mytable input[type=checkbox]").each(function () {
	                $(this).prop("checked", false);
	            });
	        }
	    });
	    
	    $("[data-toggle=tooltip]").tooltip();
	});



function doOutbox(){
	$.ajax(
			{
				
				url : 'outbox',
				data:{},
				type:'GET',
				
				success: function(data){
					$('#mainpage').html(data);
				}
					}
	);	
}




function doDrafts(){
	$.ajax(
			{
				
				url : 'drafts',
				data:{},
				type:'GET',
				
				success: function(data){
					$('#mainpage').html(data);
				}
					}
	);	
}

function doSpam(){
	$.ajax(
			{
				
				url : 'spam',
				data:{},
				type:'GET',
				
				success: function(data){
					$('#mainpage').html(data);
				}
					}
	);	
}




function doTrash(){
	$.ajax(
			{
				
				url : 'trash',
				data:{},
				type:'GET',
				
				success: function(data){
					$('#mainpage').html(data);
				}
					}
	);	
}


function doStarred(){
	$.ajax(
			{
				
				url : 'starred',
				data:{},
				type:'GET',
				
				success: function(data){
					$('#mainpage').html(data);
				}
					}
	);	
}




$("#menu-close").click(function(e) {
    e.preventDefault();
    $("#sidebar-wrapper").toggleClass("active");
  });
  $("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#sidebar-wrapper").toggleClass("active");
  });