$('#leftside-menu').mouseenter(function(event) {
	$('#leftside-menu').animate({left: "+=260px"}, 350)
});

$('#leftside-menu').mouseleave(function(event) {
	$('#leftside-menu').animate({left: "-=260px"}, 500)
});