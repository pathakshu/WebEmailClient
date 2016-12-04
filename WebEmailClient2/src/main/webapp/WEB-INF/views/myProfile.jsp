<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">


<!--Optional theme-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<script src="resources/js/jquery.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>


</head>
<body>

	<div class="container" style="padding-top: 60px;">
		<h1 class="page-header">Edit Profile</h1>
		<div class="row">
			<!-- left column -->


			<div class="col-md-4 col-sm-6 col-xs-12">
				<div class="text-center">
					<img class="avatar img-circle img-thumbnail" alt="avatar">
					<form method="POST" enctype="multipart/form-data" action="upload">
						File to upload: <input type="file" name="file"><br />
						Name: <input type="text" name="name"><br /> <br /> <input
							type="submit" value="Upload"> Press here to upload the
						file!
					</form>
				</div>
			</div>
			<!-- edit form column -->
			<div class="col-md-8 col-sm-6 col-xs-12 personal-info">
				<div class="alert alert-info alert-dismissable">
					<a class="panel-close close" data-dismiss="alert">×</a> <i
						class="fa fa-coffee"></i> This is an <strong>.alert</strong>. Use
					this to show important messages to the user.
				</div>
				<h3>Personal info</h3>




				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-lg-3 control-label">First name:</label>
						<div class="col-lg-8">
							<input class="form-control" value="Jane" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Last name:</label>
						<div class="col-lg-8">
							<input class="form-control" value="Bishop" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Company:</label>
						<div class="col-lg-8">
							<input class="form-control" value="" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Email:</label>
						<div class="col-lg-8">
							<input class="form-control" value="janesemail@gmail.com"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Time Zone:</label>
						<div class="col-lg-8">
							<div class="ui-select">
								<select id="user_time_zone" class="form-control">
									<option value="Hawaii">(GMT-10:00) Hawaii</option>
									<option value="Alaska">(GMT-09:00) Alaska</option>
									<option value="Pacific Time (US &amp; Canada)">(GMT-08:00)
										Pacific Time (US &amp; Canada)</option>
									<option value="Arizona">(GMT-07:00) Arizona</option>
									<option value="Mountain Time (US &amp; Canada)">(GMT-07:00)
										Mountain Time (US &amp; Canada)</option>
									<option value="Central Time (US &amp; Canada)"
										selected="selected">(GMT-06:00) Central Time (US
										&amp; Canada)</option>
									<option value="Eastern Time (US &amp; Canada)">(GMT-05:00)
										Eastern Time (US &amp; Canada)</option>
									<option value="Indiana (East)">(GMT-05:00) Indiana
										(East)</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Username:</label>
						<div class="col-md-8">
							<input class="form-control" value="janeuser" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Password:</label>
						<div class="col-md-8">
							<input class="form-control" value="11111122333" type="password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Confirm password:</label>
						<div class="col-md-8">
							<input class="form-control" value="11111122333" type="password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-8">
							<input class="btn btn-primary" value="Save Changes" type="button">
							<span></span> <input class="btn btn-default" value="Cancel"
								type="reset">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>




</body>
</html>