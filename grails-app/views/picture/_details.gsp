<%@ page import="album.Picture" %>
<div class="center image-wrapper">
<input class="imgId" type="text" hidden="true" value="${pictureInstance.id}"/>
<g:link action="show" id="${pictureInstance.id}">
 <al:pictureImg picture="${pictureInstance}" target="_blank" title="Show Original" width="200px" height="200px"/></g:link>
<p>${fieldValue(bean: pictureInstance, field: "title")}</p>
created on <g:formatDate date="${pictureInstance.dateCreated}" /></p>
<button  class="showComments" type="button" >Comments ${pictureInstance.comments.size()}</button>
</div>
