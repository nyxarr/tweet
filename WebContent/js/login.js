$(document).ready(function() {
	$('.show-login').click(function() {
		$('.login-box').show();
		$('#shadow-popup').show();
	});

	$('#login-submit').click(function() {
		$.get(
			"/tweet/login",
			{ username: $('#username').val(), password: $('#password').val() }
		).done(function (data) {
			alert(data.key);
		});
	});
});
