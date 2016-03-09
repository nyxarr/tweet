$(document).ready(function() {
	/*$('#send-message').on('submit', function() {
		$.get(
			"/tweet/"
		)
	})*/
	
	$.get(
		"/tweet/comment/get"
	).done(function(data) {
		$.each(data.comments, function(i, item)  {
					$('#messages-panel').append(
							'<div class="panel panel-default">' +
							'<div class="panel-body">' +
						    data.comments[i].data +
						    '</div>' +
						    '<div class="panel-footer">Posted on ' + data.comments[i].post_date + '</div>' +
						    '</div>'
					);
				});
	});
});