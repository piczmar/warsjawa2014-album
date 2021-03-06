
<%@ page import="album.Picture" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'picture.label', default: 'Picture')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-picture" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-picture" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

			
				<g:render template="details"  model="['pictureInstance':pictureInstance]"/>
				<g:render template="detailsScript" />

				
		</div>
			
				<g:form url="[resource:pictureInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link controller="image" action="rotateL"  params="[id:pictureInstance.id]" ><span class="rotate-left"><g:message code="default.button.rotateL.label" default="Rotate Left" /></span></g:link>
					<g:link controller="image" action="rotateR"  params="[id:pictureInstance.id]" ><span class="rotate-right"><g:message code="default.button.rotateR.label" default="Rotate Right" /></span></g:link>
					
					<g:link class="edit" action="edit" resource="${pictureInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					
					<sec:ifAllGranted roles="ROLE_ADMIN">
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</sec:ifAllGranted>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
