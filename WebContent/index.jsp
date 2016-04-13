<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
	<%@include file="includes/head.html" %>
</head>
<body>
	
	<div id="container-body" class="container-fluid">
		<div id="row-body" class="row">
			<%@include file="includes/menu.jsp" %>
			<div class="body-scroll col-md-8" style="margin-top: 25px;">
				<form id="message-form" method="get">
					<textarea id="send-textarea" class="span6" rows="3" cols="50"
					placeholder="What's up? (min. 3 characters)" required></textarea>
					<button id="send-message" class="btn btn-primary">Send</button>
				</form>
				<div class="row">
					<nav>
						<ul id="pager" class="pager">
							<li class="prev-page" data-user="0"><button class="btn">Previous</button></li>
							<li class="next-page" data-user="0"><button class="btn">Next</button></li>
						</ul>
					</nav>
				</div>
				<div id="messages-panel"></div>
			</div>
		</div>
	</div>
	<div id="edit-tweets" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">My tweets</h4>
				</div>
				<div id="modal-tweets" class="modal-body">

				</div>
				<div class="row">
					<nav>
						<ul id="pager-modal-tweets" class="pager">
							<li class="prev-page" data-user="1"><button class="btn">Previous</button></li>
							<li class="next-page" data-user="1"><button class="btn">Next</button></li>
						</ul>
					</nav>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<script type="text/javascript" src="js/authentification.js"></script>
	<script type="text/javascript" src="js/message.js"></script>
	<script type="text/javascript" src="js/friends.js"></script>
</body>
</html>