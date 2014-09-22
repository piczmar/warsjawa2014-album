<%@ page import="album.Picture" %>



<div class="fieldcontain ${hasErrors(bean: pictureInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="picture.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${pictureInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pictureInstance, field: 'data', 'error')} ">
	<label for="data">
		<g:message code="picture.data.label" default="Data" />
		
	</label>
	<input type="file" id="data" name="data" />

</div>

<div class="fieldcontain ${hasErrors(bean: pictureInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="picture.comments.label" default="Comments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${pictureInstance?.comments?}" var="c">
    <li><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="comment" action="create" params="['picture.id': pictureInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')])}</g:link>
</li>
</ul>


</div>

