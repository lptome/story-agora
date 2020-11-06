$(function() {
	$("#back").click(function() {
		window.history.back();
	});
});

$(document)
	.ready(
		function() {
			$('#storyContent')
				.summernote(
					{
						height: 400,
						width: '100%',
						disableResizeEditor: true,
						fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '24'],
						toolbar: [
							[
								'style',
								['bold', 'italic',
									'underline']],
							[
								'font',
								['strikethrough',]],
							['fontsize',
								['fontsize']],
							['para', ['paragraph', 'height']],],
						placeholder: 'Begin your story...',
						callbacks: {
							onImageUpload: function(data) {
								data.pop();
							},
							onPaste: function(e) {
								var charLimit = 5000;
								var textToPaste = e.originalEvent.clipboardData
									.getData('text');
								var currentText = $(
									".note-editable")
									.text();
								//Prevent pasting if it exceeds the character limit;
								if (textToPaste.length
									+ currentText.length >= charLimit)
									e.preventDefault();

							},
							onKeydown: function(e) {
								var charLimit = 5000;
								var text = $.trim($(
									".note-editable")
									.text());
								var totalChar = text.length;

								if (totalChar >= charLimit)
									if (e.keyCode != 8)
										e.preventDefault();
							}
						}
					});
		});