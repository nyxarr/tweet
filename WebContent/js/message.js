$(document).ready(function() {
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
	
	$('#send-message').click(function() {
		var key = localStorage.getItem("key");
		//if (key) {
			$.get(
				"/tweet/comment/add",
				{ key: key, comment: $('#send-textarea').val() }
			).done(function (data) {
				//location.reload(true);
			});
		//}
	});
});