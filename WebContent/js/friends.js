$('#show-friends').click(function () {
	if ($('#friends-panel').is(':visible')) {
		$('#friends-panel').hide();
	} else {
		$('#friends-panel').show();
	}
});