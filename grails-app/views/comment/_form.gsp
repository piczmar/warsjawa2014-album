<%@ page import="album.Comment" %>



<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'text', 'error')} required">
	<label for="text">
		<g:message code="comment.text.label" default="Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="text" cols="40" rows="5" maxlength="200" required="" value="${commentInstance?.text}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'picture', 'error')} required">
	<label for="picture">
		<g:message code="comment.picture.label" default="Picture" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="picture" name="picture.id" from="${album.Picture.list()}" optionKey="id" required="" value="${commentInstance?.picture?.id}" class="many-to-one"/>

</div>

