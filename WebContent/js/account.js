$(document).ready(function() {
	$.get("/tweet/account/getinfo", {
		key: localStorage.getItem("tweet_key")
	}).done(function (data) {
		$('#account-username').append('<td><p>' + data.account_info.username + '</p><a href="#"><span class="glyphicon glyphicon-edit" style="float:none;padding-left:10px;"></span></a></td>');
		$('#account-email').append('<td><p>' + data.account_info.email + '</p><a href="#"><span class="glyphicon glyphicon-edit" style="float:none;padding-left:10px;"></span></a></td>');
		$('#account-firstname').append('<td><p>' + data.account_info.firstname + '</p><a href="#"><span class="glyphicon glyphicon-edit" style="float:none;padding-left:10px;"></span></a></td>');
		$('#account-lastname').append('<td><p>' + data.account_info.lastname + '</p><a href="#"><span class="glyphicon glyphicon-edit" style="float:none;padding-left:10px;"></span></a></td>');
		$('#account-subdate').append('<td><p>' + data.account_info.sub_date + '</p></td>');
	});

	$(document).on('click', '.row-account-infos a', function() {
		var tr = $(this).parents().get(1)
		var field = $(tr).attr('id').split('-')[1];

		$(this).prev('p').replaceWith('<input type="text" value="'+$(this).prev('p').text()+'" />');
		$(this).replaceWith('<button type="button" class="btn btn-primary" data-field="' + field + '">Save</button>');
	});

	$(document).on('click', '.row-account-infos button', function() {
		$.get("/tweet/account/saveinfo", {
			key: localStorage.getItem("tweet_key"),
			field: $(this).attr('data-field'),
			data: $(this).prev().val()
		}).done(function (data) {
			window.location.reload();
		});
	});
});