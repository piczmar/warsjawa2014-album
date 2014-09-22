<div id="dialog" title="Comments">
	<div class="content" ></div>
	<div class="actions">
		<a class="create">New</a>
	</div>
</div>

<script>
$(function() {
	$(".showComments").on('click',function(){
		
		var imgId = $(this).siblings( ".imgId" ).val();
		$( "#dialog .actions .create" ).attr('href','${request.contextPath}/comment/create'+ "?pictureId="+imgId);
		
		$( "#dialog .content" ).empty();
		$.get( "${request.contextPath}/comment/index.json?pictureId="+imgId, function( data ) {
			$(data).each(function(index, item) {
				var editHref = document.createElement('a');
				editHref.setAttribute('href','${request.contextPath}/comment/edit/'+item.id + "?pictureId="+imgId);
				editHref.innerHTML = 'Edit';


				$( "#dialog .content" ).append(
					$(document.createElement('p')).text(item.text)
				).append(
					$(document.createElement('i')).text('Added: ' + item.dateCreated + ' ')
				).append(
					$(editHref)
				).append(
					$(document.createElement('hr'))
				);
			});
			$( "#dialog" ).dialog({ height: 350 });
		});


	});
});
$( "#dialog" ).hide();
</script>
