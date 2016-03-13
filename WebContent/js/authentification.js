$(document).ready(function() {
	if (localStorage.getItem("key")) {
		$('li.show-login').replaceWith("<li id='logout-submit'><a href='javascript:void(0)'>Logout</a></li>");
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

	$('#login-form').on('submit', function() {
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
			localStorage.setItem("key", key);

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
			"/tweet/logout"
		).done(function() {
			localStorage.removeItem("key");
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