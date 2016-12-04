<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Home Page</title>
   
<link rel="stylesheet" href="resources/css/navbar.css">
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">


 <!--Optional theme--> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
 <script src="resources/js/jquery.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<style type="text/css">
.STARRED{
	color: #FDD017;
}
</style>
</head>

<body>






<div class="container">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Compose Message</h4>
					</div>
					<div class="modal-body">
        <form role="form" class="form-horizontal">
          <div class="form-group">
            <label class="col-sm-2" for="inputTo">To</label>
            <div class="col-sm-10"><input class="form-control" id="receiver" placeholder="List of recipients" type="email"></div>
          </div>
          <div class="form-group">
            <label class="col-sm-2" for="inputSubject">Subject</label>
            <div class="col-sm-10"><input class="form-control" id="subject" placeholder="Subject" type="text"></div>
          </div>
          <div class="form-group">
            <label class="col-sm-12" for="inputBody">Message</label>
            <div class="col-sm-12"><textarea class="form-control" id="content" rows="8"></textarea></div>
          </div>
        </form>
      </div>
					<div class="modal-footer">
                         <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel</button> 
                         <button type="button" class="btn btn-warning pull-left">Save Draft</button>
                         <button type="button" class="btn btn-primary " data-dismiss="modal"  onclick='sendMessage();'>Send <i class="fa fa-arrow-circle-right fa-lg"></i></button>
                    </div>
				</div>
			</div>
		</div>
	</div>






	








<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">${sessionScope.user.firstName}</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
     
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">ABOUT</a></li>
         <li><a href="#">CONTACT US</a></li>
          <li><a href="#">SERVICES</a></li>
           <li><a href="#">LOGOUT</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="jumbotron">

 <div class="row">

   <div class="col-md-4">
     <h2>Hello ${sessionScope.user.firstName}</h2>
   </div>
   <div class="col-md-6">
     <div class="well">
      Look, here is my status
     </div>
   </div>
   <div class="col-md-2">
   <a class="btn btn-primary btn-lg" href="myProfile"> My Profile</a>
   </div>

</div>
</div>

<div class="container">
<div class="row">
 
 <div class="col-md-10" id="mainpage">
 </div>

<div class="col-md-2">

<a id="menu-toggle" href="#" class="btn btn-primary btn-lg toggle"><i class="glyphicon glyphicon-bookmark"></i></a>
<div id="sidebar-wrapper">
  <ul class="sidebar-nav">
    <a id="menu-close" href="#" class="btn btn-default btn-lg pull-right toggle"><i class="glyphicon glyphicon-remove"></i></a>
    <li class="sidebar-brand">
      <a href="#" data-toggle="modal" data-target="#myModal">COMPOSE </a>
    </li>
    <li>
      <a href="#" onclick='MakeRequest();' >INBOX</a>
    </li>
    <li>
      <a href="#" onclick='doOutbox();'>SENT</a>
    </li>
    <li>
      <a href="#" onclick='doDrafts();'>DRAFTS</a>
    </li>
     <li>
      <a href="#" onclick='doTrash();'>TRASH</a>
    </li>
    <li>
      <a href="#" onclick='doSpam()'>SPAM</a>
    </li>
     <li>
      <a href="#" onclick='doStarred();'>STARRED</a>
    </li>
    <li>
      <a href="#" onclick='doContacts();'>CONTACTS</a>
    </li>
  </ul>
</div>
</div>

</div>
</div>


<!-- jQuery -->
    
    
    <script src="resources/js/script.js"></script>

    
  


</body>



</html>
