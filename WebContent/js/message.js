$(document).ready(function() {
	if (localStorage.getItem("tweet_key")) {
		$('#leftmenu-ul > ul').append('<li id="my-tweets"><a href="javascript:void(0)" data-toggle="modal" data-target="#edit-tweets"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>My tweets</a></li>');
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
		$('#prev-page button').prop('disabled', false);
		if (!maxPage || page < maxPage) {
			$.get(
				"/tweet/comment/get",
				{
					page: ++page
				}
			).done(function(data) {
				if (data.comments.length < 1) {
					maxPage = --page;
					$('#next-page button').prop('disabled', true);
				} else {
					showMessages(data);

				}
			});
		} else if (page == maxPage) {
			$('#next-page button').prop('disabled', true);
		}
	});

	$('#prev-page').click(function() {
		$('#next-page button').prop('disabled', false);
		if (page > 1) {
			$.get(
				"/tweet/comment/get",
				{
					page: --page
				}
			).done(function(data) {
				showMessages(data);
			});
		} else {
			$('#prev-page button').prop('disabled', true);
		}
	});

	$('#edit-tweets').on('show.bs.modal', function() {
		var key = localStorage.getItem("tweet_key");
		$.get(
			"/tweet/comment/get",
			{
				key: key,
				page: 1
			}
		).done(function(data) {
			var rows = '';
			for (i = 0; i < data.comments.length; i++) {
				rows += '<tr><td>' + data.comments[i].data + '</td><td>' + data.comments[i].post_date + '</td></tr>';
			}

			$('#modal-tweets').html(
				'<table class="table">' +
				'	<thead><th>Message</th><th>Post date</th></thead>' +
				'	<tbody>' +
						rows +
				'	</tbody>' +
				'</table>'
			);
		});		
	});

	function showMessages(data) {
		$('#messages-panel').empty();
		$.each(data.comments, function(i, item) {
			$('#messages-panel').append(
				'<div class="row">' +
				'	<div class="col-md-12">' +
				'		<div class="panel panel-default">' +
				'			<div class="panel-heading">' +
								data.comments[i].username +
				'			</div>' +
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