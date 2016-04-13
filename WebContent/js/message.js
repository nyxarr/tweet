$(document).ready(function() {
	if (localStorage.getItem("tweet_key")) {
		$('#leftmenu-ul > ul').append(
			'<li id="my-tweets">' +
			'	<a href="javascript:void(0)" data-toggle="modal" data-target="#edit-tweets">' +
			'		<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>' +
			'		My tweets' +
			'	</a>' +
			'</li>'
		);
	} else {
		$("#message-form").hide();
	}

	var page = 1;
	var bufferPage;
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
		console.log('ok');
		if ($('#send-textarea').val() && $('#send-textarea').val().length > 2 && key) {
			$.get(
				"/tweet/comment/add", {
					key: key,
					comment: $('#send-textarea').val()
				}
			).done(function(data) {
				location.reload(true);
			});
		} else {
			alert('Cannot send message.');
		}
	});

	$('.next-page').click(function() {
		if ($(this).attr('data-user') == 1) {
			var key = localStorage.getItem("tweet_key");
		} else {
			var key = null;
			if (bufferPage)
				page = bufferPage
		}

		$(this).siblings('.prev-page button').removeProp('disabled');
		if (!maxPage || page < maxPage) {
			$.get(
				"/tweet/comment/get",
				{
					page: ++page,
					key: key
				}
			).done(function(data) {
				if (data.comments.length < 1) {
					maxPage = --page;
					$('#next-page button').prop('disabled', true);
				} else {
					if (key)
						showMessages(data, true);
					else
						showMessages(data, false);
				}
			});
		} else if (page == maxPage) {
			$(this).siblings('.next-page button').prop('disabled', true);
		}
	});

	$('.prev-page').click(function() {
		if ($(this).attr('data-user') == 1) {
			var key = localStorage.getItem("tweet_key");
		} else {
			var key = null;
		}

		$(this).siblings('.next-page button').removeProp('disabled');
		if (page > 1) {
			$.get(
				"/tweet/comment/get",
				{
					page: --page,
					key: key
				}
			).done(function(data) {
				if (key)
					showMessages(data, true);
				else
					showMessages(data, false);
			});
		} else {
			$(this).siblings('.prev-page button').prop('disabled', true);
		}
	});

	$('#edit-tweets').on('show.bs.modal', function() {
		var key = localStorage.getItem("tweet_key");
		maxPage = 0;
		bufferPage = page;
		page = 1;
		$.get(
			"/tweet/comment/get",
			{
				key: key,
				page: 1
			}
		).done(function(data) {
			var rows = '';
			for (i = 0; i < data.comments.length; i++) {
				rows +=
					'<tr><td><p>' +
					data.comments[i].data +
					'</p></td><td>' +
					data.comments[i].post_date +
					'</td><td><a class="delete-message" href="#" data-comment="' +
					data.comments[i]._id +
					'"><span class="glyphicon glyphicon-remove"></span></a></td>' +
					'<td><a class="delete-message" href="#" data-comment="' +
					data.comments[i]._id +
					'"><span class="glyphicon glyphicon-edit"></span></a></td></tr>'
				;
			}

			$('#modal-tweets').html(
				'<table class="table">' +
				'	<thead><th>Message</th><th>Post date</th><th>Delete</th><th>Edit</th></thead>' +
				'	<tbody>' +
						rows +
				'	</tbody>' +
				'</table>'
			);
		});		
	});

	$(document).on('click', '#edit-tweets a.delete-message', function() {
		var response = confirm("Do you want to delete this comment ?");
		var key = localStorage.getItem("tweet_key");

		if (response) {
			$.get(
				"/tweet/comment/remove",
				{
					key: key,
					comment_id: $(this).attr('data-comment')
				}
			).done(function(data) {
				window.location.reload(true);
			});
		}
	});

	function showMessages(data, isModal) {
		if (isModal) {
			var rows = '';
			for (i = 0; i < data.comments.length; i++) {
				rows += '<tr><td><p>' + data.comments[i].data + '</p></td><td>' + data.comments[i].post_date + '</td></tr>';
			}

			$('#modal-tweets').html(
				'<table class="table">' +
				'	<thead><th>Message</th><th>Post date</th></thead>' +
				'	<tbody>' +
						rows +
				'	</tbody>' +
				'</table>'
			);
		} else {
			$('#messages-panel').empty();
			$.each(data.comments, function(i, item) {
				$('#messages-panel').append(
					'<div class="row">' +
					'	<div class="col-md-12">' +
					'		<div class="panel panel-default" data-id="' + data.comments[i]._id + '">' +
					'			<div class="panel-heading">' +
									data.comments[i].username +
					'			</div>' +
					'			<div class="panel-body">' +
									data.comments[i].data.replace(/\n/g, "<br />") +
					'			</div>' +
					'			<div class="panel-footer">Posted on ' + data.comments[i].post_date +
					'				<div class="votes"><span class="glyphicon glyphicon-thumbs-up"></span><span class="glyphicon glyphicon-thumbs-down"></span></div>' +
					'			</div>' +
					'		</div>' +
					'	</div>' +
					'</div>'
				);
			});
		}
	}
});