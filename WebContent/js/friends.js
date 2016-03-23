$(document).ready(function($) {
	if (localStorage.getItem("tweet_key")) {
		$.get('/tweet/friends/get', {
			key: localStorage.getItem("tweet_key")
		}).done(function(data) {
			if (data.friends && !data.friends.length) {
				$('#friends-panel ul').append(
					'<li>No friends</li>'
				);
			}

			$.each(data.friends, function(i, item) {
				$('#friends-panel ul').append(
					'<li><button class="remove-friend" onclick="removeFriend(this)"><span class="glyphicon glyphicon-remove" aria-hidden="true" style="font-size:0.8em;"></span></button>' +
					item.username +
					'</li>'
				);
			});
		});
	}

	$('#show-friends').click(function() {
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
});