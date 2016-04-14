<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../includes/head.html" %>
</head>
<body>
	<div id="container-body" class="container-fluid">
		<div id="row-body" class="row">
			<%@include file="../includes/menu.jsp" %>
			<div class="body-scroll col-md-8" style="margin-top: 25px;">
				<h3 class="page-header">My Account</h3>
				 <div class="table-responsive">
  					<table id="account-infos-table" class="table table-striped borderless">
  						<tbody>
	  						<tr class="row-account-infos" id="account-username">
	  							<td class="account-row-title">
	  								Username
	  							</td>
	  						</tr>
	  						<tr class="row-account-infos" id="account-email">
	  							<td class="account-row-title">
	  								Email
	  							</td>
	  						</tr>
	  						<tr class="row-account-infos" id="account-firstname">
	  							<td class="account-row-title">
	  								Firstname
	  							</td>
	  						</tr>
	  						<tr class="row-account-infos" id="account-lastname">
	  							<td class="account-row-title">
	  								Lastname
	  							</td>
	  						</tr>
	  						<tr class="row-account-infos" id="account-subdate">
	  							<td class="account-row-title">
	  								Subscription date
	  							</td>
	  						</tr>
  						</tbody>
     				</table>
				</div> 
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/authentification.js"></script>
	<script type="text/javascript" src="js/friends.js"></script>
	<script type="text/javascript" src="js/account.js"></script>
</body>
</html>