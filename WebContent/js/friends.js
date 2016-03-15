$('#show-friends').click(function () {
	if ($('#friends-panel').is(':visible')) {
		$('#friends-panel').hide();
	} else {
		$('#friends-panel').show();
	}
});

$(document).on('click', 'button.remove-item', function() {
	$.get(
		'/tweet/friends/remove', {
			key: localStorage.getItem("tweet_key"),
			friend: $(this).closest('li').html()
		}
	).done(location.reload(true));
});

function removeFriend(button) {
	var text = '';
	$(button).closest('li').contents().each(function() {
		if (this.nodeType === 3) {
			text += this.wholeText;
		}
	});

	$.get(
		'/tweet/friends/remove', {
			key: localStorage.getItem("tweet_key"),
			friend: text
		}
	).done(location.reload(true));
}