
$('.story-preview').click(function() {
	window.location = $(this).find("#storyLink").attr("th:href");
	return false;
});