$(document).ready(function() {
	if (localStorage.getItem("tweet_key")) {
		$('li.show-login').replaceWith("<li id='logout-submit'><a href='javascript:void(0)'>Logout</a></li>");
		$('#leftmenu-ul > ul').prepend('<div id="welcome">Welcome, ' + localStorage.getItem("username") +'!</div>')

		$.get('/tweet/friends/get', {
				key: localStorage.getItem("tweet_key")
			},
			function(data) {
				if (!data.friends.length) {
					$('#friends-panel ul').append(
						'<li>No friends</li>'
					);
				}
				
				$.each(data.friends, function(i, item) {
					$('#friends-panel ul').append(
						'<li><button class="remove-friend" onclick="removeFriend(this)"><span aria-hidden="true">&times;</span></button>' + item.username + '</li>'
					);
				});
			}
		);
	}

	$('.show-login').click(function() {
		$('#shadow-popup').show();
		$('.login-box').show();
	});

	$('#shadow-popup').click(function() {
		$(this).hide();
		$('.login-box').css("height", "250px");
		$('.login-box').hide();
		$("#login-form").show();
		$("#register-form").hide();
	});

	$('#login-submit').on('click', function() {
		if (!$('#username').val() || !$('#password').val()) {
			alert('Fields are required.');
			return;
		}

		var username = $('#username').val();

		$.get(
			"/tweet/login", {
				username: $('#username').val(),
				password: $('#password').val()
			}
		).done(function(data) {
			if (data.error) {
				alert(data.error.message);
				return;
			}

			var key = data.key;
			localStorage.setItem("tweet_key", key);
			localStorage.setItem("username", username);

			$.get("/tweet/comment/get", {
				key: key
			}).done(function(data) {
				$('.login-box').hide();
				$('#shadow-popup').hide();
				location.reload();
			});
		});
	});

	$('#logout-submit').click(function() {
		$.get(
			"/tweet/logout", {
				key: localStorage.getItem("tweet_key")
			}
		).done(function() {
			localStorage.removeItem("tweet_key");
			location.reload();
		});
	});

	$("#register").on("click", function() {
		$('.login-box').animate({
				height: "375px"
			},
			500);
		$("#login-form").fadeOut("slow", function() {
			$("#register-form").fadeIn("slow");
		});
	});

	$("#register-form").on('submit', function() {
		if ($('#password-confirm').val() != $('#password-register').val()) {
			alert('Please enter same password.');
		} else {
			$.get(
				"/tweet/register", {
					firstname: $('#firstname-register').val(),
					lastname: $('#lastname-register').val(),
					username: $('#username-register').val(),
					password: $('#password-register').val(),
					email: $('#email-register').val()
				}
			).done(function(data) {
				if (data.error) {
					alert(data.error.message);
				} else {

				}
			});
		}
	});
});