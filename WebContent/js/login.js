$(document).ready(function() {
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

	$('#login-submit').click(function() {
		$.get(
			"/tweet/login",
			{ username: $('#username').val(), password: $('#password').val() }
		).done(function (data) {
			var key = data.key;
			$.get("/tweet/comment/get",
					{ key: key }
			).done(function (data) {
				if (data.error) {
					alert(data.error.message);
					return;
				}
				$('.login-box').hide();
				$('#shadow-popup').hide();
			});
		});
	});
	
	$( "#register" ).on( "click", function() {
        $('.login-box').animate({
            height: "350px"},
            500);
	    $( "#login-form" ).fadeOut( "slow", function(){
	       $( "#register-form" ).fadeIn( "slow" );
	      });
	});
	
	$("#register-submit").click(function() {
		$.get(
            "/tweet/register",
            {
                firstname: $('#firstname-register').val(),
                lastname: $('#lastname-register').val(),
                username: $('#username-register').val(),
                password: $('#password-register').val(),
                email: $('#email-register').val()
            }
        ).done(function (data) {
            if (data.error) {
                alert(data.error.message);
            }
        });
	});
});
