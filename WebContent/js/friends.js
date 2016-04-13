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
					'<li><a href="#" class="remove-friend"><span class="glyphicon glyphicon-remove" aria-hidden="true" style="font-size:0.8em; float:left; margin-top:3px;"></span></a>' +
					item.friend_username +
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
	
	$('#add-friend-button').click(function() {
		if ($('#add-friend-form').is(':visible')) {
			$('#add-friend-form').hide();
		} else {
			$('#add-friend-form').show();
			$('#friend-add-input').focus();
		}
	});
	
	$("#friend-add-input").on('keydown', function(event) {
		if(event.which == 13) {
			$.get(
			"/tweet/friends/add",
			{
				key: localStorage.getItem("tweet_key"),
				friend: $('#friend-add-input').val()
			}).done(function(data) {
				console.log(data);
				if (data.error) {
					alert(data.error);
					return;
				}

				location.reload(true);
			});
		}
	});
	
	$('#friend-add-input').click(function(event){
		event.stopPropagation();
	});

	$(document).on('click', 'button.remove-item', function() {
		$.get(
			'/tweet/friends/remove', {
				key: localStorage.getItem("tweet_key"),
				friend: $(this).closest('li').html()
			}
		).done(location.reload(true));
	});

	$(document).on('click', '.remove-friend', function() {
		var r = confirm("Do you want to remove '" + $(this).closest('li').text() +"' ?");

		if (r == true) {
			removeFriend(this);
		}
	});

	function removeFriend(element) {
		var text = '';
		$(element).closest('li').contents().each(function() {
			if (this.nodeType === 3) {
				text += this.wholeText;
			}
		});

		console.log(text);

		$.get(
			'/tweet/friends/remove', {
				key: localStorage.getItem("tweet_key"),
				friend: text
			}
		).done(location.reload(true));
	}
});