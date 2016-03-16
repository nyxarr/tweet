$(document).ready(function() {
	if (localStorage.getItem("tweet_key")) {
		$('#leftmenu-ul > ul').append('<li id="my-tweets"><a href="javascript:void(0)"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>My tweets</a></li>');
	}

	var page = 1;
	var maxPage;

	$.get(
		"/tweet/comment/get",
		{
			page: 1
		}
	).done(function(data) {
		showMessages(data);
	});

	$('#my-tweets').click(function(event) {
		alert('my tweets');
	});

	$('#send-message').click(function() {
		var key = localStorage.getItem("tweet_key");
		if ($('#send-textarea').val() && key) {
			$.get(
				"/tweet/comment/add", {
					key: key,
					comment: $('#send-textarea').val()
				}
			).done(function(data) {
				location.reload(true);
			});
		}
	});

	$('#next-page').click(function() {
		if (!maxPage || page < maxPage) {
			$.get(
				"/tweet/comment/get",
				{
					page: ++page
				}
			).done(function(data) {
				if (data.comments.length < 1) {
					maxPage = --page;
					$('#next-page').prop('disabled', true);
				} else {
					showMessages(data);
				}
			});
		}
	});

	$('#prev-page').click(function() {
		if (page != 1) {
			$.get(
				"/tweet/comment/get",
				{
					page: --page
				}
			).done(function(data) {
				showMessages(data);
			});
		} else {
			$('#prev-page').prop('disabled', true);
		}
	});

	function showMessages(data) {
		$('#messages-panel').empty();
		$.each(data.comments, function(i, item) {
			$('#messages-panel').append(
				'<div class="row">' +
				'	<div class="col-md-2">' +
				'		<div class="panel panel-default">' +
				'			<div class="panel-body">' + data.comments[i].username + '</div>' +
				'		</div>' +
				'	</div>' +
				'	<div class="col-md-10">' +
				'		<div class="panel panel-default">' +
				'			<div class="panel-body">' +
								data.comments[i].data.replace(/\n/g, "<br />") +
				'			</div>' +
				'			<div class="panel-footer">Posted on ' + data.comments[i].post_date + '</div>' +
				'		</div>' +
				'	</div>' +
				'</div>'
			);
		});
	}
});